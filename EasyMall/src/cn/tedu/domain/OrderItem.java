package cn.tedu.domain;
/**
 *������Ŀ�� 
 * @author guangfuxiong
 *
 */
public class OrderItem {

	private String order_id;//����id����ӦOrder���id
	private String prod_id;//��Ʒid����ӦProduct���id
	private int buyNum;//��������
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	
}
