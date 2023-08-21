package com.developer.monitor.infra.dB;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(value={"com.developer.monitor.domain.admin.mapper",
                    "com.developer.monitor.domain.erpServer.mapper",
                    "com.developer.monitor.domain.etcServer.mapper",
                    "com.developer.monitor.domain.gwServer.mapper"})
public class DataSourceMainConfig {
    private final String MAIN_DATASOURCE = "mainDataSource";

    @Primary
    @Bean(MAIN_DATASOURCE)
    @ConfigurationProperties(prefix="spring.test-a.datasource")
    public DataSource mainDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public SqlSessionFactory mainSessionFactory(DataSource mainDataSource) throws Exception{

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mainDataSource);

        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");
        bean.setMapperLocations(res);

//        // MyBatis Config Setting
//        // MyBatis 설정 파일
//        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
//        bean.setConfigLocation(myBatisConfig);

        return bean.getObject();
    }

    @Primary
    @Bean
    public DataSourceTransactionManager mainTransactionManager(DataSource mainDataSource){
        return new DataSourceTransactionManager(mainDataSource);
    }

}
