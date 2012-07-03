package data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HibernateDataContext implements  IHibernateDataContext {
	
	 private static final Logger log = LoggerFactory.getLogger(HibernateDataContext.class);
	 private Session session;
	 private Transaction tran;
	 //private boolean IsInTransaction;
	 

//	public void setIsInTransaction(boolean isInTransaction) {
//		IsInTransaction = isInTransaction;
//	}

	public HibernateDataContext()
	 {
		 session=HibernateSessionFactory.getSession();
		 tran=null;
	 }
	 
	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#save(java.lang.Object)
	 */

	public void save(Object item) {
		// TODO Auto-generated method stub
		log.debug("saving item");
		if(item==null)
		{
			log.debug("item is null");
			throw(new NullPointerException() );
		}
		boolean tranFlag=false;
		if(!IsInTransaction())
		{
			this.BeginTransaction();
			tranFlag=true;
		}
		
		session.save(item);

		if(!IsInTransaction()||tranFlag)
		{
			try 
			{
				this.Commit();
				//session.flush();
				log.debug("save successful");
			}
			catch(RuntimeException re)
			{
				this.Rollback();
		        log.error("save faile", re);
		        throw re;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#delete(java.lang.Object)
	 */

	public void delete(Object item ) {
		// TODO Auto-generated method stub
		log.debug("deleting item");
		if(item==null)
		{
			log.debug("item is null");
			throw(new NullPointerException() );
		}
		boolean tranFlag=false;
		if(!IsInTransaction())
		{
			this.BeginTransaction();
			tranFlag=true;
		}

		session.delete(item);

		if(!IsInTransaction()||tranFlag)
		{
			try 
			{
				this.Commit();
				//session.flush();
				log.debug("delete successful");
			}
			catch(RuntimeException re)
			{
				this.Rollback();
				log.error("delete failed", re);
		        throw re;
			}
		}
	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#update(java.lang.Object)
	 */

	public void update(Object item)
	{
		//先清空一下Session
		session.clear();
		// TODO Auto-generated method stub
		log.debug("updateing item");
		if(item==null)
		{
			log.debug("item is null");
			throw(new NullPointerException() );
		}
		boolean tranFlag=false;
		if(!IsInTransaction())
		{
			this.BeginTransaction();
			tranFlag=true;
		}
		
		session.update(item);

		if(!IsInTransaction()||tranFlag)
		{
			try 
			{
				this.Commit();
				//session.flush();
				log.debug("update successful");
			}
			catch(RuntimeException re)
			{
				this.Rollback();
				log.error("update failed", re);
		        throw re;
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#findById(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")

	public<T> T findById(Class<T> clazz,Serializable id)
	{
		log.debug("getting Object instance with id: " + id);
		try
		{
			//T t=clazz.newInstance();
			return (T)session.get(clazz,id);
			//return t;
		}
		catch (RuntimeException re)
		{
			log.error("findById failed", re);
			throw re;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#findByExample(java.lang.Object)
	 */

	public<T> List<T> findByExample(Object instance) {
		log.debug("finding instance instance by example");
		try {
          @SuppressWarnings("unchecked")
		List<T> results = (List<T>)getSession().createCriteria(instance.getClass())
          				.add(Example.create(instance)).list();
                  //.createCriteria("Student")
                  //.add(Example.create(instance))

          log.debug("find by example successful, result size: " + results.size());
          return results;
      } catch (RuntimeException re) {
          log.error("find by example failed", re);
          throw re;
      }
		
	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#findByProperty(java.lang.Class, java.lang.String, java.lang.Object)
	 */

	public<T> List<T> findByProperty(Class<T> clazz,String propertyName, Object value) {
		log.debug("finding Student instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	    	  String queryString = "from "+clazz.getName()+" as model where model." 
	         						+ propertyName + "= ?";
	    	  System.out.println(queryString);
	         Query queryObject = getSession().createQuery(queryString);
			 queryObject.setParameter(0, value);
			 return (List<T>)queryObject.list();
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
	}


	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#findAll(java.lang.Class)
	 */
	
	public<T> List<T> findAll(Class<T> clazz) {
		log.debug("finding all object instances");
		try {
			String queryString = "from "+clazz.getName();
	         Query queryObject = getSession().createQuery(queryString);
			 return (List<T>)queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#findAll(java.lang.Class, int, int)
	 */
	public<T> List<T> findAll(Class<T> clazz,int pageIndex, int pageSize )
	{
		log.debug("finding all object instances");
		try {
			String queryString = "from "+clazz.getName();
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(pageIndex);
	         queryObject.setMaxResults(pageSize);
			 return (List<T>)queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/*
	 * 根据HQL语句查询对象
	 */
	public<T> List<T> queryByHql(String hql)
	{
		log.debug("query object by hql");
		try{			
			Query queryObject=getSession().createQuery(hql);
			return (List<T>)queryObject.list();				
		}catch (RuntimeException re) {
			log.error("query failed", re);
			throw re;
		}
			
	}

	
	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#merge(java.lang.Class, java.lang.Object)
	 */
	
	public<T> T merge(Class<T> clazz,Object detachedInstance) {
      log.debug("merging object instance");
    	  if(detachedInstance==null)
	  		{
	  			log.debug("item is null");
	  			throw(new NullPointerException() );
	  		}
	  		boolean tranFlag=false;
	  		if(!IsInTransaction())
	  		{
	  			this.BeginTransaction();
	  			tranFlag=true;
	  		}
          @SuppressWarnings("unchecked")
		T result = (T)session.merge(detachedInstance);          
         if(!IsInTransaction()||tranFlag)
         {
        	 try           			
        	 {          				
        		 this.Commit();         				
        		 //session.flush();         				
        		 log.debug("merge successful");         			
        	 }       			
        	 catch(RuntimeException re)          			
        	 {          				
        		 this.Rollback();          				
        		 log.error("merge failed", re);         		       
        		 throw re;		
        	 }
         }
		return result;
	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#getCount(java.lang.Class)
	 */
	public<T> int getCount(Class<T> clazz)
	{
		Criteria  criteria=session.createCriteria(clazz);
		criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);    
		criteria.list();
		 return (Integer)criteria.list().size();
	}
	
	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#attachDirty(java.lang.Object)
	 */
	
	public void attachDirty(Object item) {
		
		log.debug("attaching dirty object instance");
		if(item==null)
		{
			log.debug("item is null");
			throw(new NullPointerException() );
		}
		boolean tranFlag=false;
		if(!IsInTransaction())
		{
			this.BeginTransaction();
			tranFlag=true;
		}

		session.saveOrUpdate(item);

		if(!IsInTransaction()||tranFlag)
		{
			try 
			{
				this.Commit();
				//session.flush();
				log.debug("attach successful");
			}
			catch(RuntimeException re)
			{
				this.Rollback();
				log.error("attach failed", re);
		        throw re;
			}
		}

	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#attachClean(java.lang.Object)
	 */
	
	public void attachClean(Object item) {

		log.debug("attaching clean object instance");
		if(item==null)
		{
			log.debug("item is null");
			throw(new NullPointerException() );
		}
		boolean tranFlag=false;
		if(!IsInTransaction())
		{
			this.BeginTransaction();
			tranFlag=true;
		}

		session.lock(item, LockMode.NONE);

		if(!IsInTransaction()||tranFlag)
		{
			try 
			{
				this.Commit();
				//session.flush();
				log.debug("attach successful");
			}
			catch(RuntimeException re)
			{
				this.Rollback();
				log.error("attach failed", re);
		        throw re;
			}
		}


	}

	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#getSession()
	 */
	
	public Session getSession() {

		if(this.session!=null)
		{
			return this.session;
		}
		else
		{
			return HibernateSessionFactory.getSession();
		}
	}
	/* (non-Javadoc)
	 * @see dao.IHibernateDataContext#IsInTransaction()
	 */
	 public boolean IsInTransaction() {
			return tran != null && tran.isActive();
		}

	 /* (non-Javadoc)
	 * @see dao.IHibernateDataContext#BeginTransaction()
	 */
	 public void BeginTransaction() 
	 {
		 log.debug("Begin a transaction");
		 if(IsInTransaction())
		 {
			 log.debug("A transaction is already opened");
			 throw(new IllegalStateException ("A transaction is already opened") );
		 }
		 else
		 {
			 try
             {
                 tran = session.beginTransaction();
                 log.debug("Openning a transaction  successful");
             }
             catch(HibernateException ex)
             {
            	 log.error("Open a transaction  Error");
                 throw (ex);
             }
		 }
	 }
	 
	 /* (non-Javadoc)
	 * @see dao.IHibernateDataContext#Commit()
	 */
	 public void Commit() 
	 {
		 if(!IsInTransaction())
		 {
			 log.debug("Operation requires an active transaction");
			 throw(new IllegalStateException ("Operation requires an active transaction") );
		 }
		 else
		 {
			 try
             {
                 tran.commit();                
                 log.debug("Commit successful");
             }
             catch(HibernateException ex)
             {
            	 log.error("Commit a transaction  Error");
                 throw (ex);
             }
		 }
	 }
	 
	 /* (non-Javadoc)
	 * @see dao.IHibernateDataContext#Rollback()
	 */
	 public void Rollback()
	 {
		 if(!IsInTransaction())
		 {
			 log.debug("Operation requires an active transaction");
			 throw(new IllegalStateException ("Operation requires an active transaction") );
		 }
		 else
		 {
			 try
             {
                 tran.commit();                
                 log.debug("Commit successful");
             }
             catch(HibernateException ex)
             {
            	 log.error("Commit a transaction  Error");
                 throw (ex);
             }
		 }
	 }
	 
	 /* (non-Javadoc)
	 * @see dao.IHibernateDataContext#IsDirty()
	 */
	 public boolean IsDirty()
	 {
		 return session.isDirty();
	 }
	 
	//value[i]为第i个查询条件propertyName[i]的值          （本方法已通过测试）  
	 
		/*多条件查询,查询条件的值为空时自动除去该条件  
		* rigor为true时采用精确查询  
		*/ 
		public List searchByPropertys(String model,String[]propertyName,Object[] value,boolean rigor){    
		    StringBuffer sqlBuffer = new StringBuffer();  
		    String ralation=" like ";  
		    if(rigor){  
		     ralation=" = ";  
		    }  
		    sqlBuffer.append("from "+model+" as model\n");  
		    int len=propertyName.length;  
		    List list=new ArrayList();  
		    boolean first=true;  
		    for(int i=0;i< len;i++){  
		     if(value[i]!=null){  
		     if(first){      
		      sqlBuffer.append(" where "+ "model."+ propertyName[i] + ralation+" ?\n");      
		      list.add(value[i]);  
		      first=false;  
		     }else{      
		      sqlBuffer.append(" and "+ "model."+ propertyName[i] +ralation+ " ?\n");      
		      list.add(value[i]);  
		     }  
		    }  
		    }  
		    
		     try { 
		    	 Query queryObject = session.createQuery(sqlBuffer.toString());  
		             for(int i=0;i< list.size();i++){  
		             if(rigor){  
		              queryObject.setParameter(i, list.get(i));  
		             }else{  
		              queryObject.setParameter(i, "%"+list.get(i)+"%");  
		             }  
		             
		      }  
		            
		            list=queryObject.list();  
		            //closeSession(session);  
		      return list;  
		         } catch (RuntimeException re) {  
		            log.error("find by property name failed", re);  
		            throw re;  
		         }  
		 
		}
		
		/*多条件查询,可以使用Between条件,查询条件的值为空时自动除去该条件  
		* rigor为true时采用精确查询  
		*/ 
		public List searchByPropertysBetween(String model,String[]propertyName,Object[] value,boolean rigor,String BetweenProperty,Object[] valueObject){    
		    System.out.println("序列化"+value[0].toString());
			StringBuffer sqlBuffer = new StringBuffer();  
		    String ralation=" like ";  
		    if(rigor){  
		     ralation=" = ";  
		    }  
		    sqlBuffer.append("from "+model+" as model\n");  
		    int len=propertyName.length;  
		    List list=new ArrayList();  
		    boolean first=true;  
		   for(int i=0;i< len;i++){  
		     if(value[i]!=null){  
		     if(first){      
		      sqlBuffer.append(" where "+ "model."+ propertyName[i] + ralation+" ?\n");      
		      list.add(value[i]);  
		      first=false;  
		     }else{      
		      sqlBuffer.append(" and "+ "model."+ propertyName[i] +ralation+ " ?\n");      
		      list.add(value[i]);  
		     }  
		    }  
		    }  
		   System.out.println(valueObject[0].toString());
		   System.out.println(valueObject[1].toString());
		    sqlBuffer.append(" and model."+BetweenProperty +" between ? and ? \n");
		    list.add(valueObject[0]);
		    list.add(valueObject[1]);
		     try { 
		    	 Query queryObject = session.createQuery(sqlBuffer.toString());  
		             for(int i=0;i< list.size();i++){  
		             if(rigor){  
		              queryObject.setParameter(i, list.get(i));  
		             }else{  
		              queryObject.setParameter(i, "%"+list.get(i)+"%");  
		             }  
		             
		      }  
		       System.out.println(sqlBuffer.toString());     
		            list=queryObject.list();  
		            //closeSession(session);  
		      return list;  
		         } catch (RuntimeException re) {  
		            log.error("find by property name failed", re);  
		            throw re;  
		         }  
		 
		}
}
