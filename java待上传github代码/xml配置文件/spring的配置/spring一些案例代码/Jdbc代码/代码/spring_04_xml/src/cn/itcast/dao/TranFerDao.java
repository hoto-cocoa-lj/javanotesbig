package cn.itcast.dao;

public interface TranFerDao {

	void toMoney(String toUser, double money);

	void inMoney(String inUser, double money);

}
