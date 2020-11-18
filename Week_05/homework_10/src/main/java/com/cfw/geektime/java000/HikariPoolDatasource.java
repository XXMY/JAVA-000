package com.cfw.geektime.java000;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.util.StringUtils;

public class HikariPoolDatasource {

    public HikariDataSource dataSource(String url,String username,String password) {
        DataSourceProperties properties = createDatasourceProperties(url, username, password);
        HikariDataSource dataSource = (HikariDataSource) createDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }

        return dataSource;
    }

    private DataSourceProperties createDatasourceProperties(String url,String username,String password) {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(url);
        dataSourceProperties.setUsername(username);
        dataSourceProperties.setPassword(password);

        return dataSourceProperties;
    }

    private Object createDataSource(DataSourceProperties properties, Class<HikariDataSource> hikariDataSourceClass) {
        return properties.initializeDataSourceBuilder().type(hikariDataSourceClass).build();
    }

}
