package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.ssm.mapper.ItemsMapper;
import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.ItemsExample;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemsMapper itemsMapper;
	@Override
	public List<Items> findAll(QueryVo queryVo) {
		ItemsExample e = new ItemsExample();
		if(queryVo!=null && queryVo.getQv()!=null){
			System.out.println("有查询条件");
			e.createCriteria().andNameLike("%"+queryVo.getQv().getName()+"%");
		}
		List<Items> s = itemsMapper.selectByExampleWithBLOBs(e);
		System.out.println("List<Items> findAll s="+s);
		return s;
	}
	
	@Override
	public List<Items> findAll() {
		return itemsMapper.selectByExampleWithBLOBs(new ItemsExample());
	}
	
	@Override
	public Items findByID(int id) {
		// TODO Auto-generated method stub
		return itemsMapper.selectByPrimaryKey(id);
	}
	@Override
	public void update(Items items) {
		itemsMapper.updateByPrimaryKeySelective(items);		
	}

}
