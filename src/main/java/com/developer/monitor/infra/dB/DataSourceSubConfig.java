package com.developer.monitor.infra.dB;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="com.developer.monitor.infra.sms.mapper", sqlSessionFactoryRef = "subSqlSessionFactory")
public class DataSourceSubConfig {

    private final String SUB_DATASOURCE = "subDataSource";

    @Bean(SUB_DATASOURCE)
    @ConfigurationProperties(prefix="spring.test-b.datasource")
    public DataSource subDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory subSqlSessionFactory(@Qualifier(SUB_DATASOURCE) DataSource subDataSource) throws Exception{

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(subDataSource);

        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");
        bean.setMapperLocations(res);

//        // MyBatis Config Setting
//        // MyBatis 설정 파일
//        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
//        bean.setConfigLocation(myBatisConfig);

        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager subTransactionManager(@Qualifier(SUB_DATASOURCE) DataSource subDataSource) throws Exception{
        return new DataSourceTransactionManager(subDataSource);
    }
}
