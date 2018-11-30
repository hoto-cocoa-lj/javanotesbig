package cn.itcast.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Controller;

@Table(name="Packing_list_c")
@Entity
public class PackingList implements Serializable{
	@Id
	@Column(name="Packing_list_ID")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;			//部门id
	
	@Column(name="seller")
	private String seller;	//部门名称
	@Column(name="buyer")
	private String buyer;	//部门名称	
	@Column(name="invoice_No")
	private String invoiceNo;	//部门名称
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="invoice_Date")
	private Date invoiceDate;	//部门名称
	@Column(name="marks")
	private String marks;	//部门名称
	@Column(name="descriptions")
	private String descriptions;	//部门名称
	@Column(name="export_ids")
	private String exportIds;		//状态，0取消1运行
	@Column(name="export_Nos")
	private  String exportNos;		//状态，0取消1运行
	@Column(name="STATE")
	private Integer state;		//状态，0取消1运行
	@Column(name="CREATE_BY")
	protected String createBy;//创建者的id
	@Column(name="CREATE_DEPT")
	protected String createDept;//创建者所在部门的id
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createTime;//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public  String getExportIds() {
		return exportIds;
	}
	public void setExportIds(String exportIds) {
		this.exportIds = exportIds;
	}
	public String getExportNos() {
		return exportNos;
	}
	public void setExportNos(String exportNos) {
		this.exportNos = exportNos;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "PackingList [id=" + id + ", seller=" + seller + ", buyer=" + buyer + ", invoiceNo=" + invoiceNo
				+ ", invoiceDate=" + invoiceDate + ", marks=" + marks + ", descriptions=" + descriptions
				+ ", exportIds=" + exportIds + ", exportNos=" + exportNos + ", state=" + state + ", createBy="
				+ createBy + ", createDept=" + createDept + ", createTime=" + createTime + "]";
	}
	
	

	
	
}
