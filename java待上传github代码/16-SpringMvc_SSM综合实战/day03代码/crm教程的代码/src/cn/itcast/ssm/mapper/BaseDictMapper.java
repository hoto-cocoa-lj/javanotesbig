package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.pojo.BaseDict;

public interface BaseDictMapper {
	public List<BaseDict> findByTypecode(String typecode);
}
