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

@Table(name="wt_c")
@Entity
public class weituo implements Serializable{
	@Id
	@Column(name="wt_ID")
	@GeneratedValue(generator="system-assigned")
	@GenericGenerator(name="system-assigned",strategy="assigned")
	private String id;			//部门id
	
	
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

	
	
}
