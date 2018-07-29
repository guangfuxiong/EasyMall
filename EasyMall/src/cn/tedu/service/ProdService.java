package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Product;
import cn.tedu.exception.MsgException;

public interface ProdService {
	/**��ѯȫ����Ʒ�ķ���
	 * @return ȫ����Ʒ�ļ��϶���
	 */
	List<Product> findAll();
	/**�޸���Ʒ�Ŀ��
	 * @param pid����Ʒid
	 * @param pnum:��Ʒ�µĿ��
	 * @return true:��ʾ�޸ĳɹ���false:��ʾ�޸�ʧ��
	 */
	boolean changePnum(String pid, int pnum);
	/**������Ʒ��idɾ����Ӧ����Ʒ
	 * @param pid����Ʒid
	 * @throws MsgException ���ɾ��ʧ�ܣ����׳��쳣����������ʾ��Ϣ�����ɹ����׳��쳣
	 */
	void deleteProdById(String pid) throws MsgException;
	/**���ݹؼ��ֲ�ѯ��������������Ʒ
	 * @param name����Ʒ���ƹؼ��֣�ģ����ѯ��
	 * @param category����Ʒ����ؼ��֣�ģ����ѯ��
	 * @param minprice���۸��������Сֵ
	 * @param maxprice���۸���������ֵ
	 * @return  ����������������Ʒ�ļ���
	 */
	List<Product> findAllByKey(String name, String category, Double minprice,
			Double maxprice);
	/**������Ʒ��id��ѯ��Ʒ����ϸ��Ϣ
	 * @param pid��Ʒid
	 * @return ��id��Ӧ����Ʒ��Ϣ
	 */
	Product findProdById(String pid);
}
