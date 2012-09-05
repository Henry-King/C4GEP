package data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface IHibernateDataContext {

	/**
	 * ADD����
	 */
	public abstract void save(Object item);

	/**
	 * DELETE
	 */
	public abstract void delete(Object item);

	/**
	 * UPDATE
	 */
	public abstract void update(Object item);

	/**
	 * ����ID��ȡ����
	 */
	public abstract <T> T findById(Class<T> clazz, Serializable id);

	/**
	 * ���ݶ�����в�ѯ
	 */
	public abstract <T> List<T> findByExample(Object instance);

	public abstract <T> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object value);
	
	/**
	 * ����HQL����ѯ����
	 */
	public abstract <T> List<T> queryByHql(String hql);

	/**
	 * ��ȡȫ��
	 */
	public abstract <T> List<T> findAll(Class<T> clazz);

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @param pageIndex ҳ��
	 * @param pageSize	���ص�����
	 * @return
	 */
	public abstract <T> List<T> findAll(Class<T> clazz, int pageIndex,
			int pageSize);

	/**
	 * MERGE
	 */
	public abstract <T> T merge(Class<T> clazz, Object detachedInstance);

	/**
	 * ����
	 */
	public abstract <T> int getCount(Class<T> clazz);

	public abstract void attachDirty(Object item);

	public abstract void attachClean(Object item);

	public abstract Session getSession();

	/**
	 * �Ƿ���������
	 * @return true or false
	 */
	public abstract boolean IsInTransaction();

	/**
	 * ����һ������
	 */
	public abstract void BeginTransaction();

	/**
	 * �ύ����
	 */
	public abstract void Commit();

	/**
	 * ����ع�
	 */
	public abstract void Rollback();

	/**
	 * ������
	 * @return true or false
	 */
	public abstract boolean IsDirty();
	
	/**
	 * ��������ѯ
	 * */
	public List<Object> searchByPropertys(String model,String[]propertyName,Object[] value,boolean rigor);
	
	/*��������ѯ,����ʹ��Between����,��ѯ������ֵΪ��ʱ�Զ���ȥ������  
	* rigorΪtrueʱ���þ�ȷ��ѯ  
	*/ 
	public List<Object> searchByPropertysBetween(String model,String[]propertyName,Object[] value,boolean rigor,String BetweenProperty,Object[] valueObject);
}