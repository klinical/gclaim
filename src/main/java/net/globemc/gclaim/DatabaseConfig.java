package net.globemc.gclaim;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DatabaseConfig extends YamlConfiguration {

    private YamlConfiguration yamlConfiguration;
    private String host;
    private String db;
    private String user;
    private String password;
    private int port;
    
    DatabaseConfig(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        host = config.getString("host");
        port = config.getInt("port");
        db = config.getString("db");
        user = config.getString("user");
        password = config.getString("password");
        yamlConfiguration = config;
    }

    public String getHost() {
        return host;
    }

    public String getDb() {
        return db;
    }

    public String getUser() {
        return user;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public YamlConfiguration getYamlConfiguration() {
        return this.yamlConfiguration;
    }
}
