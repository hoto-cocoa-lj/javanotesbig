package cn.itcast.ssm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.ssm.pojo.BaseDict;


public interface BaseDictService {
	public List<BaseDict> findByTypeCode(String s);
}
