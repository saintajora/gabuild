/*
 * GABuild. For building, silly.
 * This mod is licensed under GNU GPL v3, included with this repo and available at https://www.gnu.org/licenses/
 */

package com.geekagestudios.build;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import com.geekagestudios.build.round.BuildCylinderOnXCommand;
import com.geekagestudios.build.round.BuildCylinderOnYCommand;
import com.geekagestudios.build.round.BuildCylinderOnZCommand;
import com.geekagestudios.build.round.BuildRingHorizCommand;
import com.geekagestudios.build.round.BuildRingVertXCommand;
import com.geekagestudios.build.round.BuildRingVertZCommand;
import com.geekagestudios.build.round.BuildSphereCommand;
import com.geekagestudios.build.square.BuildRectangularCommand;
import com.geekagestudios.build.square.BuildSquareHorizCommand;
import com.geekagestudios.build.square.BuildSquareVertXCommand;
import com.geekagestudios.build.square.BuildSquareVertZCommand;
import com.geekagestudios.build.square.BuildTubeOnXCommand;
import com.geekagestudios.build.square.BuildTubeOnYCommand;
import com.geekagestudios.build.square.BuildTubeOnZCommand;

@Mod(modid = GABuildMod.MODID, name = GABuildMod.NAME, version = GABuildMod.VERSION)

public class GABuildMod {

	public static final String MODID = "gabuilder";
    public static final String NAME = "GA Builder Mod";
    public static final String VERSION = "0.0.1";

    public static Logger logger;
    
    public static Configuration config;
    
    public static Block defFillBlock;
    public static Block defBorderBlock;
    public static Block defLightBlock;
    

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        
        config = new Configuration(event.getSuggestedConfigurationFile());
        checkConfig();  
    }
    
    public static void checkConfig() {
    	try {
	    	config.load();
	    	
	    	String fillBlock = config.get("default-blocks", "defaultFillBlock", "minecraft:dirt").getString();
	    	defFillBlock = Block.getBlockFromName(fillBlock);
	    	if(defFillBlock == null) logger.warn("Block "+fillBlock+" not found.");
    	
	    	String borderBlock = config.get("default-blocks", "defaultBorderBlock", "minecraft:dirt").getString();
	    	defBorderBlock = Block.getBlockFromName(borderBlock);
	    	if(defBorderBlock == null) logger.warn("Block "+borderBlock+" not found.");
	    	
	    	String lightBlock = config.get("default-blocks", "defaultLightBlock", "minecraft:glowstone").getString();
	    	defLightBlock = Block.getBlockFromName(lightBlock);
	    	if(defLightBlock == null) logger.warn("Block "+lightBlock+" not found.");
    	} catch(Exception e) {
    		logger.error("Failed to load configuration. "+e.getMessage());
    	} finally {
    		if(config.hasChanged()) config.save();
    	}
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	logger.debug("GA Builder: registering commands");
        event.registerServerCommand(new BuildRectangularCommand());
        event.registerServerCommand(new BuildClearCommand());
        event.registerServerCommand(new BuildRingHorizCommand());
        event.registerServerCommand(new BuildRingVertXCommand());
        event.registerServerCommand(new BuildRingVertZCommand());
        event.registerServerCommand(new BuildCylinderOnXCommand());
        event.registerServerCommand(new BuildCylinderOnYCommand());
        event.registerServerCommand(new BuildCylinderOnZCommand());
        event.registerServerCommand(new BuildSphereCommand());
        event.registerServerCommand(new BuildSquareHorizCommand());
        event.registerServerCommand(new BuildSquareVertXCommand());
        event.registerServerCommand(new BuildSquareVertZCommand());
        event.registerServerCommand(new BuildTubeOnXCommand());
        event.registerServerCommand(new BuildTubeOnYCommand());
        event.registerServerCommand(new BuildTubeOnZCommand());
    }
}
