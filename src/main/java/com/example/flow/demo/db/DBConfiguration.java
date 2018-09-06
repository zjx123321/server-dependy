package com.example.flow.demo.db;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring JavaConfig configuration class to setup a Spring container and infrastructure components.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.flow.demo.repositories")
@EnableTransactionManagement
public class DBConfiguration{

   @Bean(name = "getSessionFactory")
   public SessionFactory sessionFactory(@Qualifier(value = "neo4jConfiguration") org.neo4j.ogm.config.Configuration configuration) {
      // with domain entity base package(s)
      return new SessionFactory(configuration, "com.example.flow.demo.entity");
   }

   @Bean(name = "neo4jConfiguration")
   public org.neo4j.ogm.config.Configuration configuration() {
      ConfigurationSource properties = new ClasspathConfigurationSource("ogm.properties");
//      properties.properties().setProperty("","");
//      properties.properties().setProperty("","");
      org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
      return configuration;
   }



   @Bean
   public Neo4jTransactionManager transactionManager(@Qualifier(value = "getSessionFactory") SessionFactory sessionFactory) {
      return new Neo4jTransactionManager(sessionFactory);
   }
}