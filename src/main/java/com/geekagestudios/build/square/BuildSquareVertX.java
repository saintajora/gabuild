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

public class BuildSquareVertX extends BuildRadial {

	public BuildSquareVertX(String[] args) {
		super(args);
	}
	
	public BuildSquareVertX(BlockPos center, Integer rad) {
		super(center, rad);
	}
	
	public BuildSquareVertX(BlockPos center, Integer rad, Integer thick) {
		super(center, rad, thick);
	}
	
	public BuildSquareVertX(BlockPos center, Integer rad, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}
	
	public BuildSquareVertX(BlockPos center, Integer rad, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		for(int y=-this.radius; y<=this.radius; y++) {
			for(int x=-this.radius; x<=this.radius; x++) {
				BlockPos p = new BlockPos(this.start.ix+x, this.start.iy+y, this.start.iz);
				BlockPos q = new BlockPos(this.start.ix-x, this.start.iy-y, this.start.iz);
				if(this.isOnBorder(y, x) || this.isOnInner(y, x)) {
					this.placeBlock(world, p, this.blocks[1], this.blockMeta[1]);
					this.placeBlock(world, q, this.blocks[1], this.blockMeta[1]);
				} else if((y>this.innerRadius || y<-this.innerRadius) || (x>this.innerRadius || x<-this.innerRadius)) {
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
	
	public Boolean isOnBorder(Integer y, Integer x) {
		Boolean boolY = y.equals(this.radius) || y.equals(-this.radius);
		Boolean boolX = x.equals(this.radius) || x.equals(-this.radius);
		return boolY || boolX;
	}
	
	public Boolean isOnInner(Integer y, Integer x) {
		Boolean boolY = (y.equals(this.innerRadius) || y.equals(-this.innerRadius))
				&& (x <= this.innerRadius && x >= -this.innerRadius);
		Boolean boolX = (x.equals(this.innerRadius) || x.equals(-this.innerRadius))
				&& (y <= this.innerRadius && y >= -this.innerRadius);
		return boolY || boolX;
	}
}
