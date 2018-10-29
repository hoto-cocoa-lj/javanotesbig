package cc.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "base_dict")
public class BaseDict{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long dict_id;// '数据字典id(主键)',
	@Column
	private String dict_type_code;// '数据字典类别代码',
	@Column
	private String dict_type_name;//'数据字典类别名称',
	@Column
	private String dict_item_name;//'数据字典项目名称',
	@Column
	private String dict_item_code;// '数据字典项目(可为空)',
	@Column
	private Integer dict_sort;// '排序字段',
	@Column
	private String dict_enable;//'1:使用 0:停用',
	@Column
	private String dict_memo;// '备注',
	
	 @OneToMany(targetEntity=Customer.class, mappedBy="cust_source")
	 
	 private Set<Customer> cs_source=new HashSet<Customer>();

	public Long getDict_id() {
		return dict_id;
	}

	public void setDict_id(Long dict_id) {
		this.dict_id = dict_id;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public String getDict_type_name() {
		return dict_type_name;
	}

	public void setDict_type_name(String dict_type_name) {
		this.dict_type_name = dict_type_name;
	}

	public String getDict_item_name() {
		return dict_item_name;
	}

	public void setDict_item_name(String dict_item_name) {
		this.dict_item_name = dict_item_name;
	}

	public String getDict_item_code() {
		return dict_item_code;
	}

	public void setDict_item_code(String dict_item_code) {
		this.dict_item_code = dict_item_code;
	}

	public Integer getDict_sort() {
		return dict_sort;
	}

	public void setDict_sort(Integer dict_sort) {
		this.dict_sort = dict_sort;
	}

	public String getDict_enable() {
		return dict_enable;
	}

	public void setDict_enable(String dict_enable) {
		this.dict_enable = dict_enable;
	}

	public String getDict_memo() {
		return dict_memo;
	}

	public void setDict_memo(String dict_memo) {
		this.dict_memo = dict_memo;
	}

	public Set<Customer> getCs_source() {
		return cs_source;
	}

	public void setCs_source(Set<Customer> cs_source) {
		this.cs_source = cs_source;
	}

	@Override
	public String toString() {
		return "BaseDict [dict_id=" + dict_id + ", dict_type_code=" + dict_type_code + ", dict_type_name="
				+ dict_type_name + ", dict_item_name=" + dict_item_name + ", dict_item_code=" + dict_item_code
				+ ", dict_sort=" + dict_sort + ", dict_enable=" + dict_enable + ", dict_memo=" + dict_memo
				;
	}
	 


}
