package cn.itcast.ssm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
//S:source, T:target
public class DateConverter implements Converter<String, Date> {
//	2016-02-03 13:22:53
	@Override
	public Date convert(String source) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

}
