package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.pojo.Items;

public interface ItemService {

	public List<Items> findAll();

	public Items findById(int itemId);

	public void update(Items item);
	
}
