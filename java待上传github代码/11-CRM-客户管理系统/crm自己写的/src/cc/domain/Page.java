package cc.domain;

public class Page {
private int a,b=3,c,d;


@Override
public String toString() {
	return "Page [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
}

public int getA() {
	return a;
}

public void setA(int a) {
	this.a = a;
}

public int getB() {
	return b;
}

public int getC() {
	return c;
}

public void setC(int c) {
	this.c = c;
}

public int getD() {
	Double d=Math.ceil(c*1.0/b);
	return d.intValue();
}




}
