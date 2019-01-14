package net.xalcon.technicallights.common.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.*;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.technicallights.TechnicalLights;
import net.xalcon.technicallights.common.blocks.BlockLamp;

@Mod.EventBusSubscriber(modid = TechnicalLights.MODID)
@GameRegistry.ObjectHolder(TechnicalLights.MODID)
public class ModBlocks
{
    @GameRegistry.ObjectHolder("lamp_on")
    public static BlockLamp lamp_on;

    @GameRegistry.ObjectHolder("lamp_off")
    public static BlockLamp lamp_off;

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockLamp(false));
        event.getRegistry().register(new BlockLamp(true));
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemCloth(lamp_on).setRegistryName(lamp_on.getRegistryName()));
    }

    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent event)
    {
        for(EnumDyeColor dye : EnumDyeColor.values())
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lamp_on), dye.getMetadata(), new ModelResourceLocation(lamp_on.getRegistryName(), "inventory"));
        }
    }
}
