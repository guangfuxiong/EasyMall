package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.domain.Product;

public interface ProdDao {
	/**查询全部的商品
	 * @return 全部商品的集合
	 */
	public List<Product> findAll();
	/**修改商品的库存
	 * @param pid：商品的id
	 * @param pnum：商品新的库存
	 * @return  影响的行数
	 */
	public int changePnum(String pid, int pnum);
	/**根据商品删除对应的商品
	 * @param pid：商品的id
	 * @return 影响的行数
	 */
	public int deleteProdById(String pid);
	/**根据关键字查询符合条件所有商品
	 * @param name：商品名称关键字（模糊查询）
	 * @param category：商品分类关键字（模糊查询）
	 * @param minprice：价格区间的最小值
	 * @param maxprice：价格区间的最大值
	 * @return  符合条件的所有商品的集合
	 */
	public List<Product> findAllByKey(String name, String category,
			Double minprice, Double maxprice);
	/**根据商品id查询商品的详细信息
	 * @param pid商品id
	 * @return 该id对应的商品信息
	 */
	public Product findProdById(String pid);
}
