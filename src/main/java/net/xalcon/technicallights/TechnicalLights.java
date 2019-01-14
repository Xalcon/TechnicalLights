package net.xalcon.technicallights;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.technicallights.client.BlockLightColor;
import net.xalcon.technicallights.common.CommonProxy;
import net.xalcon.technicallights.common.init.ModBlocks;
import org.apache.logging.log4j.Logger;

@Mod(modid = TechnicalLights.MODID, name = TechnicalLights.NAME, version = TechnicalLights.VERSION)
public class TechnicalLights
{
    public static final String MODID = "technicallights";
    public static final String NAME = "Technical Lights";
    public static final String VERSION = "@VERSION@";

    private static Logger logger;

    @SidedProxy(modId = MODID, clientSide = "net.xalcon.technicallights.client.ClientProxy", serverSide = "net.xalcon.technicallights.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
}
