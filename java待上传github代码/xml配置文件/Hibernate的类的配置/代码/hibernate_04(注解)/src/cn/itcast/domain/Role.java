package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// 角色 N
@Entity
@Table(name="sys_role")
public class Role 
{
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long role_id; // 主键
	@Column(name="role_name")
	private String role_name; // '角色名称',
	@Column(name="role_memo")
	private String role_memo; // '备注',
	
	
	// 配置多对多
	// 有user的集合
	/*targetEntity:对象的字节码文件对象
	mappedBy:自己在对方中的属性名
	*/
	@ManyToMany(targetEntity=User.class,mappedBy="roles")
	private Set<User> users=new HashSet<User>();
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_memo() {
		return role_memo;
	}
	public void setRole_memo(String role_memo) {
		this.role_memo = role_memo;
	}
	
	
}
