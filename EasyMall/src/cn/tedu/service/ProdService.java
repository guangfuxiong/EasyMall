package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Product;
import cn.tedu.exception.MsgException;

public interface ProdService {
	/**查询全部商品的方法
	 * @return 全部商品的集合对象
	 */
	List<Product> findAll();
	/**修改商品的库存
	 * @param pid：商品id
	 * @param pnum:商品新的库存
	 * @return true:表示修改成功，false:表示修改失败
	 */
	boolean changePnum(String pid, int pnum);
	/**根据商品的id删除对应的商品
	 * @param pid：商品id
	 * @throws MsgException 如果删除失败，则抛出异常（并带有提示信息）；成功则不抛出异常
	 */
	void deleteProdById(String pid) throws MsgException;
	/**根据关键字查询符合条件所有商品
	 * @param name：商品名称关键字（模糊查询）
	 * @param category：商品分类关键字（模糊查询）
	 * @param minprice：价格区间的最小值
	 * @param maxprice：价格区间的最大值
	 * @return  符合条件的所有商品的集合
	 */
	List<Product> findAllByKey(String name, String category, Double minprice,
			Double maxprice);
	/**根本商品的id查询商品的详细信息
	 * @param pid商品id
	 * @return 该id对应的商品信息
	 */
	Product findProdById(String pid);
}
