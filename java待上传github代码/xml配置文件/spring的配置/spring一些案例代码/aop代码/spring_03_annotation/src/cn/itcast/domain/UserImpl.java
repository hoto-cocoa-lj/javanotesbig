package cn.itcast.domain;

// 带接口的--为了演示jdk动态代理
public class UserImpl implements User
{

	@Override
	public void save() {
		System.out.println("普通的保存方法...");
		
	}

	@Override
	public void delete() {
		System.out.println("普通的删除方法...");
		
	}

	@Override
	public void update() {
		System.out.println("普通的修改方法...");
		
	}

	@Override
	public void find() {
		System.out.println("普通的查询方法...");
		
	}

}
