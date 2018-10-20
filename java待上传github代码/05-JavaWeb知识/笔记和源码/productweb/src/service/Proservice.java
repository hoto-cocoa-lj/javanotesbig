package service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import bean.Pagebean;
import bean.product;
import dao.Dao;

public class Proservice {
	static int onepage = 1;
	//
	// public Pagebean getall(int page) throws SQLException {
	// Pagebean pd = new Pagebean(page, onepage);
	// Dao dao = new Dao();
	// List<product> pl = dao.selects(pd.getIndex(), pd.getOnepage());
	// int co = (int) dao.count();
	// pd.setDatanum(co);
	// pd.setPagenum((int) Math.ceil(co * 1.0 / onepage));
	// System.out.println(pd.getDatanum() + " " + pd.getPagenum());
	// pd.setList(pl);
	// return pd;
	// }

	public Pagebean getall(int page, String... searchword) throws SQLException {
		Pagebean pd = new Pagebean(page, onepage);
		Dao dao = new Dao();
		List<product> pl = null;
		int co;
		if (searchword.length != 0) {
			System.out.println("searchword=" + Arrays.toString(searchword));
			co = (int) dao.count(searchword);
			pl = dao.selects(pd.getIndex(), pd.getOnepage(), searchword);
			System.out.println(pl);
		} else {
			pl = dao.selects(pd.getIndex(), pd.getOnepage());
			co = (int) dao.count();
		}

		pd.setDatanum(co);
		pd.setPagenum((int) Math.ceil(co * 1.0 / onepage));
		System.out.println(pd.getDatanum() + " " + pd.getPagenum());
		pd.setList(pl);
		return pd;
	}

	public String insert(product pro) throws SQLException {
		Dao dao = new Dao();
		String price = String.valueOf(pro.getPrice());
		// System.out.println("pro.getName()=" + pro.getName() +
		// dao.isnamein(pro.getName()));
		if (dao.isnamein(pro.getName())) {
			dao.update(price, pro.getDate(), pro.getImg(), pro.getName());
			return "修改成功";
		} else {
			UUID uid1 = UUID.randomUUID();
			String uid = uid1.toString().replace("-", "").toUpperCase();
			int i = dao.insert(uid, pro.getName(), price, pro.getDate(), pro.getImg());
			if (i == 0) {
				return "添加失败";
			} else {
				return "添加成功";
			}
		}
	}

	public int delete(String i) throws SQLException {
		// TODO Auto-generated method stub
		return new Dao().delete(i);
	}

}
