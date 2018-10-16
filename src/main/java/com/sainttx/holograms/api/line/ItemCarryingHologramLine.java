package com.sainttx.holograms.api.line;

import org.bukkit.inventory.ItemStack;

public interface ItemCarryingHologramLine extends HologramLine {

    /**
     * Returns the current displayed item by this line.
     *
     * @return the current item
     */
    ItemStack getItem();

    /**
     * Sets the item for this hologram line to display.
     *
     * @param item the new item
     */
    void setItem(ItemStack item);
}
