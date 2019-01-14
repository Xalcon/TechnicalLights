package net.xalcon.technicallights.client;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.xalcon.technicallights.common.CommonProxy;
import net.xalcon.technicallights.common.init.ModBlocks;

public class ClientProxy extends CommonProxy
{
    @Override
    public void postInit()
    {
        BlockLightColor color = new BlockLightColor();
        FMLClientHandler.instance().getClient().getBlockColors().registerBlockColorHandler(color, ModBlocks.lamp_off);
        FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(color, ModBlocks.lamp_off);
        FMLClientHandler.instance().getClient().getBlockColors().registerBlockColorHandler(color, ModBlocks.lamp_on);
        FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(color, ModBlocks.lamp_on);
    }
}
