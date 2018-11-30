package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.MailUtil;
import cn.itcast.utils.SysConstant;
import cn.itcast.utils.UtilFuns;

@Component("us")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dd;
	@Override
	public List<User> find(Specification<User> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<User> findPage(Specification<User> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(User entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			String uid=UUID.randomUUID().toString();
			System.out.println("user save uid="+uid);
			entity.setId(uid);
			entity.getUserinfo().setId(uid);
			Md5Hash p = new Md5Hash(SysConstant.DEFAULT_PASS, entity.getUserName(),2);
			entity.setPassword(p.toString());
			
			String email=entity.getUserinfo().getEmail();
			String subject=entity.getDept().getDeptName()+"欢迎你";
			String content="你想进入超级黄狗2部吗？"+entity.getUserName();
			
			try {
				MailUtil.sendMsg(email, subject, content);
				System.out.println("?????"+entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		User d1=dd.findOne(id);
		try {		
			if(d1!=null){
				System.out.println("d1="+d1);
				
				dd.delete(id);
			}
		} catch (Exception e) {
		}	
	}

	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			dd.delete(id);
		}
	}
//	@Override
//	public void dg_delete(String s) {
//		System.out.println("in s="+s);
//		List<User> ld = dd.findUserByPid(s);
//		for(User p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<User> findUserByUserNameLike(String s) {
		// TODO Auto-generated method stub
		return dd.findUserByUserNameLike(s);
	}



	
}
