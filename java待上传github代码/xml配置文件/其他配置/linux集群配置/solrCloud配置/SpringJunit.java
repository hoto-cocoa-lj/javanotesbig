package com.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.data.solr.core.query.result.GroupResult;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.me.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr1111.xml")
public class SpringJunit {

	@Autowired
	private CloudSolrServer c;
	@Autowired
	private SolrTemplate solrTemplate;

	// 增加（修改）
	@Test
	public void testAdd() {
		System.out.println(solrTemplate);
		TbItem item = new TbItem();
		item.setId(1L);
		item.setBrand("华为");
		item.setCategory("手机");
		item.setGoodsId(1L);
		item.setSeller("华为2号专卖店");
		item.setTitle("华为Mate9");
		item.setPrice(new BigDecimal(2000));
		solrTemplate.saveBean(item);
		solrTemplate.commit();
	}

	// 按主键查询
	@Test
	public void testFindOne() {
		TbItem item = solrTemplate.getById(1, TbItem.class);
		System.out.println(item.getTitle());
	}

	@Test
	public void testFindOne1() throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		QueryResponse r = c.query(query);
		System.out.println(r);
	}

	// 按主键删除
	@Test
	public void testDelete() {
		solrTemplate.deleteById("1");
		solrTemplate.commit();
	}

	// 分页查询,首先循环插入100条测试数据
	@Test
	public void testAddList() {
		List<TbItem> list = new ArrayList();

		for (int i = 0; i < 100; i++) {
			TbItem item = new TbItem();
			item.setId(i + 1L);
			item.setBrand("华为" + i % 4);
			item.setCategory("手机" + i % 5);
			item.setGoodsId(1L);
			item.setSeller("华为" + i % 6 + "号专卖店");
			item.setTitle("华为Mate" + i);
			item.setPrice(new BigDecimal(2000 + i));
			Map<String, String> specMap = new HashMap();
			specMap.put("key" + i % 7, "value" + i % 7);
			item.setSpecMap(specMap);
			list.add(item);

		}

		solrTemplate.saveBeans(list);
		solrTemplate.commit();
	}

	// 编写分页查询测试代码：

	@Test
	public void testPageQuery() {
		Query query = new SimpleQuery("*:*");
		query.setOffset(20);// 开始索引（默认0）
		query.setRows(20);// 每页记录数(默认10)
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数：" + page.getTotalElements());
		List<TbItem> list = page.getContent();
		showList(list);
	}

	// 显示记录数据
	private void showList(List<TbItem> list) {
		for (TbItem item : list) {
			System.out.println(item.getTitle() + ":" + item.getPrice());
		}
	}

	// 条件查询, Criteria 用于对条件的封装：
	@Test
	public void testPageQueryMutil() {// item_keywords
		Query query = new SimpleQuery("*:*");
		Criteria criteria = new Criteria("item_title").contains("2");
		// criteria = criteria.and("item_title").contains("5");
		query.addCriteria(criteria);
		// query.setOffset(20);//开始索引（默认0）
		// query.setRows(20);//每页记录数(默认10)
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数：" + page.getTotalElements());
		List<TbItem> list = page.getContent();
		showList(list);
	}

	// 条件查询, Criteria 用于对条件的封装：
	@Test
	public void testPageQueryMutil1() {// item_keywords
		HighlightQuery query = new SimpleHighlightQuery();
		Criteria criteria = new Criteria("item_keywords").contains("专卖");
		criteria = criteria.and("item_keywords").contains("2");
		query.addCriteria(criteria);
		FilterQuery q1 = new SimpleQuery("*:*");
		query.addFilterQuery(q1);
		HighlightOptions o = new HighlightOptions().addField("item_title");
		o.setSimplePrefix("<h1>");
		o.setSimplePostfix("</h1>");
		query.setHighlightOptions(o);
		// query.setOffset(20);//开始索引（默认0）
		// query.setRows(20);//每页记录数(默认10)
		HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
		System.out.println("总记录数：" + page.getTotalElements());
		List<HighlightEntry<TbItem>> hs = page.getHighlighted();
		for (HighlightEntry<TbItem> h : hs) {
			TbItem item = h.getEntity();
			System.out.println(item);
			if (h.getHighlights().size() > 0 && h.getHighlights().get(0).getSnipplets().size() > 0) {
				item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));// 设置高亮的结果

			}
			System.out.println(item);
		}
	}

	// 删除全部数据
	@Test
	public void testDeleteAll() {
		Query query = new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}

	private List searchCategoryList(Map searchMap) {
		List<String> list = new ArrayList();
		Query query = new SimpleQuery();
		// 按照关键字查询
		Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		// 设置分组选项
		GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");
		query.setGroupOptions(groupOptions);
		// 得到分组页
		GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
		// 根据列得到分组结果集
		GroupResult<TbItem> groupResult = page.getGroupResult("item_category");
		// 得到分组结果入口页
		Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
		// 得到分组入口集合
		List<GroupEntry<TbItem>> content = groupEntries.getContent();
		for (GroupEntry<TbItem> entry : content) {
			list.add(entry.getGroupValue());// 将分组结果的名称封装到返回值中
		}
		return list;
	}

	@Test
	public void searchCategory() {
		List<String> list = new ArrayList();
		Query query = new SimpleQuery("*:*");
		Query query1 = new SimpleQuery("*:*");
		// 按照关键字查询
		// Criteria criteria = new Criteria("item_keywords").contains("2");
		// query.addCriteria(criteria);
		// 设置分组选项
		// String groupBy = "item_category";
		String groupBy = "item_brand";
		GroupOptions groupOptions = new GroupOptions().addGroupByField(groupBy);
		query.setGroupOptions(groupOptions);
		ScoredPage<TbItem> l = solrTemplate.queryForPage(query1, TbItem.class);
		// 得到分组页
		GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
		// 根据列得到分组结果集
		GroupResult<TbItem> groupResult = page.getGroupResult(groupBy);
		// 得到分组结果入口页
		Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
		// 得到分组入口集合
		List<GroupEntry<TbItem>> content = groupEntries.getContent();
		for (GroupEntry<TbItem> entry : content) {
			list.add(entry.getGroupValue());// 将分组结果的名称封装到返回值中
		}
		System.out.println(list);
	}

	@Test
	public void addFilterQuery() {
		Query query = new SimpleQuery("*:*");
		String[] ss = new String[] { "华为1", "华为2" };
		Criteria filterCriteria = new Criteria("item_brand").in(ss);
		FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
		query.addFilterQuery(filterQuery);
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		query.addSort(sort);
		query.setRows(5);
		query.setOffset(2);
		ScoredPage<TbItem> q = solrTemplate.queryForPage(query, TbItem.class);
		List<TbItem> content = q.getContent();
		for (TbItem t : content) {
			System.out.println(t);
		}
	}
}
