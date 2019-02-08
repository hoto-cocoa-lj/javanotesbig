package cn.me.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_P")
@DynamicInsert(true) // oralce可以字段用默认值
@DynamicUpdate(true)
public class User extends BaseEntity {
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(generator = "system-assigned")
	@GenericGenerator(name = "system-assigned", strategy = "assigned")
	private String id;// 编号


	/*详细测试后发现@JoinColumn的name属性始终是本表的外键名，多对多时是中间表的外键名；1对多时是配置在多表的类，是多表里的外键名；1对1时配置在哪个类就是该类对应的表外键名；前三种情况都能在表里查到外键。如果使用1对1且是主键对主键，表里没有外键，但是修改依旧有效。比如类A有类B的引用，在类A配置@JoinColumn(name = "表B的id")和级联保存，a.setB(b)，保存a时要先保存b或者b已经存在(a和b的id必须一样，如果b不存在就保存a会保存，不知道是不是因为没有外键所以级联无效)，使用a.getB().setBname("..")的情况下保存a会修改b在表里的数据，此时依旧没有外键关系。这说明hibernamte是根据@JoinColumn的name属性进行多表查询修改的*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private Userinfo userinfo;// 一对一 用户与扩展信息

.................


}
