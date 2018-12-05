package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.pojo.BaseDict;

public interface BaseDictService {
	public List<BaseDict> findByTypecode(String typecode);
}
