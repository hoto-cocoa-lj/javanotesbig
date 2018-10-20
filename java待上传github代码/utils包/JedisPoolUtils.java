package com.jedis;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	private static JedisPoolConfig jc = null;
	private static int maxTotal = 0;
	private static long maxWaitMillis = 0;
	private static JedisPool jp = null;
	private static String host = null;
	private static int port = 0;
	// static {
	// jc = new JedisPoolConfig();
	// Properties p = new Properties();
	// try {
	// File f = new File("src/jedisconfig.properties");
	// System.out.println(f.getAbsolutePath());
	// FileInputStream ff = new FileInputStream(f);
	// p.load(ff);
	// jc.setMaxTotal(Integer.parseInt((String) p.get("maxTotal")));
	// jc.setMaxWaitMillis(Long.parseLong((String) p.get("maxWaitMillis")));
	// String host = (String) p.get("host");
	// int port = Integer.parseInt((String) p.get("port"));
	// System.out.println(host + ":" + port);
	// jp = new JedisPool(jc, host, port);
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	static {
		jc = new JedisPoolConfig();
		ResourceBundle re = ResourceBundle.getBundle("jedisconfig");
		maxTotal = Integer.parseInt(re.getString("maxTotal"));
		maxWaitMillis = Long.parseLong(re.getString("maxWaitMillis"));
		host = String.valueOf(re.getString("host"));
		port = Integer.parseInt(re.getString("port"));
		jc.setMaxTotal(maxTotal );
		jc.setMaxWaitMillis(maxWaitMillis);
		jp = new JedisPool(jc, host, port );
	}

	public static Jedis getj() {
		return jp.getResource();
	}

}
