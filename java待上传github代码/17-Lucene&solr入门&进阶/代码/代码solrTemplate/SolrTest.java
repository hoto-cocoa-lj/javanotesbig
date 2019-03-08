package com.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.me.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class SolrTest {

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
			
			/*查看数据可以发现如下字段
			{"id": "1",	...,"item_spec_key0": "value0"},
			{"id": "1",	...,"item_spec_key1": "value1"},
			{"id": "1",	...,"item_spec_key2": "value2"},	........*/
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

	// 删除全部数据
	@Test
	public void testDeleteAll() {
		Query query = new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}
	
	//设置高亮，从searchMap得到关键字查询
	private Map searchList(Map searchMap){
		Map map=new HashMap();
		HighlightQuery query=new SimpleHighlightQuery();
		
		//设置高亮的域,从searchMap拿到关键字到复制域去查询,当关键字出现在item_title才会被高亮
		HighlightOptions highlightOptions=new HighlightOptions().addField("item_title");
		highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀 
		highlightOptions.setSimplePostfix("</em>");//高亮后缀
		query.setHighlightOptions(highlightOptions);//设置高亮选项
		//按照关键字查询，关键字从searchMap得到
		Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
		for(HighlightEntry<TbItem> h: page.getHighlighted()){//循环高亮入口集合
			TbItem item = h.getEntity();//获取原实体类			
			if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
				item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
			}	
			//System.out.println(item);
		}		
		map.put("rows",page.getContent());
		return map;
	}
	
	
	@Test
	public void searchCategory() {
		List<String> list = new ArrayList();
		Query query = new SimpleQuery("*:*");
		// 按照关键字查询
		Criteria criteria = new Criteria("item_keywords").contains("2");
		query.addCriteria(criteria);
		
		// 设置分组选项
		// String groupBy = "item_category";
		String groupBy = "item_brand";	
		GroupOptions groupOptions = new GroupOptions().addGroupByField(groupBy);
		query.setGroupOptions(groupOptions);
		// 得到分组页
		GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
		// 根据列得到分组结果集
		GroupResult<TbItem> groupResult = page.getGroupResult(groupBy);
		// 得到分组结果入口页,这个Page是org.springframework.data.domain.Page
		Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
		// 得到分组入口集合
		List<GroupEntry<TbItem>> content = groupEntries.getContent();
		for (GroupEntry<TbItem> entry : content) {
			list.add(entry.getGroupValue());// 将分组结果的名称封装到返回值中
		}
		
		//debug可以发现group查询只能查到分组的组名，比如以品牌分组会把要查询的所有商品
		//的品牌放进一个list(重复的不放入)，不能查到该组下有哪些商品
		System.out.println(list);
	}
	
	/* 超重要：条件查询, and有两种写法。
		一种是创建一个Criteria，用and添加多个条件(这个还可以用or添加);
		一种是创建多个Criteria和FilterQuery	*/
	@Test
	public void testPageQueryMutil() {
		Query query = new SimpleQuery("*:*");
		
		Criteria criteria = new Criteria("item_brand").is("华为1");
		criteria = criteria.and("item_title").contains("5");
		query.addCriteria(criteria);
		
		/* 方法2：
		Criteria criteria = new Criteria("item_brand").is("华为1");
		query.addCriteria(criteria);
		Criteria criteria1 = new Criteria("item_title").is("5");
		query.addCriteria(criteria1);
		*/
		
		// query.setOffset(20);//开始索引（默认0）
		// query.setRows(20);//每页记录数(默认10)
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数：" + page.getTotalElements());
		List<TbItem> list = page.getContent();
		showList(list);
	}
	
	
	//排序和in的写法,下面代码返回逆序后从第3个开始的5个值,ss还可以是list,其他未测试
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
