package bean;

import java.util.List;

public class Pagebean {
	List list;
	int cpage, onepage, datanum, pagenum;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public int getOnepage() {
		return onepage;
	}

	public void setOnepage(int onepage) {
		this.onepage = onepage;
	}

	public int getDatanum() {
		return datanum;
	}

	public void setDatanum(int datanum) {
		this.datanum = datanum;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	@Override
	public String toString() {
		return "Pagebean [list=" + list + ", cpage=" + cpage + ", onepage=" + onepage + ", datanum=" + datanum
				+ ", pagenum=" + pagenum + "]";
	}

	public Pagebean(int cpage, int onepage) {
		super();
		this.cpage = cpage;
		this.onepage = onepage;
	}

	public int getIndex() {
		return (cpage - 1) * onepage;
	}
}
