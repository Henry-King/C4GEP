package data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface IHibernateDataContext {

	/**
	 * ADD方法
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
	 * 根据ID获取对象
	 */
	public abstract <T> T findById(Class<T> clazz, Serializable id);

	/**
	 * 根据对象进行查询
	 */
	public abstract <T> List<T> findByExample(Object instance);

	public abstract <T> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object value);
	
	/**
	 * 根据HQL语句查询对象
	 */
	public abstract <T> List<T> queryByHql(String hql);

	/**
	 * 获取全部
	 */
	public abstract <T> List<T> findAll(Class<T> clazz);

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @param pageIndex 页码
	 * @param pageSize	返回的条数
	 * @return
	 */
	public abstract <T> List<T> findAll(Class<T> clazz, int pageIndex,
			int pageSize);

	/**
	 * MERGE
	 */
	public abstract <T> T merge(Class<T> clazz, Object detachedInstance);

	/**
	 * 计数
	 */
	public abstract <T> int getCount(Class<T> clazz);

	public abstract void attachDirty(Object item);

	public abstract void attachClean(Object item);

	public abstract Session getSession();

	/**
	 * 是否在事务中
	 * @return true or false
	 */
	public abstract boolean IsInTransaction();

	/**
	 * 开启一个事务
	 */
	public abstract void BeginTransaction();

	/**
	 * 提交事务
	 */
	public abstract void Commit();

	/**
	 * 事务回滚
	 */
	public abstract void Rollback();

	/**
	 * 脏数据
	 * @return true or false
	 */
	public abstract boolean IsDirty();
	
	/**
	 * 多条件查询
	 * */
	public List<Object> searchByPropertys(String model,String[]propertyName,Object[] value,boolean rigor);
	
	/*多条件查询,可以使用Between条件,查询条件的值为空时自动除去该条件  
	* rigor为true时采用精确查询  
	*/ 
	public List<Object> searchByPropertysBetween(String model,String[]propertyName,Object[] value,boolean rigor,String BetweenProperty,Object[] valueObject);
}