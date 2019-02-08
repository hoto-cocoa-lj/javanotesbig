package cn.me.domain;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_INFO_P")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Userinfo extends BaseEntity {
	@Id
	@Column(name = "USER_INFO_ID")
	@GeneratedValue(generator = "system-assigned")
	@GenericGenerator(name = "system-assigned", strategy = "assigned")
	private String id;

	@Column(name = "NAME")
	private String name;// 真实姓名

.................

}
