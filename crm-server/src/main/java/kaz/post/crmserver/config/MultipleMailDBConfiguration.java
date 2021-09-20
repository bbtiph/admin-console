package kaz.post.crmserver.config;

import com.zaxxer.hikari.HikariDataSource;
import kaz.post.crmserver.entity.OrganizationEntity;
import kaz.post.crmserver.entity.ReportTransactionEntity;
import kaz.post.crmserver.entity.UserEntity;
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
@EnableJpaRepositories(basePackages = "kaz.post.crmserver.repositories.mail",
		entityManagerFactoryRef = "crmServerMailEntityManagerFactory",
		transactionManagerRef = "crmServerMailTransactionManager"
)
public class MultipleMailDBConfiguration {
	@Bean(name = "crmServerMailDataSourceProperties")
	@ConfigurationProperties("spring.mail-app.datasource")
	@Primary
	public DataSourceProperties corpClientDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "crmServerMailDataSource")
	@ConfigurationProperties("spring.mail-app.datasource.configuration")
	@Primary
	public DataSource corpClientDataSource() {
		return corpClientDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "crmServerMailEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean crmServerMailEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(corpClientDataSource())
				.packages(UserEntity.class)
				.packages(ReportTransactionEntity.class)
				.build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager crmServerMailTransactionManager(
			final @Qualifier("crmServerMailEntityManagerFactory") LocalContainerEntityManagerFactoryBean crmServerMailEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(crmServerMailEntityManagerFactory.getObject()));
	}
}