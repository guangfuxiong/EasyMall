package cn.tedu.domain;
/**
 *订单条目类 
 * @author guangfuxiong
 *
 */
public class OrderItem {

	private String order_id;//订单id，对应Order表的id
	private String prod_id;//产品id，对应Product表的id
	private int buyNum;//购买数量
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
