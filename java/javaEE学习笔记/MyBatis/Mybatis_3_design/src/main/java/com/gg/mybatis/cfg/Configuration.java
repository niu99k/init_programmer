package com.gg.mybatis.cfg;

import java.util.Map;

public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Map<String, Mapper> mappers;

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    /*
    此处使用追加的方式
     */
    public void setMappers(Map<String, Mapper> mappers) {
        if (this.mappers == null) {
            this.mappers = mappers;
        } else {
            this.mappers.putAll(mappers);
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
