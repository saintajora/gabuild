/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.square;

import com.geekagestudios.build.BlockPos;
import com.geekagestudios.build.BuildCanvas;
import com.geekagestudios.build.GABuildMod;

import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BuildRectangular extends BuildCanvas {
	
	public BuildRectangular(String[] args) {
		this.parseCoords(args);
		
		if(args.length == 6) {
			this.parseBlocks(new Block[]{GABuildMod.defFillBlock, GABuildMod.defBorderBlock});
		} else if(args.length == 7) {
			this.parseBlocks(new String[]{args[6], args[6]});
		} else {
			this.parseBlocks(new String[]{args[6], args[7]});
		}
	}
	
	public BuildRectangular(BlockPos from, BlockPos to, String[] theBlocks) {
		this.start = from;
		this.end = to;
		
		if(theBlocks.length <= 0) {
			this.parseBlocks(new Block[]{GABuildMod.defFillBlock, GABuildMod.defBorderBlock});
		} else if(theBlocks.length == 1) {
			this.parseBlocks(new String[]{"minecraft:air", theBlocks[0]});
		} else {
			this.parseBlocks(theBlocks);
		}
	}
	
	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Iterable<BlockPos> toDo = BlockPos.getAllInBox(this.start, this.end);
		for(BlockPos p : toDo) {
			Integer i = this.isOnBorder(p) ? 1 : 0;
			this.placeBlock(world, p, this.blocks[i], this.blockMeta[i]);
		}
	}
}
