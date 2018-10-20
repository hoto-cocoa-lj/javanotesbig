package com.itheima.demo2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//T1，T2，T3分别表示3个终端，思路是send方法传入收听者列表和自己，然后遍历列表进行群发。
//和tcp的实现不一样的是，udp的服务器只有一个
public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Send implements Runnable {
	// po和i是接收者列表，d1是自己
	int[] po;
	InetAddress i;
	DatagramSocket d1;

	public Send(int[] po, InetAddress i, DatagramSocket d1) {
		super();
		this.po = po;
		this.i = i;
		this.d1 = d1;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while (true) {
				System.out.println(this.d1.getLocalPort() + "请输入要发送的数据:");
				Scanner bi = new Scanner(System.in);
				//这里buf使用赋值的方法，所以new DatagramPacket里直接使用buf.length，所以这个长度就是数据的真实长度
				byte[] buf = bi.nextLine().getBytes();
				for (int p1 : po) {
					DatagramPacket p = new DatagramPacket(buf, buf.length, i, p1);
					d1.send(p);
				}

				System.out.println("发送ok!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			d1.close();
		}

	}

}

class Reic implements Runnable {
	// po和i是接收者，d1是自己
	// int po;
	// InetAddress i;
	DatagramSocket d1;
	int o = 1024;
	DatagramPacket p = new DatagramPacket(new byte[o], o);

	public Reic(DatagramSocket d1) {
		super();
		// this.po = po;
		// this.i = i;
		this.d1 = d1;
	}

	@Override
	public void run() {
		try {
			while (true) {
				d1.receive(p);
				//System.out.println(p.getAddress().getHostAddress() + ":" + p.getPort());
				//o用来接收数据，所以下面new String里的长度必须用每次接收到数据长度，
				//只能用p.getLength不能用o.length或p.getData().length
				System.out.println(p.getPort()+"说："+new String(p.getData(), 0, p.getLength()));
				// System.out.println(new String(p.getData()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			d1.close();
		}
	}
}