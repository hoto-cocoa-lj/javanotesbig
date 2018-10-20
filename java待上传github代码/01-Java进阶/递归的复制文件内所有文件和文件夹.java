package com.itheima.demo2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
	static int u, v;

	public static void main(String[] args) throws IOException {
		File f1 = new File("1");
		File f2 = new File("C:\\Users\\Administrator\\Desktop/啊");
		f2.mkdir();
		d1(f1, f2);
		System.out.println(u + " " + v);
	}

	public static void d1(File f1, File f2) throws IOException {
		File[] l1 = f1.listFiles();
		for (File a : l1) {
			File c = new File(f2, a.getName());
			if (a.isFile()) {

				copy(a, c);
				System.out.println("复制文件" + c + "成功");
				u++;
			} else if (a.isDirectory()) {
				System.out.println("复制文件jia:" + c.mkdir());
				v++;
				d1(a, c);

			}
		}
	}

	public static void copy(File a, File b) throws IOException {

		BufferedInputStream b1 = new BufferedInputStream(new FileInputStream(a));
		BufferedOutputStream b2 = new BufferedOutputStream(new FileOutputStream(b));
		byte[] bs = new byte[1024];
		int len = 0;
		while ((len = b1.read(bs)) != -1) {
			b2.write(bs, 0, len);
		}
		b2.flush();

		b2.close();
		b1.close();

	}

}