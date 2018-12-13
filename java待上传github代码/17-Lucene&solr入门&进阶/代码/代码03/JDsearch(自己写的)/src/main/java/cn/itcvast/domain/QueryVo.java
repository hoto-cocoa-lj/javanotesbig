package cn.itcvast.domain;

import java.util.List;

public class QueryVo {

	private List<Product> productList ;
	private Long recordCount;
	private Integer curPage;
	private Integer pageCount;
	
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "QueryVo [productList=" + productList + ", recordCount=" + recordCount + ", curPage=" + curPage
				+ ", pageCount=" + pageCount + "]";
	}
	
}
