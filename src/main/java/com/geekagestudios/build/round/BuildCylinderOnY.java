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

public class BuildCylinderOnY extends BuildRadialStack {

	public BuildCylinderOnY(String[] args) {
		super(args);
	}
	
	public BuildCylinderOnY(BlockPos b, Integer rad, Integer len) {
		super(b, rad, len);
	}
	
	public BuildCylinderOnY(BlockPos b, Integer rad, Integer len, Integer thick) {
		super(b, rad, len, thick);
	}
	
	public BuildCylinderOnY(BlockPos b, Integer rad, Integer len, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}
	
	public BuildCylinderOnY(BlockPos b, Integer rad, Integer len, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Integer toY = this.start.iy+length;
		Integer from = toY > this.start.iy ? this.start.iy : toY;
		Integer to = toY < this.start.iy ? this.start.iy : toY;
		
		for(int y=from; y<=to; y++) {
			new BuildRingHoriz(new BlockPos(this.start.ix, y, this.start.iz), this.radius, this.thickness,
					this.blocks, this.blockMeta).build(sender);
		}
	}
}
