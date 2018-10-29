package cc.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cst_customer")
public class Customer {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cust_id;// '客户编号(主键)',
	@Column
	private String cust_name;// '客户名称(公司名称)',
	@Column
	private String cust_phone;// '固定电话',
	@Column
	private String cust_mobile;// '移动电话',

	 @ManyToOne(targetEntity=BaseDict.class)
	 @JoinColumn(name="cust_source",referencedColumnName="dict_id")
	private BaseDict cust_source;// '客户信息来源',

	 @ManyToOne(targetEntity=BaseDict.class)
	 @JoinColumn(name="cust_industry",referencedColumnName="dict_id")
	private BaseDict cust_industry;// '客户所属行业',

	 @ManyToOne(targetEntity=BaseDict.class)
	 @JoinColumn(name="cust_level",referencedColumnName="dict_id")
	private BaseDict cust_level;// '客户级别',
	 
	 @OneToMany(targetEntity=Linkman.class, mappedBy="customer")
	 private List<Linkman> linkmans;
	 

	public List<Linkman> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List<Linkman> linkmans) {
		this.linkmans = linkmans;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public BaseDict getCust_source() {
		return cust_source;
	}

	public void setCust_source(BaseDict cust_source) {
		this.cust_source = cust_source;
	}

	public BaseDict getCust_industry() {
		return cust_industry;
	}

	public void setCust_industry(BaseDict cust_industry) {
		this.cust_industry = cust_industry;
	}

	public BaseDict getCust_level() {
		return cust_level;
	}

	public void setCust_level(BaseDict cust_level) {
		this.cust_level = cust_level;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile +"]";
	}
	 


}
