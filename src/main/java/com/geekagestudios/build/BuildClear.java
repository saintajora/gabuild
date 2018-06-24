/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BuildClear extends BuildCanvas {
	
	public BuildClear(String[] args) {
		this.parseCoords(args);
	}

	@Override
	public void build(ICommandSender sender) {
		World world = sender.getEntityWorld();
		
		Iterable<BlockPos> toDo = BlockPos.getAllInBox(this.start, this.end);
		for(BlockPos p : toDo) {
			this.placeAirBlock(world, p.ix, p.iy, p.iz);
		}
	}
}
