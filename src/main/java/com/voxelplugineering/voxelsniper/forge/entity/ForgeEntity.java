/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 The Voxel Plugineering Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.voxelplugineering.voxelsniper.forge.entity;

import java.util.UUID;

import com.voxelplugineering.voxelsniper.api.entity.EntityType;
import com.voxelplugineering.voxelsniper.api.world.Location;
import com.voxelplugineering.voxelsniper.api.world.World;
import com.voxelplugineering.voxelsniper.core.Gunsmith;
import com.voxelplugineering.voxelsniper.core.entity.AbstractEntity;
import com.voxelplugineering.voxelsniper.core.util.math.Vector3d;
import com.voxelplugineering.voxelsniper.core.world.CommonLocation;
import com.voxelplugineering.voxelsniper.forge.util.ForgeUtilities;
import com.voxelplugineering.voxelsniper.forge.world.ForgeWorld;

/**
 * A wrapper for a forge entity.
 */
public class ForgeEntity extends AbstractEntity<net.minecraft.entity.Entity>
{

    private final EntityType type;

    /**
     * Creates a new {@link ForgeEntity}.
     * 
     * @param entity The entity to wrap
     */
    public ForgeEntity(net.minecraft.entity.Entity entity)
    {
        super(entity);
        this.type = ForgeUtilities.getEntityType(entity.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld()
    {
        return Gunsmith.getWorldRegistry().getWorld(getThis().worldObj.getProviderName()).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return getThis().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType()
    {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocation()
    {
        return new CommonLocation(this.getWorld(), getThis().posX, getThis().posY, getThis().posZ);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation(Location loc)
    {
        if (loc.getWorld() instanceof ForgeWorld)
        {
            getThis().setWorld(((ForgeWorld) loc.getWorld()).getThis());
            getThis().setPositionAndUpdate(loc.getX(), loc.getY(), loc.getZ());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getUniqueId()
    {
        return getThis().getUniqueID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3d getRotation()
    {
        return new Vector3d(getThis().rotationYaw, getThis().rotationPitch, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotation(Vector3d rotation)
    {
        getThis().setRotationYawHead((float) rotation.getX());
        getThis().rotationPitch = (float) rotation.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove()
    {
        getThis().worldObj.removeEntity(getThis());
        return true;
    }

}