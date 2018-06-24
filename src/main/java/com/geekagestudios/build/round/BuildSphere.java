/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.round;

import java.util.Arrays;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildRadial;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class BuildSphere extends BuildRadial {
	
	public BuildSphere(String[] args) {
		super(args);
	}
	
	public BuildSphere(BlockPos center, Integer rad) {
		super(center, rad);
	}
	
	public BuildSphere(BlockPos center, Integer rad, Integer thick) {
		super(center, rad, thick);
	}
	
	public BuildSphere(BlockPos center, Integer rad, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}
	
	public BuildSphere(BlockPos center, Integer rad, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(center, rad, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Integer rad = this.radius*this.radius;
		Integer inRad = this.innerRadius*this.innerRadius;
		
		for(int z=-this.radius; z<=this.radius; z++) {
			for(int y=-this.radius; y<=this.radius; y++) {
				for(int x=-this.radius; x<=this.radius; x++) {
					Integer r = y*y + z*z + x*x;
					
					if(r.equals(rad) || r.equals(inRad) || r.equals(rad-1) || r.equals(inRad+1)) {
						this.placeBlock(world, this.start.ix+x, this.start.iy+y, this.start.iz+z, this.blocks[1], this.blockMeta[1]);
					} else if(r < rad && r > inRad) {
						this.placeBlock(world, this.start.ix+x, this.start.iy+y, this.start.iz+z, this.blocks[0], this.blockMeta[0]);
					}
				}
			}
		}
	}

}
