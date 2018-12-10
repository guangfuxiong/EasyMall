package cn.tedu.domain;

public class Product {
	private String id;//商品id
	private String name;//商品名称
	private double price;//价格
	private String category;//分类
    private int pnum;//库存
	private String imgurl;//图片全名称（路径+文件名+后缀名）
	private String description;//商品的描述信息
	
	@Override
	public int hashCode() {
		return id==null?31:31+id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		//当前对象和obj在堆内存中地址相同
		if(this==obj){
			return true;
		}
		//当前对象和obj在堆内存中的地址不同
		//obj是否为Product产生的对象
		if(!(obj instanceof Product)){//obj不是Product产生的对象
			return false;
		}
		//obj是Product产生的对象
		Product other = (Product)obj;
		//两个id相同，则返回true
		if(id!=null&&id.equals(other.getId())){
			return true;
		}
		return false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
