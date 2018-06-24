/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build.square;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class BuildSquareVertXCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "build-squarevx";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/build-squarevx [cX] [cY] [cZ] [radius] <thickness> <material> <border-material>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args.length >= 4) {
			new BuildSquareVertX(args).build(sender);
		} else {
			throw new CommandException("Invalid arguments.");
		}
	}
}