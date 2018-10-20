package com.itheima.demo2;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class T1 {

	public static void main(String[] args) throws SocketException, UnknownHostException {
		int[] po = { 11111, 11112, 11113, 11111 };
		InetAddress i = InetAddress.getByName("127.0.0.1");
		DatagramSocket d1 = new DatagramSocket(po[0], i);
		new Thread(new Send(Arrays.copyOfRange(po, 1, 3), i, d1)).start();
		new Thread(new Reic(d1)).start();

	}

}
