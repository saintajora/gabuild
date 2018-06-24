/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.square;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildRadial;
import com.geekagestudios.build.GABuildMod;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class BuildSquareVertZ extends BuildRadial {

	public BuildSquareVertZ(String[] args) {
		super(args);
	}
	
	public BuildSquareVertZ(BlockPos center, Integer rad) {
		super(center, rad);
	}
	
	public BuildSquareVertZ(BlockPos center, Integer rad, Integer thick) {
		super(center, rad, thick);
	}
	
	public BuildSquareVertZ(BlockPos center, Integer rad, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}
	
	public BuildSquareVertZ(BlockPos center, Integer rad, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		for(int z=-this.radius; z<=this.radius; z++) {
			for(int y=-this.radius; y<=this.radius; y++) {
				BlockPos p = new BlockPos(this.start.ix, this.start.iy+y, this.start.iz+z);
				BlockPos q = new BlockPos(this.start.ix, this.start.iy-y, this.start.iz-z);
				if(this.isOnBorder(y, z) || this.isOnInner(z, y)) {
					this.placeBlock(world, p, this.blocks[1], this.blockMeta[1]);
					this.placeBlock(world, q, this.blocks[1], this.blockMeta[1]);
				} else if((z>this.innerRadius || z<-this.innerRadius) || (y>this.innerRadius || y<-this.innerRadius)) {
					this.placeBlock(world, p, this.blocks[0], this.blockMeta[0]);
					this.placeBlock(world, q, this.blocks[0], this.blockMeta[0]);
				} else if(this.blocks.length >= 3) {
					this.placeBlock(world, p, this.blocks[2], this.blockMeta[2]);
					this.placeBlock(world, q, this.blocks[2], this.blockMeta[2]);
				} else {
					this.placeAirBlock(world, p);
					this.placeAirBlock(world, q);
				}
			}
		}
	}
	
	public Boolean isOnBorder(Integer y, Integer z) {
		Boolean boolY = y.equals(this.radius) || y.equals(-this.radius);
		Boolean boolX = z.equals(this.radius) || z.equals(-this.radius);
		return boolY || boolX;
	}
	
	public Boolean isOnInner(Integer z, Integer y) {
		Boolean boolZ = (z.equals(this.innerRadius) || z.equals(-this.innerRadius))
				&& (y <= this.innerRadius && y >= -this.innerRadius);
		Boolean boolY = (y.equals(this.innerRadius) || y.equals(-this.innerRadius))
				&& (z <= this.innerRadius && z >= -this.innerRadius);
		return boolZ || boolY;
	}
}
