package cn.itcast.ssm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String s) {
		
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
