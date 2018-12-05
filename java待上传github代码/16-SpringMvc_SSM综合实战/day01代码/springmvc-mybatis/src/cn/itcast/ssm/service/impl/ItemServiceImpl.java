package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.ssm.mapper.ItemsMapper;
import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.ItemsExample;
import cn.itcast.ssm.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsMapper itemsMapper;
	public List<Items> findAll() {
		return itemsMapper.selectByExampleWithBLOBs(new ItemsExample());
	}
	@Override
	public Items findById(int itemId) {
		return itemsMapper.selectByPrimaryKey(itemId);
	}
	@Override
	public void update(Items item) {
		itemsMapper.updateByPrimaryKeySelective(item);
	}

}
