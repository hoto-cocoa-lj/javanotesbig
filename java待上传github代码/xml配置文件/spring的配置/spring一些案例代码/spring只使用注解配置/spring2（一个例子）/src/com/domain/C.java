package com.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class C {
	private String[] ss;
	private List ll;
	private Map mm;
	private Properties pp;

	@Override
	public String toString() {
		return "C [ss=" + Arrays.toString(ss) + ", ll=" + ll + ", mm=" + mm + ", pp=" + pp + "]";
	}

	public String[] getSs() {
		return ss;
	}

	public void setSs(String[] ss) {
		this.ss = ss;
	}

	public List getLl() {
		return ll;
	}

	public void setLl(List ll) {
		this.ll = ll;
	}

	public Map getMm() {
		return mm;
	}

	public void setMm(Map mm) {
		this.mm = mm;
	}

	public Properties getPp() {
		return pp;
	}

	public void setPp(Properties pp) {
		this.pp = pp;
	}
}
