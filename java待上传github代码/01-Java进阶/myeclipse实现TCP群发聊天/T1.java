package com.itheima.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class T1 {
	static int po1 = 11111;

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(po1);
		while (true) {
			System.out.println("等待链接........");
			Socket c = s.accept();
			System.out.println("链接来了");
			InputStream in = c.getInputStream();
			OutputStream out = c.getOutputStream();
			new Thread(new Send(out, s, c)).start();
			new Thread(new Re(in, s, c)).start();
		}

	}

}
