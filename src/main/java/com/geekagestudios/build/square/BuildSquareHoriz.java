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

public class BuildSquareHoriz extends BuildRadial {
	
	public BuildSquareHoriz(String[] args) {
		super(args);
	}
	
	public BuildSquareHoriz(BlockPos center, Integer rad) {
		super(center, rad);
	}
	
	public BuildSquareHoriz(BlockPos center, Integer rad, Integer thick) {
		super(center, rad, thick);
	}
	
	public BuildSquareHoriz(BlockPos center, Integer rad, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}
	
	public BuildSquareHoriz(BlockPos center, Integer rad, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		for(int x=-this.radius; x<=this.radius; x++) {
			for(int z=-this.radius; z<=this.radius; z++) {
				BlockPos p = new BlockPos(this.start.ix+x, this.start.iy, this.start.iz+z);
				BlockPos q = new BlockPos(this.start.ix-x, this.start.iy, this.start.iz-z);
				if(this.isOnBorder(x, z) || this.isOnInner(x, z)) {
					this.placeBlock(world, p, this.blocks[1], this.blockMeta[1]);
					this.placeBlock(world, q, this.blocks[1], this.blockMeta[1]);
				} else if((x>this.innerRadius || x<-this.innerRadius) || (z>this.innerRadius || z<-this.innerRadius)) {
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
	
	public Boolean isOnBorder(Integer x, Integer z) {
		Boolean boolY = z.equals(this.radius) || z.equals(-this.radius);
		Boolean boolX = x.equals(this.radius) || x.equals(-this.radius);
		return boolY || boolX;
	}
	
	public Boolean isOnInner(Integer x, Integer z) {
		Boolean boolX = (x.equals(this.innerRadius) || x.equals(-this.innerRadius))
				&& (z <= this.innerRadius && z >= -this.innerRadius);
		Boolean boolZ = (z.equals(this.innerRadius) || z.equals(-this.innerRadius))
				&& (x <= this.innerRadius && x >= -this.innerRadius);
		return boolX || boolZ;
	}
}
