package cn.tedu.domain;

import java.util.Date;

/**
 * ������
 * @author guangfuxiong
 *
 */
public class Order {

	private String id;//����id
	private double money;//�������
	private String receiverinfo ;//�ջ�����Ϣ
	private int paystate;//֧��״̬��0Ϊ֧����1��֧��
	private Date ordertime;//��������ʱ��
	private int user_id;//�û�id����Ӧuser��������id
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