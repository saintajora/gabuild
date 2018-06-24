/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import java.util.Arrays;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildCanvas;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public abstract class BuildRadial extends BuildCanvas {

	public Integer radius;
	public Integer innerRadius;
	public Integer thickness;
	
	public Boolean isLit = false;
	public Integer lightSpacing = 0;
	
	
	public BuildRadial(String[] args) {
		this.parseCoords(Arrays.copyOfRange(args, 0, 3));
		
		this.radius = Integer.parseInt(args[3]);
		if(args.length > 4) {
			this.thickness = Integer.parseInt(args[4]);
			if(this.thickness < 0) this.thickness = 0;
			this.innerRadius = this.radius - this.thickness+1;
			if(this.innerRadius < 0) this.innerRadius = 0;
		} else {
			this.innerRadius = this.radius;
		}
		
		if(args.length == 6) this.parseBlocks(args[5]);
		else if(args.length == 7) this.parseBlocks(new String[]{args[5], args[6]});
		else if(args.length >= 8) this.parseBlocks(new String[]{args[5], args[6], args[7]});
		else this.parseBlocks(GABuildMod.defFillBlock);
		
		if(args.length >= 9) this.setLightSpacing(Integer.parseInt(args[8]));
	}
	
	public BuildRadial(BlockPos center, Integer rad) {
		this.start = center;
		this.radius = rad;
		this.thickness = 1;
		this.innerRadius = this.radius - this.thickness;
		this.parseBlocks(new Block[]{GABuildMod.defFillBlock, GABuildMod.defBorderBlock});
	}
	
	public BuildRadial(BlockPos center, Integer rad, Integer thick) {
		this.start = center;
		this.radius = rad;
		this.thickness = thick;
		this.innerRadius = this.radius - this.thickness;
		this.parseBlocks(new Block[]{GABuildMod.defFillBlock, GABuildMod.defBorderBlock});
	}
	
	public BuildRadial(BlockPos center, Integer rad, Integer thick, Block[] theBlocks, Integer[] theBlockMeta) {
		this.start = center;
		this.radius = rad;
		this.thickness = thick;
		this.innerRadius = this.radius - this.thickness+1;
		this.blocks = theBlocks;
		this.blockMeta = theBlockMeta;
	}
	
	public BuildRadial(BlockPos center, Integer rad, Integer thick, String[] theBlocks, Integer[] theBlockMeta) {
		this.start = center;
		this.radius = rad;
		this.thickness = thick;
		this.innerRadius = this.radius - this.thickness+1;
		this.parseBlocks(theBlocks);
		this.blockMeta = theBlockMeta;
	}
	
	public abstract void build(ICommandSender sender);
	
	@Override
	public Boolean placeBlock(World w, int bx, int by, int bz, Block toPlace, Integer blockMeta) {
		if(this.isLit && (bx == this.start.ix || bx % this.lightSpacing == 0) 
				&& (by == this.start.iy || by % this.lightSpacing == 0)
				&& (bz == this.start.iz || bz % this.lightSpacing == 0)) {
			return super.placeBlock(w, bx, by, bz, GABuildMod.defLightBlock, blockMeta);
		} else {
			return super.placeBlock(w, bx, by, bz, toPlace, blockMeta);
		}
	}
	
	public void setLightSpacing(Integer spacing) {
		this.lightSpacing = spacing > 0 ? spacing : 0;
		this.isLit = this.lightSpacing > 0;
	}
	
	public void isLit(Boolean lit) {
		this.isLit = lit;
	}
}
