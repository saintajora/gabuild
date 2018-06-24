/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;


public abstract class BuildCanvas {

	public BlockPos start;
	public BlockPos end;
	public Block[] blocks;
	public Integer[] blockMeta;
	
	
	public void parseCoords(String[] args) {
		Integer[] coords = this.parseInts(args);
		this.parseCoords(coords);
	}
	
	public abstract void build(ICommandSender sender);
	
	public static Integer[] parseInts(String[] args) {
		int length = args.length > 6 ? 6 : args.length;
		Integer[] coords = new Integer[length];
		for(int i=0; i<length; i++) {
			coords[i] = Integer.parseInt(args[i]);
		}
		
		return coords;
	}
	
	public void parseCoords(Integer[] coords) {
		this.start = new BlockPos(coords[0], coords[1], coords[2]);
		
		if(coords.length >= 6) {
			this.end = new BlockPos(coords[3], coords[4], coords[5]);
		}
	}
	
	public void parseCoords(int fromx, int fromy, int fromz, int tox, int toy, int toz) {
		this.start = new BlockPos(fromx, fromy, fromz);
		this.end = new BlockPos(tox, toy, toz);
	}
	
	public void parseBlocks(String[] theBlocks) {
		this.blocks = new Block[theBlocks.length];
		this.blockMeta = new Integer[theBlocks.length];
		for(int i=0; i<this.blocks.length; i++) {
			Block b = Block.getBlockFromName(theBlocks[i]);
			if(b == null) {
				GABuildMod.logger.debug("Null error: Block not found");
			} else {
				this.blocks[i] = b;
				this.blockMeta[i] = 0;
			}
		}
	}
	
	public void parseBlocks(String theBlock) {
		Block b = Block.getBlockFromName(theBlock);
		if(b == null) {
			GABuildMod.logger.debug("Null error: Block not found");
		} else {
			this.blocks = new Block[] {b, b};
			this.blockMeta = new Integer[] {0, 0};
		}
	}
	
	public void parseBlocks(Block[] theBlocks) {
		this.blocks = theBlocks;
		this.blockMeta = new Integer[] {0, 0};
	}
	
	public void parseBlocks(Block theBlock) {
		this.blocks = new Block[]{theBlock, theBlock};
		this.blockMeta = new Integer[] {0, 0};
	}
	
	public void setBlocks(Block[] theBlocks, Integer[] theBlockMeta) {
		this.blocks = theBlocks;
		this.blockMeta = theBlockMeta;
	}
	
	public void setBlockMeta(Integer[] args) {
		if(args.length == this.blocks.length) {
			this.blockMeta = args;
		}
	}
	
	public Boolean isOnBorder(BlockPos p) {
		return this.isOnBorder(p.ix, p.iy, p.iz);
	}
	
	public Boolean isOnBorder(BlockPos p, Integer offset) {
		return this.isOnBorder(p.ix, p.iy, p.iz, offset);
	}
	
	public Boolean isOnBorder(Integer x, Integer y, Integer z) {
		Boolean boolX = this.start.ix.equals(this.end.ix) || this.start.ix.equals(x) || this.end.ix.equals(x);
		Boolean boolY = this.start.iy.equals(this.end.iy) || this.start.iy.equals(y) || this.end.iy.equals(y);
		Boolean boolZ = this.start.iz.equals(this.end.iz) || this.start.iz.equals(z) || this.end.iz.equals(z);
		
		return (boolX && boolY) || (boolX && boolZ) || (boolY && boolZ);
	}
	
	public Boolean isOnBorder(Integer x, Integer y, Integer z, Integer offset) {
		Boolean boolX = this.start.ix.equals(this.end.ix) || x.equals(this.start.ix+offset) || x.equals(this.end.ix-offset);
		Boolean boolY = this.start.iy.equals(this.end.iy) || y.equals(this.start.iy+offset) || y.equals(this.end.iy-offset);
		Boolean boolZ = this.start.iz.equals(this.end.iz) || z.equals(this.start.iz+offset) || z.equals(this.end.iz-offset);
		
		return (boolX && boolY) || (boolX && boolZ) || (boolY && boolZ);
	}
	
	public Boolean isOnSide(BlockPos p) {
		Boolean boolX = this.start.ix.equals(p.ix) || this.end.ix.equals(p.ix);
		Boolean boolY = this.start.iy.equals(p.iy) || this.end.iy.equals(p.iy);
		Boolean boolZ = this.start.iz.equals(p.iz) || this.end.iz.equals(p.iz);
		
		return boolX || boolY || boolZ;
	}
	
	public Boolean placeBlock(World w, BlockPos b, Block toPlace, Integer blockMeta) {
		return this.placeBlock(w, b.ix, b.iy, b.iz, toPlace, blockMeta);
	}
	
	public Boolean placeBlock(World w, int bx, int by, int bz, Block toPlace, Integer blockMeta) {
		Block oldBlock = w.getBlock(bx, by, bz);
		Integer oldBlockMeta = w.getBlockMetadata(bx, by, bz);
		
		if(oldBlock.equals(toPlace) && oldBlockMeta.equals(blockMeta)) {
			return true;
		} else {
			return w.setBlock(bx, by, bz, toPlace, blockMeta, 2);
		}
	}
	
	public Boolean placeAirBlock(World w, BlockPos b) {
		return this.placeAirBlock(w, b.ix, b.iy, b.iz);
	}
	
	public Boolean placeAirBlock(World w, int bx, int by, int bz) {
		Block oldBlock = w.getBlock(bx, by, bz);
		
		if(oldBlock.getMaterial() == Material.air) {
			return true;
		} else {
			return w.setBlockToAir(bx, by, bz);
		}
	}
}
