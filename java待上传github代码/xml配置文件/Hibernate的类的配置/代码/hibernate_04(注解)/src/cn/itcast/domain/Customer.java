package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//所有的注解都在persistence下
@Entity // 当前类是持久化类
@Table(name="cst_customers") //当前持久化类和哪个表做映射
// 1的一方
public class Customer 
{    @Id //当前的属性是oid属性
	 @Column(name="cust_id") // 和表的哪个字段做映射
	 @GeneratedValue(strategy=GenerationType.IDENTITY) //oid的增长策略
	 private Long cust_id;// '客户编号(主键)',

	 // 其它属性和表中的字段映射
	 @Column(name="cust_name") 
	 private String cust_name;// '客户名称(公司名称)',
	 @Column(name="cust_source") 
	 private String cust_source;// '客户信息来源',
	 @Column(name="cust_industry") 
	 private String cust_industry;//'客户所属行业',
	 @Column(name="cust_level") 
	 private String cust_level;// '客户级别',
	 @Column(name="cust_address") 
	 private String cust_address;// '客户联系地址',
	 @Column(name="cust_phone") 
	 private String cust_phone;// '客户联系电话',
	 
	 
	 // 配置1对多的关系
	 // targetEntity: 对方的字节码文件类型
	 // mappedBy:自己在对方中的属性名    出现哪一方 意味着哪一方不会去维护外键了
	 	/*cascade
	 		  CascadeType.All 即使级联保存 又是级联删除         save-update,delete
	 		  CascadeType.PERSIST 级联保存		 	    save-update
	 		  CascadeType.REMOVE 级联删除				delete
	 		*/
	 			
	 @OneToMany(targetEntity=Linkman.class, mappedBy="customer",cascade=CascadeType.ALL)
	 private Set<Linkman> linkmans=new HashSet<Linkman>();
	 
	 
	 
	public Set<Linkman> getLinkmans() {
		return linkmans;
	}
	public void setLinkmans(Set<Linkman> linkmans) {
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
	public String getCust_source() {
		return cust_source;
	}
	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}
	public String getCust_industry() {
		return cust_industry;
	}
	public void setCust_industry(String cust_industry) {
		this.cust_industry = cust_industry;
	}
	public String getCust_level() {
		return cust_level;
	}
	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_source=" + cust_source
				+ ", cust_industry=" + cust_industry + ", cust_level=" + cust_level + ", cust_address=" + cust_address
				+ ", cust_phone=" + cust_phone + "]";
	}
	 
	 
}
