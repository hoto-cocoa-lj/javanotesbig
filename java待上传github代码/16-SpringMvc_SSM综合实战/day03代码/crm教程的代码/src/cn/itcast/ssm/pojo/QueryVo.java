package cn.itcast.ssm.pojo;

public class QueryVo {
	
   private int currentPage;
   
   private int pageSize;
   
   private String custName;
   
   private String custSource;
   
   private String custIndustry;
   
   private String custLevel;
   
   private int start;

public int getStart() {
	return start;
}

public void setStart(int start) {
	this.start = start;
}

public int getCurrentPage() {
	return currentPage;
}

public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}

public int getPageSize() {
	return pageSize;
}

public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}

public String getCustName() {
	return custName;
}

public void setCustName(String custName) {
	this.custName = custName;
}

public String getCustSource() {
	return custSource;
}

public void setCustSource(String custSource) {
	this.custSource = custSource;
}

public String getCustIndustry() {
	return custIndustry;
}

public void setCustIndustry(String custIndustry) {
	this.custIndustry = custIndustry;
}

public String getCustLevel() {
	return custLevel;
}

public void setCustLevel(String custLevel) {
	this.custLevel = custLevel;
}
   
   
}
