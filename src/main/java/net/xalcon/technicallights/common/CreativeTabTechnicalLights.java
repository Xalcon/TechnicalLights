package net.xalcon.technicallights.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.xalcon.technicallights.TechnicalLights;
import net.xalcon.technicallights.common.init.ModBlocks;

public class CreativeTabTechnicalLights extends CreativeTabs
{
    public final static CreativeTabs INSTANCE = new CreativeTabTechnicalLights();

    private CreativeTabTechnicalLights()
    {
        super(TechnicalLights.MODID);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ModBlocks.lamp_on, 1, EnumDyeColor.LIME.getMetadata());
    }
}
