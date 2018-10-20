package com.itheima.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//注意：凡是用byte[]接收的数据，不管读写都要用(byte[],0,length)的方法；
//这里Send的b不是接收而是赋值，可以不用；Re的是接收，必须用。
//和udp的思路基本一样，问题是服务器每接受一个访问，就创建一个线程，
//导致服务器发送的信息只会被一个客户端抢到
public class T {
}

class Send implements Runnable {
	OutputStream out;
	ServerSocket s;
	Socket c;
	String n1;

	byte[] b = new byte[1024];

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			b = sc.nextLine().getBytes();
			//没有任何输入的话重新进入循环
			if (b.length == 0) {
				continue;
			}
			try {
				out.write(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(n1 + "发送信息成功");
		}
	}

	public Send(OutputStream out, ServerSocket s, Socket c) {
		super();
		this.out = out;
		this.s = s;
		this.c = c;
		if (s != null) {
			n1 = s.getInetAddress().getHostAddress() + ":" + s.getLocalPort();
		} else {
			n1 = "客户"+c.getInetAddress().getHostAddress() + ":" + c.getLocalPort();
		}
	}
}

class Re implements Runnable {

	InputStream in;
	String n2;
	ServerSocket s;
	Socket c;
	byte[] b = new byte[1024];

	@Override
	public void run() {
		while (true) {
			try {
				int len = in.read(b);
				System.out.println(n2 + "说:" + new String(b, 0, len));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Re(InputStream in, ServerSocket s, Socket c) {
		super();
		this.in = in;
		this.s = s;
		this.c = c;
		if (s != null) {
			n2 = "客户" + c.getInetAddress().getHostAddress() + ":" + c.getPort();
		} else {
			n2 = "服务器";
		}
	}
}