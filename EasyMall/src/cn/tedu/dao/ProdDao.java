package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.domain.Product;

public interface ProdDao {
	/**��ѯȫ������Ʒ
	 * @return ȫ����Ʒ�ļ���
	 */
	public List<Product> findAll();
	/**�޸���Ʒ�Ŀ��
	 * @param pid����Ʒ��id
	 * @param pnum����Ʒ�µĿ��
	 * @return  Ӱ�������
	 */
	public int changePnum(String pid, int pnum);
	/**������Ʒɾ����Ӧ����Ʒ
	 * @param pid����Ʒ��id
	 * @return Ӱ�������
	 */
	public int deleteProdById(String pid);
	/**���ݹؼ��ֲ�ѯ��������������Ʒ
	 * @param name����Ʒ���ƹؼ��֣�ģ����ѯ��
	 * @param category����Ʒ����ؼ��֣�ģ����ѯ��
	 * @param minprice���۸��������Сֵ
	 * @param maxprice���۸���������ֵ
	 * @return  ����������������Ʒ�ļ���
	 */
	public List<Product> findAllByKey(String name, String category,
			Double minprice, Double maxprice);
	/**������Ʒid��ѯ��Ʒ����ϸ��Ϣ
	 * @param pid��Ʒid
	 * @return ��id��Ӧ����Ʒ��Ϣ
	 */
	public Product findProdById(String pid);
}
