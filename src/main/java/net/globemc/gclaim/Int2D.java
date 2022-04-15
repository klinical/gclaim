package net.globemc.gclaim;

import java.util.Objects;

public record Int2D(int x, int z) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Int2D other = (Int2D) obj;
        if (this.x == other.x && this.z == other.z)
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}
