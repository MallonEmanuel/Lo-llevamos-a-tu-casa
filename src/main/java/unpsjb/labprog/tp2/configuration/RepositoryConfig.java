package unpsjb.labprog.tp2.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author Juan Manuel Cortez
 *
 */
@Configuration
@EnableTransactionManagement
public class RepositoryConfig implements TransactionManagementConfigurer {
	// ${jdbc.driverClassName}
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;
	
	@Value("${hibernate.cache.use_second_level_cache}")
	private String hibernateCacheUseSecondLevelCache;
	
	@Value("${hibernate.jdbc.batch_size}")
	private int hibernateJdbcBatchSize; 
	
	@Bean()
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
//		HibernateTemplate hibernateTemplate = HibernateSingleton.getHibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}
	
//	TODO Refactorizar Singleton!!
	private LocalSessionFactoryBean localSessionFactoryBean;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		if(localSessionFactoryBean == null){
			localSessionFactoryBean = new LocalSessionFactoryBean();
			localSessionFactoryBean.setDataSource(getDataSource());
			localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
			localSessionFactoryBean.setPackagesToScan(new String[] { "unpsjb.labprog.tp2.springmvc" });	
		}
		return localSessionFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
		return transactionManager;
	}

	@Value("${hibernate.connection.release_mode}")
	private String hibernateConnectiOnReleaseMode;
	
	@Bean
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShowSql);
		properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		properties.put("hibernate.cache.use_second_level_cache", hibernateCacheUseSecondLevelCache);
		properties.put("hibernate.jdbc.batch_size", hibernateJdbcBatchSize);
//		properties.put("hibernate.enable_lazy_load_no_trans", true);
//		properties.put("hibernate.connection.release_mode",hibernateConnectiOnReleaseMode);
//		properties.put("hibernate.transaction.auto_close_session", true);
		return properties;
	}

	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}

}