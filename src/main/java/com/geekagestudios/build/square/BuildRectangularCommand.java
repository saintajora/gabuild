/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.square;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class BuildRectangularCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "build-rect";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/build-rect [x] [y] [z] [ex] [ey] [ez] [material] [border-material]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args.length >= 6) {
			BuildRectangular b = new BuildRectangular(args);
			b.build(sender);
		} else {
			throw new CommandException("Invalid arguments.");
		}
	}
}
