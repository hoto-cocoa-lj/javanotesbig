package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.ssm.mapper.BaseDictMapper;
import cn.itcast.ssm.pojo.BaseDict;
import cn.itcast.ssm.pojo.BaseDictExample;
import cn.itcast.ssm.service.BaseDictService;

@Service
public class BaseDictServiceImpl implements BaseDictService{
	@Autowired
	private BaseDictMapper baseDictMapper;
	@Override
	public List<BaseDict> findByTypeCode(String s) {
		// TODO Auto-generated method stub
		BaseDictExample e = new BaseDictExample();
		e.createCriteria().andDictTypeCodeEqualTo(s);
		return baseDictMapper.selectByExample(e);
	}

}
