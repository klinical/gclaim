package net.globemc.gclaim;

import java.util.HashMap;
import java.util.Optional;

public class TerritoryMap {

    private HashMap<Integer, Territory> territoryMap;

    TerritoryMap() {
        this.territoryMap = new HashMap<>();
    }

    public void put(Territory territory) {
        this.territoryMap.put(territory.getId(), territory);
    }

    public int size() {
        return this.territoryMap.size();
    }

    public Optional<Territory> get(Integer key) {
        Territory territory = territoryMap.get(key);
        return Optional.ofNullable(territory);
    }
}
