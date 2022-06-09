package com.gouthamkrishnakv.serendipity;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gouthamkrishnakv.serendipity.models.User;
import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@MapperScan("com.gouthamkrishnakv.serendipity")
@MappedTypes({User.class})
public class MyBatisConfig {
    @Bean(name="dataSource")
    public DataSource dataSource() {
        //  DataSourceBuilder Class
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        //  Tell dataSourceBuilder that MySQL JDBC Driver is what's required.
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        //  Tell dataSourceBuilder that the dataSource Type if MySQL
        dataSourceBuilder.type(MysqlDataSource.class);
        //  Set up URL for dataSourceBuilder
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/serendipity");
        //  Set Username
        dataSourceBuilder.username("serendipity");
        //  Set Password
        dataSourceBuilder.password("serendipity");
        //  Export the built DataSource Object
        return dataSourceBuilder.build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        //  Create the Data-source
        DataSource dataSource = this.dataSource();
        //  Create JDBC TransactionFactory
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // Create new Dev-environment data-source
        Environment environment = new Environment("development", transactionFactory, dataSource);
        // Set up Configuration with Dev-Environment
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        //  Export Created Configuration
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
