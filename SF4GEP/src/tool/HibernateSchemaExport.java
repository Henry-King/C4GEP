package tool;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateSchemaExport {

	static Session session;
	static Configuration config = null;
	static Transaction tx = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * /** 根据映射文件创建数据库结构
		 */
		SessionFactory sessionFactory = null;
		try {
			config = new Configuration().configure(new File(
					"src/data/configure/hibernate.cfg.xml"));

			System.out.println("Creating tables...");

			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();

			SchemaExport schemaExport = new SchemaExport(config);
			schemaExport.setOutputFile("src\\data\\sql\\sql.sql");
			schemaExport.setFormat(true);
			schemaExport.setHaltOnError(true);
			schemaExport.create(true, true);

			System.out.println("Table created.");

			session.flush();

		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();

		}
	}

}
