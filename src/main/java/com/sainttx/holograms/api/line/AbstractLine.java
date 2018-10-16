package com.sainttx.holograms.api.line;

import com.sainttx.holograms.api.Hologram;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;

public abstract class AbstractLine implements HologramLine {

    private Hologram parent;
    private Location location;
    private String raw;

    public AbstractLine(Hologram parent, String raw) {
        Validate.notNull(parent, "Parent hologram cannot be null");
        Validate.notNull(raw, "Raw representation cannot be null");
        this.parent = parent;
        this.raw = raw;
        this.location = parent.getLocation();
    }

    @Override
    public Location getLocation() {
        return location.clone();
    }

    @Override
    public void setLocation(Location location) {
        this.location = location.clone();
    }

    @Override
    public double getHeight() {
        return 0d;
    }

    @Override
    public final Hologram getHologram() {
        return parent;
    }

    @Override
    public final String getRaw() {
        return raw;
    }

    protected void setRaw(String raw) {
        Validate.notNull(raw, "Raw representation cannot be null");
        this.raw = raw;
    }
}
