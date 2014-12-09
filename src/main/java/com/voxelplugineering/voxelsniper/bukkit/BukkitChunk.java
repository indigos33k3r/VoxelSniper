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
package com.voxelplugineering.voxelsniper.bukkit;

import org.bukkit.Chunk;
import org.bukkit.block.Block;

import com.google.common.base.Optional;
import com.voxelplugineering.voxelsniper.common.CommonBlock;
import com.voxelplugineering.voxelsniper.common.CommonChunk;
import com.voxelplugineering.voxelsniper.common.CommonLocation;
import com.voxelplugineering.voxelsniper.common.CommonMaterial;

/**
 * A bukkit wrapper for {@link CommonChunk}.
 */
public class BukkitChunk extends CommonChunk<Chunk>
{

    /**
     * Creates a new {@link BukkitChunk} wrapping the given bukkit {@link Chunk}.
     * 
     * @param chunk the chunk to wrap, cannot be null
     */
    public BukkitChunk(Chunk chunk, BukkitWorld world)
    {
        super(chunk, world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CommonBlock> getRelativeBlockAt(int x, int y, int z)
    {
        Block b = getThis().getBlock(x, y, z);
        CommonLocation l = new CommonLocation(this.getWorld(), b.getX(), b.getY(), b.getZ());
        Optional<?> m = this.getWorld().getMaterialRegistry().get(b.getType().name());
        if (!m.isPresent())
        {
            return Optional.absent();
        }
        return Optional.of(new CommonBlock(l, (CommonMaterial<?>) m.get()));
    }

}