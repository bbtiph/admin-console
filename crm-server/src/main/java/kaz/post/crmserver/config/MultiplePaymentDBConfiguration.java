package kaz.post.crmserver.config;

import com.zaxxer.hikari.HikariDataSource;
import kaz.post.crmserver.entity.OrganizationEntity;
import kaz.post.crmserver.entity.payment.OfflineOperator;
import kaz.post.crmserver.entity.payment.Operators;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "kaz.post.crmserver.repositories.payment",
		entityManagerFactoryRef = "crmServerPaymentEntityManagerFactory",
		transactionManagerRef = "crmServerPaymentTransactionManager"
)
public class MultiplePaymentDBConfiguration {
	@Bean(name = "crmServerPaymentDataSourceProperties")
	@ConfigurationProperties("spring.payment.datasource")
//	@Primary
	public DataSourceProperties corpClientDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "crmServerPaymentDataSource")
	@ConfigurationProperties("spring.payment.datasource.configuration")
//	@Primary
	public DataSource corpClientDataSource() {
		return corpClientDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

//	@Primary
	@Bean(name = "crmServerPaymentEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean crmServerPaymentEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(corpClientDataSource())
				.packages(Operators.class)
				.packages(OfflineOperator.class)
				.build();
	}

//	@Primary
	@Bean
	public PlatformTransactionManager crmServerPaymentTransactionManager(
			final @Qualifier("crmServerPaymentEntityManagerFactory") LocalContainerEntityManagerFactoryBean crmServerPaymentEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(crmServerPaymentEntityManagerFactory.getObject()));
	}
}