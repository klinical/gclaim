package net.globemc.gclaim;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Gclaim extends JavaPlugin {

    private TerritoryMap territoryMap = new TerritoryMap();
    private Database database;

    private File databaseConfigFile = new File(getDataFolder(), "database.yml");
    private Logger logger = getLogger();

    @Override
    public void onEnable() {
        if (!databaseConfigFile.exists())
            saveResource("database.yml", false);

        try {
            initDatabase();

            getCommand("generate-territory").setExecutor(new GenerateTerritoryCommand(this));
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Could not connect to db!");
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void initDatabase() throws SQLException {
        Database.loadFromConfig(databaseConfigFile);
        database = Database.getInstance();

        InputStream input = getClassLoader().getResourceAsStream("db-init.sql");

        try {
            String[] initStatements = new String(input.readAllBytes()).split(";");

            Connection conn = database.getConnection();
            for (String statement : initStatements) {
                PreparedStatement preparedStatement = conn.prepareStatement(statement);
                preparedStatement.execute();
            }
        } catch (IOException ex) {;
            getLogger().log(Level.WARNING, "Skipping db-init step as file could not be read or found.");
        }
    }

    @Override
    public void onDisable() {
    }

    public TerritoryMap getTerritoryMap() {
        return territoryMap;
    }
}
