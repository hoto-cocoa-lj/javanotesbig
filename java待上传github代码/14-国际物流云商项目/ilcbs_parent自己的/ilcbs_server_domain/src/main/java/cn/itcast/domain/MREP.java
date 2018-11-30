package cn.itcast.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

import com.alibaba.fastjson.annotation.JSONField;

public class MREP implements Serializable{
	
	private String[] mr_id;   
	private String[] mr_changed;
	private Integer[] mr_cnumber;
	private Double[] mr_grossWeight;
	private Double[] mr_netWeight;
	private Double[] mr_sizeLength;
	private Double[] mr_sizeWidth;
	private Double[] mr_sizeHeight;
	private Double[] mr_exPrice;
	private Double[] mr_tax;
	public String[] getMr_id() {
		return mr_id;
	}
	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}
	public String[] getMr_changed() {
		return mr_changed;
	}
	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}
	public Integer[] getMr_cnumber() {
		return mr_cnumber;
	}
	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}
	public Double[] getMr_grossWeight() {
		return mr_grossWeight;
	}
	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}
	public Double[] getMr_netWeight() {
		return mr_netWeight;
	}
	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}
	public Double[] getMr_sizeLength() {
		return mr_sizeLength;
	}
	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}
	public Double[] getMr_sizeWidth() {
		return mr_sizeWidth;
	}
	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}
	public Double[] getMr_sizeHeight() {
		return mr_sizeHeight;
	}
	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}
	public Double[] getMr_exPrice() {
		return mr_exPrice;
	}
	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}
	public Double[] getMr_tax() {
		return mr_tax;
	}
	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}
	@Override
	public String toString() {
		return "MREP [mr_id=" + Arrays.toString(mr_id) + ", mr_changed=" + Arrays.toString(mr_changed) + ", mr_cnumber="
				+ Arrays.toString(mr_cnumber) + ", mr_grossWeight=" + Arrays.toString(mr_grossWeight)
				+ ", mr_netWeight=" + Arrays.toString(mr_netWeight) + ", mr_sizeLength="
				+ Arrays.toString(mr_sizeLength) + ", mr_sizeWidth=" + Arrays.toString(mr_sizeWidth)
				+ ", mr_sizeHeight=" + Arrays.toString(mr_sizeHeight) + ", mr_exPrice=" + Arrays.toString(mr_exPrice)
				+ ", mr_tax=" + Arrays.toString(mr_tax) + "]";
	}

	
}