package com.itheima.demo2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//注意：凡是用byte[]接收的数据，不管读写都要用(byte[],0,length)的方法，例如SS和CC里用的b；
//SS是服务器类，CC是客户端类，因为服务器会阻塞且必须先运行服务器，所以为了放在一个.java里运行，
//似乎必须使用多线程。run方法不能抛异常，所以各自写了ss方法和cc方法
//不用flush似乎最后一次的数据会丢失一些
public class Test2 {

	public static void main(String[] args) throws IOException {
		new Thread(new SS()).start();
		new Thread(new CC()).start();

	}
}

class SS implements Runnable {
	static int po = 11111;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ss();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ss() throws IOException {
		ServerSocket s = new ServerSocket(po);
		System.out.println("等待链接........");
		Socket c = s.accept();
		System.out.println("链接来了");
		InputStream in = c.getInputStream();
		byte[] b = new byte[1024];
		int lo = 0;
		String na = System.currentTimeMillis() + ".jpg";
		File f = new File(na);
		BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(f));
		int len = 0;
		try {
			while ((len = in.read(b)) != -1) {
				lo += len;
				// System.out.println("getting....." + len);
				o.write(b, 0, len);
				o.flush();
			}
			System.out.println("接收文件完成,名字叫" + na + "   " + lo);
		} finally {
			o.close();
			s.close();
		}
	}
}

class CC implements Runnable {
	static int po = 11111;
	static InetAddress i;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			cc();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cc() throws IOException {

		i = InetAddress.getByName("localhost");
		Socket c = new Socket(i, po);
		OutputStream out = c.getOutputStream();
		byte[] b = new byte[1024];
		File f = new File("1.jpg");
		BufferedInputStream i = new BufferedInputStream(new FileInputStream(f));
		int w = 0;
		try {
			while ((w = i.read(b)) != -1) {
				out.write(b, 0, w);
			}
			System.out.println("发送成功");
		} finally {
			i.close();
			c.close();
		}
	}
}