/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.round;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildRadial;
import com.geekagestudios.build.GABuildMod;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class BuildRingVertX extends BuildRadial {

	public BuildRingVertX(String[] args) {
		super(args);
	}
	
	public BuildRingVertX(BlockPos center, Integer rad) {
		super(center, rad);
	}
	
	public BuildRingVertX(BlockPos center, Integer rad, Integer thick) {
		super(center, rad, thick);
	}
	
	public BuildRingVertX(BlockPos center, Integer rad, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}
	
	public BuildRingVertX(BlockPos center, Integer rad, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Integer rad = this.radius*this.radius;
		Integer inRad = this.innerRadius*this.innerRadius;
		
		for(int x=-radius; x<=radius; x++) {
			for(int y=-radius; y<=radius; y++) {
				Integer r = x*x + y*y;
				
				Double sr = Math.sqrt(r);
				
				if((sr <= this.radius && sr >= this.radius-1) || (sr >= this.innerRadius && sr <= this.innerRadius+1)) {
					this.placeBlock(world, this.start.ix+x, this.start.iy+y, this.start.iz, this.blocks[1], this.blockMeta[1]);
				} else if(r < rad && r > inRad) {
					this.placeBlock(world, this.start.ix+x, this.start.iy+y, this.start.iz, this.blocks[0], this.blockMeta[0]);
				} else if(r < inRad) {
					if(this.blocks.length >= 3) {
						this.placeBlock(world, this.start.ix+x, this.start.iy+y, this.start.iz, this.blocks[2], this.blockMeta[2]);
					} else {
						this.placeAirBlock(world, this.start.ix+x, this.start.iy+y, this.start.iz);
					}
				}
			}
		}
	}
}
