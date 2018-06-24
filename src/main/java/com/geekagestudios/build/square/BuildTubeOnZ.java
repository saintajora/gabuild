/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.square;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildRadialStack;
import com.geekagestudios.build.round.BuildRingVertX;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class BuildTubeOnZ extends BuildRadialStack {

	public BuildTubeOnZ(String[] args) {
		super(args);
	}
	
	public BuildTubeOnZ(BlockPos b, Integer rad, Integer len) {
		super(b, rad, len);
	}
	
	public BuildTubeOnZ(BlockPos b, Integer rad, Integer len, Integer thick) {
		super(b, rad, len, thick);
	}
	
	public BuildTubeOnZ(BlockPos b, Integer rad, Integer len, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}
	
	public BuildTubeOnZ(BlockPos b, Integer rad, Integer len, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, len, thick, theBlocks, theBlockMeta);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Integer toZ = this.start.iz+length;
		Integer from = toZ > this.start.iz ? this.start.iz : toZ;
		Integer to = toZ < this.start.iz ? this.start.iz : toZ;
		
		for(int z=from; z<=to; z++) {
			new BuildSquareVertX(new BlockPos(this.start.ix, this.start.iy, z), this.radius, this.thickness,
					this.blocks, this.blockMeta).build(sender);
		}
	}
}
