/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class BuildClearCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "build-clear";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/build-clear [x] [y] [z] [ex] [ey] [ez]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args.length == 6) {
			BuildClear c = new BuildClear(args);
			c.build(sender);
		} else {
			throw new CommandException("Invalid arguments.");
		}
	}
}
