/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Vector3d;

import com.google.common.collect.AbstractIterator;

public class BlockPos extends Vector3d {

	public Integer ix;
	public Integer iy;
	public Integer iz;
	
	
	public BlockPos(Integer x, Integer y, Integer z) {
		super(x, y, z);
		this.ix = (int)this.x;
		this.iy = (int)this.y;
		this.iz = (int)this.z;
	}
	
	public BlockPos(Integer[] coord) {
		super(coord[0], coord[1], coord[2]);
		this.ix = coord[0];
		this.iy = coord[1];
		this.iz = coord[2];
	}
	
	public static BlockPos lowerCoord(BlockPos start, BlockPos end) {
		Integer x1 = start.ix <= end.ix ? start.ix : end.ix;
		Integer y1 = start.iy <= end.iy ? start.iy : end.iy;
		Integer z1 = start.iz <= end.iz ? start.iz : end.iz;
		return new BlockPos(x1, y1, z1);
	}
	
	public static BlockPos lowerCoord(int fromx, int fromy, int fromz, int tox, int toy, int toz) {
		Integer x1 = fromx <= tox ? fromx : tox;
		Integer y1 = fromy <= toy ? fromy : toy;
		Integer z1 = fromz <= toz ? fromz : toz;
		return new BlockPos(x1, y1, z1);
	}
	
	public static BlockPos lowerCoord(Integer[] args) {
		Integer x1 = args[0] <= args[3] ? args[0] : args[3];
		Integer y1 = args[1] <= args[4] ? args[1] : args[4];
		Integer z1 = args[2] <= args[5] ? args[2] : args[5];
		return new BlockPos(x1, y1, z1);
	}
	
	public static BlockPos upperCoord(BlockPos start, BlockPos end) {
		Integer x2 = start.ix > end.ix ? start.ix : end.ix;
		Integer y2 = start.iy > end.iy ? start.iy : end.iy;
		Integer z2 = start.iz > end.iz ? start.iz : end.iz;
		return new BlockPos(x2, y2, z2);
	}
	
	public static BlockPos upperCoord(int fromx, int fromy, int fromz, int tox, int toy, int toz) {
		Integer x2 = fromx > tox ? fromx : tox;
		Integer y2 = fromy > toy ? fromy : toy;
		Integer z2 = fromz > toz ? fromz : toz;
		return new BlockPos(x2, y2, z2);
	}
	
	public static BlockPos upperCoord(Integer[] args) {
		Integer x2 = args[0] <= args[3] ? args[0] : args[3];
		Integer y2 = args[1] <= args[4] ? args[1] : args[4];
		Integer z2 = args[2] <= args[5] ? args[2] : args[5];
		return new BlockPos(x2, y2, z2);
	}
	
	public boolean equals(BlockPos bp) {
		return this.ix == bp.ix && this.iy == bp.iy && this.iz == bp.iz;
	}
	
	public boolean isBetween(BlockPos a, BlockPos b) {
		BlockPos min = this.lowerCoord(a, b);
		BlockPos max = this.upperCoord(a, b);
		return this.ix >= min.ix && this.ix <= max.ix && this.iy >= min.iy && this.iy <= max.iy && this.iz >= min.iz
				&& this.iz <= max.iz;
	}
	
	public BlockPos addBP(BlockPos toAdd) {
		return new BlockPos(this.ix+toAdd.ix, this.iy+toAdd.iy, this.iz+toAdd.iz);
	}
	
	public static Iterable<BlockPos> getAllInBox(BlockPos start, BlockPos end) {
		
		BlockPos from = lowerCoord(start, end);
		BlockPos to = upperCoord(start, end);
		
		return getAllInBox(from.ix, from.iy, from.iz, to.ix, to.iy, to.iz);
	}
	
    /**
     * Create an Iterable that returns all positions in the box specified by the coordinates. <strong>Coordinates must
     * be in order</strong>; e.g. x1 <= x2.
     *  
     * In situations where it is usable, prefer {@link #getAllInBoxMutable(BlockPos, BlockPos}) instead as it has better
     * performance (fewer allocations)
     *  
     * @see #getAllInBox(BlockPos, BlockPos)
     * @see #getAllInBoxMutable(BlockPos, BlockPos)
     * @see #mutablesBetween(int, int, int, int, int, int)
     *  
     * @param x1 The lower x coordinate
     * @param y1 The lower y coordinate
     * @param z1 The lower z coordinate
     * @param x2 The upper x coordinate
     * @param y2 The upper y coordinate
     * @param z2 The upper z coordinate
     */
    public static Iterable<BlockPos> getAllInBox(final int x1, final int y1, final int z1, final int x2, final int y2, final int z2)
    {
        return new Iterable<BlockPos>()
        {
            public Iterator<BlockPos> iterator()
            {
                return new AbstractIterator<BlockPos>()
                {
                    private boolean first = true;
                    private int lastPosX;
                    private int lastPosY;
                    private int lastPosZ;
                    protected BlockPos computeNext()
                    {
                        if (this.first)
                        {
                            this.first = false;
                            this.lastPosX = x1;
                            this.lastPosY = y1;
                            this.lastPosZ = z1;
                            return new BlockPos(x1, y1, z1);
                        }
                        else if (this.lastPosX == x2 && this.lastPosY == y2 && this.lastPosZ == z2)
                        {
                            return (BlockPos)this.endOfData();
                        }
                        else
                        {
                            if (this.lastPosX < x2)
                            {
                                ++this.lastPosX;
                            }
                            else if (this.lastPosY < y2)
                            {
                                this.lastPosX = x1;
                                ++this.lastPosY;
                            }
                            else if (this.lastPosZ < z2)
                            {
                                this.lastPosX = x1;
                                this.lastPosY = y1;
                                ++this.lastPosZ;
                            }

                            return new BlockPos(this.lastPosX, this.lastPosY, this.lastPosZ);
                        }
                    }
                };
            }
        };
    }
}
