/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import java.util.Arrays;

import com.geekagestudios.build.BuildRadial;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;

public abstract class BuildRadialStack extends BuildRadial {

	public Integer length;
	
	
	public BuildRadialStack(String[] args) {
		super(Arrays.copyOfRange(args, 0, 4));
		this.length = Integer.parseInt(args[4]);
		
		if(args.length > 5) {
			this.thickness = Integer.parseInt(args[5]);
			if(this.thickness < 0) this.thickness = 0;
			this.innerRadius = this.radius - this.thickness-1;
			if(this.innerRadius < 0) this.innerRadius = 0;
		} else {
			this.thickness = 0;
			this.innerRadius = this.radius;
		}
		
		if(args.length == 7) {
			this.parseBlocks(args[6]);
		} else if(args.length == 8) {
			this.parseBlocks(new String[]{args[6], args[7]});
		} else if(args.length >= 9) {
			this.parseBlocks(new String[]{args[6], args[7], args[8]});
		} else {
			this.parseBlocks(new Block[]{GABuildMod.defFillBlock, GABuildMod.defBorderBlock});
		}
	}
	
	public BuildRadialStack(BlockPos b, Integer rad, Integer len) {
		super(b, rad);
		this.length = len;
	}
	
	public BuildRadialStack(BlockPos b, Integer rad, Integer len, Integer thick) {
		super(b, rad, thick);
		this.length = len;
	}
	
	public BuildRadialStack(BlockPos b, Integer rad, Integer len, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, thick, theBlocks, theBlockMeta);
		this.length = len;
	}
	
	public BuildRadialStack(BlockPos b, Integer rad, Integer len, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		super(b, rad, thick, theBlocks, theBlockMeta);
		this.length = len;
	}

	@Override
	public abstract void build(ICommandSender sender);
}
