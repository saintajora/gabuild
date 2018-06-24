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

public class BuildCylinderOnX extends BuildRadialStack {

	public BuildCylinderOnX(String[] args) {
		super(args);
	}
	
	public BuildCylinderOnX(BlockPos b, Integer rad, Integer len) {
		super(b, rad, len);
	}
	
	public BuildCylinderOnX(BlockPos b, Integer rad, Integer len, Integer thick) {
		super(b, rad, len, thick);
	}
	
	public BuildCylinderOnX(BlockPos b, Integer rad, Integer len, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}
	
	public BuildCylinderOnX(BlockPos b, Integer rad, Integer len, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Integer toX = this.start.ix+length;
		Integer from = toX > this.start.ix ? this.start.ix : toX;
		Integer to = toX < this.start.ix ? this.start.ix : toX;
		
		for(int x=from; x<=to; x++) {
			new BuildRingVertZ(new BlockPos(x, this.start.iy, this.start.iz), this.radius, this.thickness,
					this.blocks, this.blockMeta).build(sender);
		}
	}
}