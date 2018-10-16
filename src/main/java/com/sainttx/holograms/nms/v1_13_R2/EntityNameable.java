package com.sainttx.holograms.nms.v1_13_R2;

import com.sainttx.holograms.api.entity.Nameable;
import com.sainttx.holograms.api.line.HologramLine;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;

import javax.annotation.Nullable;

public class EntityNameable extends EntityArmorStand implements Nameable {

    private boolean lockTick;
    private HologramLine parentPiece;
    private String text;

    public EntityNameable(World world, HologramLine parentPiece) {
        super(world);
        super.a(new NullBoundingBox()); // Forces the bounding box
        setInvisible(true);
        setSmall(true);
        setArms(false);
        setNoGravity(true);
        setBasePlate(true);
        setMarker(true);
        this.parentPiece = parentPiece;
        setLockTick(true);
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        // Do not save NBT.
    }

    @Override
    public boolean c(NBTTagCompound nbttagcompound) {
        // Do not save NBT.
        return false;
    }

    @Override
    public boolean d(NBTTagCompound nbttagcompound) {
        // Do not save NBT.
        return false;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        // Do not save NBT.
        return new NBTTagCompound();
    }


    @Override
    public boolean isInvulnerable(DamageSource source) {
        /*
         * The field Entity.invulnerable is private.
         * It's only used while saving NBTTags, but since the entity would be killed
         * on chunk unload, we prefer to override isInvulnerable().
         */
        return true;
    }

    @Nullable
    @Override
    public IChatBaseComponent getCustomName() {
        return super.getCustomName();
    }

    @Override
    public void setCustomName(@Nullable IChatBaseComponent ichatbasecomponent) {
        if (ichatbasecomponent == null || ichatbasecomponent.getString().isEmpty()) {
            super.setCustomName(IChatBaseComponent.ChatSerializer.b(""));
            super.setCustomNameVisible(false);
        } else {
            super.setCustomName(ichatbasecomponent);
            super.setCustomNameVisible(true);
        }
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        // Locks the custom name.
    }

    @Override
    public EnumInteractionResult a(EntityHuman entityhuman, Vec3D vec3d, EnumHand enumhand) {
        // Prevent stand being equipped
        return EnumInteractionResult.FAIL;
    }

    @Override
    public boolean c(int i, ItemStack item) {
        // Prevent stand being equipped
        return false;
    }

    @Override
    public void a(AxisAlignedBB boundingBox) {
        // Do not change it!
    }

    @Override
    public int getId() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length > 2 && elements[2] != null && elements[2].getFileName().equals("EntityTrackerEntry.java") && elements[2].getLineNumber() > 137 && elements[2].getLineNumber() < 147) {
            return -1;
        }

        return super.getId();
    }

    @Override
    public void tick() {
        if (!lockTick) {
            super.playStepSound();
        }
    }

    @Override
    public void a(SoundEffect soundeffect, float f, float f1) {
        // Remove sounds.
    }

    public void setLockTick(boolean lock) {
        lockTick = lock;
    }

    @Override
    public void remove() {
        die();
    }

    @Override
    public void die() {
        setLockTick(false);
        super.die();
    }

    @Override
    public CraftEntity getBukkitEntity() {
        if (super.bukkitEntity == null) {
            this.bukkitEntity = new CraftNameable(this.world.getServer(), this);
        }
        return this.bukkitEntity;
    }

    @Override
    public void setPosition(double x, double y, double z) {
        super.setPosition(x, y, z);

        // Send a packet near to update the position.
        PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(this);
        this.world.players.stream()
                .filter(p -> p instanceof EntityPlayer)
                .map(p -> (EntityPlayer) p)
                .forEach(p -> {
                    double distanceSquared = Math.pow(p.locX - this.locX, 2) + Math.pow(p.locZ - this.locZ, 2);
                    if (distanceSquared < 8192 && p.playerConnection != null) {
                        p.playerConnection.sendPacket(teleportPacket);
                    }
                });
    }

    @Override
    public HologramLine getHologramLine() {
        return parentPiece;
    }

    @Override
    public String getName() {
        return text;
    }

    @Override
    public void setName(String text) {
        this.text = text;
        setCustomName(IChatBaseComponent.ChatSerializer.b(text));
    }
}
