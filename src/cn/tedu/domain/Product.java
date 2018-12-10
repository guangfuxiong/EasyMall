package cn.tedu.domain;

public class Product {
	private String id;//��Ʒid
	private String name;//��Ʒ����
	private double price;//�۸�
	private String category;//����
    private int pnum;//���
	private String imgurl;//ͼƬȫ���ƣ�·��+�ļ���+��׺����
	private String description;//��Ʒ��������Ϣ
	
	@Override
	public int hashCode() {
		return id==null?31:31+id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		//��ǰ�����obj�ڶ��ڴ��е�ַ��ͬ
		if(this==obj){
			return true;
		}
		//��ǰ�����obj�ڶ��ڴ��еĵ�ַ��ͬ
		//obj�Ƿ�ΪProduct�����Ķ���
		if(!(obj instanceof Product)){//obj����Product�����Ķ���
			return false;
		}
		//obj��Product�����Ķ���
		Product other = (Product)obj;
		//����id��ͬ���򷵻�true
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
