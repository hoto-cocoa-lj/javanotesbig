package cn.itcast.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Controller;

@Table(name="DEPT_P")
@Entity
public class Dept implements Serializable{
	@Id
	@Column(name="DEPT_ID")
	@GeneratedValue(generator="fuckdao1")
	@GenericGenerator(name="fuckdao1",strategy="uuid")
	private String id;			//部门id
	
	@Column(name="DEPT_NAME")
	private String deptName;	//部门名称
	
	@ManyToOne(targetEntity=Dept.class)
	@JoinColumn(name="PARENT_ID",referencedColumnName="DEPT_ID")
	private Dept parent;		//父部门
	
	@Column(name="STATE")
	private Integer state;		//状态，0取消1运行
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		String x="没有";
		try {
			x=parent.getId();
		} catch (Exception e) {
		}
		return "Dept [id=" + id + ", deptName=" + deptName + ", parent=" + x + ", state=" + state + "]";
	}

	
	
}
