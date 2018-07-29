package cn.tedu.domain;

import java.util.Date;

/**
 * 订单类
 * @author guangfuxiong
 *
 */
public class Order {

	private String id;//订单id
	private double money;//订单金额
	private String receiverinfo ;//收货人信息
	private int paystate;//支付状态，0为支付，1已支付
	private Date ordertime;//订单创建时间
	private int user_id;//用户id，对应user表的主键id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date date) {
		this.ordertime = date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
