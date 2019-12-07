package com.zhuo.musiccommuity.Configuration;

import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class MybatisConfiguration  {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.max-active}")
    private int maxActive;
    @Value("${spring.datasource.max-idle}")
    private int maxIdel;
    @Value("${spring.datasource.max-maxWait}")
    private long maxWait;
    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig;
        shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("user_info");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", DataBaseShardingAlgorithm.class.getName()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", TableShardingAlgorithm.class.getName()));
        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()));
    }
    /**
     * 设置表的node
     * @return
     */
    @Bean
    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("user_info");
        orderTableRuleConfig.setActualDataNodes("user_${0..1}.user_info_${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("user_id");
        return orderTableRuleConfig;
    }

    /**
     * 需要手动配置事务管理器
     *
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }




    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource shardingDataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(shardingDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));

        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage("com.zhuo.musiccommuity.XmlFile");
        return mapperScannerConfigurer;
    }
    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("user_0", createDataSource("user_0"));
        result.put("user_1", createDataSource("user_1"));
        result.put("music",createDataSource("music"));
        return result;
    }

    public DataSource createDataSource(final String dataSourceName){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName+"?useUnicode=true&characterEncoding=UTF-8"));
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMaxConnLifetimeMillis(0);
        dataSource.setMaxIdle(8);
        dataSource.setMaxWaitMillis(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

}
