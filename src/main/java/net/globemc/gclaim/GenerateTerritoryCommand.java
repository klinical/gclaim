package net.globemc.gclaim;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenerateTerritoryCommand implements CommandExecutor {

    private final Gclaim plugin;

    GenerateTerritoryCommand(Gclaim plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Claim newClaim = new Claim(player.getChunk());

            TerritoryMap territoryMap = plugin.getTerritoryMap();

            if (newClaim.isValid()) {
                int newTerritoryId = territoryMap.size() + 1;
                Territory newTerritory = new Territory(newTerritoryId);

                try {
                    newTerritory.addClaim(newClaim);
                    territoryMap.put(newTerritory);
                } catch (SQLException ex) {
                    sender.sendMessage("Failed to save territory! DB error...");
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
