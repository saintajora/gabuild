/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.round;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildRadialStack;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class BuildCylinderOnZ extends BuildRadialStack {

	public BuildCylinderOnZ(String[] args) {
		super(args);
	}
	
	public BuildCylinderOnZ(BlockPos b, Integer rad, Integer len) {
		super(b, rad, len);
	}
	
	public BuildCylinderOnZ(BlockPos b, Integer rad, Integer len, Integer thick) {
		super(b, rad, len, thick);
	}
	
	public BuildCylinderOnZ(BlockPos b, Integer rad, Integer len, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}
	
	public BuildCylinderOnZ(BlockPos b, Integer rad, Integer len, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Integer toZ = this.start.iz+length;
		Integer from = toZ > this.start.iz ? this.start.iz : toZ;
		Integer to = toZ < this.start.iz ? this.start.iz : toZ;
		
		for(int z=from; z<=to; z++) {
			new BuildRingVertX(new BlockPos(this.start.ix, this.start.iy, z), this.radius, this.thickness,
					this.blocks, this.blockMeta).build(sender);
		}
	}
}
