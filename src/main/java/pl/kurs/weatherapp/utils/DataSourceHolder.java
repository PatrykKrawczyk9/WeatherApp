package pl.kurs.weatherapp.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public enum DataSourceHolder {
    INSTANCE;

    private final DataSource dataSource;

    DataSourceHolder() {
        this.dataSource = create();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    private DataSource create() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:mysql://localhost:3306/weather_app?useSSL=false&serverTimezone=CET");
        bds.setUsername("root");
        bds.setPassword("1234");
        bds.setMinIdle(5);
        bds.setMaxIdle(100);
        return bds;
    }
}
