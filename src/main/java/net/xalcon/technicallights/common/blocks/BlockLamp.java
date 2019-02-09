package net.xalcon.technicallights.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.xalcon.technicallights.TechnicalLights;
import net.xalcon.technicallights.common.CreativeTabTechnicalLights;
import net.xalcon.technicallights.common.init.ModBlocks;

import java.util.Random;

public class BlockLamp extends Block
{
    private static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class, EnumDyeColor.values());

    private final boolean isOn;

    public BlockLamp(boolean isOn)
    {
        super(Material.GROUND);
        this.isOn = isOn;
        this.setRegistryName(TechnicalLights.MODID, "lamp" + (isOn ? "_on" : "_off"));
        this.setTranslationKey(this.getRegistryName().toString());
        this.setCreativeTab(CreativeTabTechnicalLights.INSTANCE);
        this.setHardness(0.3f);

        if(isOn)
        {
            this.setLightLevel(1.0f);
            this.setLightOpacity(0);
        }
    }

    /**
     * Checks if the specified tool type is efficient on this block,
     * meaning that it digs at full speed.
     *
     * @param type
     * @param state
     */
    @Override
    public boolean isToolEffective(String type, IBlockState state)
    {
        return super.isToolEffective(type, state);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(EnumDyeColor dye : EnumDyeColor.values())
        {
            items.add(new ItemStack(this, 1, dye.getMetadata()));
        }
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.getBlockColor(EnumDyeColor.byMetadata(this.getMetaFromState(state)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, COLOR);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(COLOR).getMetadata();
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return layer == BlockRenderLayer.SOLID || (isOn && layer == BlockRenderLayer.TRANSLUCENT);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(ModBlocks.lamp_on, 1, this.getMetaFromState(state));
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (!this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ModBlocks.lamp_on.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
            else if (this.isOn && worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ModBlocks.lamp_off.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
        }
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            if (!this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.scheduleUpdate(pos, this, 4);
            }
            else if (this.isOn && worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ModBlocks.lamp_off.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (!this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ModBlocks.lamp_on.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.lamp_on);
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     *
     * @param state
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.lamp_on, 1, this.getMetaFromState(state));
    }
}
