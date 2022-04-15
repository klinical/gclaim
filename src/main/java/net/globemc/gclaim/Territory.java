package net.globemc.gclaim;

import org.bukkit.Chunk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Territory {

    private int id;
    private HashMap<Long, Chunk> ownedChunks;

    Territory(int id) {
        this.ownedChunks = new HashMap<>();
        this.id = id;
    }

    public void addClaim(Claim claim) throws SQLException {
        Chunk chunk = claim.getChunk();
        // Already owned
        if (ownedChunks.get(chunk.getChunkKey()) != null)
            return;

        Connection conn = Database.getInstance().getConnection();
        ownedChunks.put(chunk.getChunkKey(), chunk);
    }

    public void saveTerritory(Territory territory) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        conn.setAutoCommit(false);
        PreparedStatement insertTerritoryStatement = conn.prepareStatement("""
                INSERT INTO territory(id) VALUES ( ? )""");
        PreparedStatement insertTerritoryChunkStatement = conn.prepareStatement("""
                INSERT INTO territoryChunk VALUES ( ?, ? )""");


        insertTerritoryStatement.setInt(1, territory.getId());
//        insertTerritoryChunkStatement.setInt(1, territory.getOwnedChunks()[0]);
//        statement.execute();
    }

    public HashMap<Long, Chunk> getOwnedChunks() {
        return ownedChunks;
    }

    public Chunk getOwnedChunk(Long chunkId) {
        return this.ownedChunks.get(chunkId);
    }

    public int getId() {
        return this.id;
    }
}
