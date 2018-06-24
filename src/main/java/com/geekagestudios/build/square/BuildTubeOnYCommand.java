/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.square;

import com.geekagestudios.build.round.BuildCylinderOnZ;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class BuildTubeOnYCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "build-tubey";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/build-tubey [cX] [cY] [cZ] [radius] [length] <thickness> <material> <border-material>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args.length >= 5) {
			new BuildTubeOnY(args).build(sender);
		} else {
			throw new CommandException("Invalid arguments.");
		}
	}
}
