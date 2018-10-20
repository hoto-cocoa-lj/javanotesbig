package com.itheima.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class T3 {
	static int po1 = 11111;

	public static void main(String[] args) throws IOException {

		Socket c = new Socket(InetAddress.getByName("localhost"), po1);
		InputStream in = c.getInputStream();
		OutputStream out = c.getOutputStream();
		new Thread(new Send(out, null, c)).start();
		new Thread(new Re(in, null, c)).start();

	}

}
