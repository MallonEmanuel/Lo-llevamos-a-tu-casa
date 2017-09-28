package unpsjb.labprog.tp2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
public class SessionFactorySingleton {

	private static LocalSessionFactoryBean localSessionFactoryBean;
	
	@Bean
	public static LocalSessionFactoryBean getSessionFactory(RepositoryConfig repo) {
		if(localSessionFactoryBean == null){
			localSessionFactoryBean = new LocalSessionFactoryBean();
			localSessionFactoryBean.setDataSource(repo.getDataSource());
			localSessionFactoryBean.setHibernateProperties(repo.getHibernateProperties());
			localSessionFactoryBean.setPackagesToScan(new String[] { "unpsjb.labprog.tp2.springmvc" });	
		}
		return localSessionFactoryBean;
	}
}
