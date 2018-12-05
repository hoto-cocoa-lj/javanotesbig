package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.QueryVo;

public interface ItemService {
	public List<Items> findAll(QueryVo queryVo);
	public List<Items> findAll();
	public Items findByID(int id);
	public void update(Items items);
	
}
