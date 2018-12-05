package cn.itcast.ssm.pojo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class QueryVo {
    private Items qv;
    private List<Items> itemList;
    private int[] ids;
    private String[] names;
    private int id;
    private String name;
    private Float[] prices;
    private Date[] createtimes;
    private String[] details;
    
   	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public Float[] getPrices() {
		return prices;
	}

	public void setPrices(Float[] prices) {
		this.prices = prices;
	}




	public Date[] getCreatetimes() {
		return createtimes;
	}

	public void setCreatetimes(Date[] createtimes) {
		this.createtimes = createtimes;
	}

	public String[] getDetails() {
		return details;
	}

	public void setDetails(String[] details) {
		this.details = details;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public Items getQv() {
		return qv;
	}

	public void setQv(Items qv) {
		this.qv = qv;
	}

	@Override
	public String toString() {
		return "QueryVo [qv=" + qv + ", ids=" + Arrays.toString(ids) + ", names=" + Arrays.toString(names) + ", prices="
				+ Arrays.toString(prices) + ", createtimes=" + Arrays.toString(createtimes) + ", details="
				+ Arrays.toString(details) + "]";
	}






    
}