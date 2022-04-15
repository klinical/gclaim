package net.globemc.gclaim;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class Claim {

    private Chunk chunk;

    Claim(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return this.chunk;
    }

    /**
     * Check if the chunk used in this claim is at least half made up of usable land/coastline.
     * At least half of the chunk must be valid for this method to return true.
     * Ocean type blocks that are not solid at slightly below sea level will not be counted as valid.
     *
     * @return  whether this chunk is valid for claiming as a part of a territory
     */
    public boolean isValid() {
        int numValid = 256;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                Block block = this.chunk.getBlock(x, 59, z);
                Biome biome = block.getBiome();
                Material blockMaterial = block.getType();

                switch (biome) {
                    case DEEP_COLD_OCEAN:
                    case DEEP_LUKEWARM_OCEAN:
                    case DEEP_FROZEN_OCEAN:
                    case FROZEN_OCEAN:
                    case WARM_OCEAN:
                    case DEEP_OCEAN:
                    case COLD_OCEAN:
                    case OCEAN:
                    case LUKEWARM_OCEAN:
                        if (blockMaterial == Material.WATER) {
                            numValid--;
                        }
                }
            }
        }

        return ( numValid >= 128 );
    }
}
