CREATE TABLE IF NOT EXISTS territory
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    owner VARCHAR(16),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS territoryChunk
(
    chunk_key FLOAT NOT NULL,
    territory_id MEDIUMINT NOT NULL,
    FOREIGN KEY (territory_id)
        REFERENCES territory(id)
        ON DELETE CASCADE
);