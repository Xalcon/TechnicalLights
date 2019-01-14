package net.xalcon.technicallights.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.xalcon.technicallights.common.init.ModBlocks;

import javax.annotation.Nullable;

public class BlockLightColor implements IBlockColor, IItemColor
{
    private int[] colorMap = new int[]
    {
        0xFFFFFFFF, // white
            0xFFFF9900, // orange
            0xFFFF55FF, // magenta
            0xFFBBBBFF, // light_blue
            0xFFFFFF00, // yellow
            0xFF55FF55, // lime
            0xFFFF99BB, // pink
            0xFF555555, // gray
            0xFFAAAAAA, // silver
            0xFF00FFFF, // cyan
            0xFF9900FF, // purple
            0xFF0000FF, // blue
            0xFF835432, // brown
            0xFF22AA22, // green
            0xFFFF0000, // red
            0xFF111111, // black
    };

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
    {
        return colorMap[state.getBlock().getMetaFromState(state)];
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex)
    {
        return colorMap[stack.getMetadata()];
    }
}
