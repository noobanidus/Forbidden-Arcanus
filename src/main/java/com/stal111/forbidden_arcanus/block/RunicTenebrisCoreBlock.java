package com.stal111.forbidden_arcanus.block;

import java.util.Random;

import com.stal111.forbidden_arcanus.item.ModItems;
import com.stal111.forbidden_arcanus.sound.ModSounds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RunicTenebrisCoreBlock extends WaterloggedBlock {

	public RunicTenebrisCoreBlock(String name, Properties properties) {
		super(name, properties);
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty()) {
			return true;
		} else {
			if (stack.getItem() == ModItems.rune && !worldIn.isRemote) {
				if (!player.abilities.isCreativeMode) {
					stack.shrink(1);
				}
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.dark_rune));
				return true;
			} else if (stack.getItem() == ModItems.rune_bag && !worldIn.isRemote) {
				if (!player.abilities.isCreativeMode) {
					stack.shrink(1);
				}
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.dark_rune_bag));
				return true;
			} else if (stack.getItem() == ModBlocks.runestone.asItem() && !worldIn.isRemote) {
				if (!player.abilities.isCreativeMode) {
					stack.shrink(1);
				}
				player.inventory.addItemStackToInventory(new ItemStack(ModBlocks.dark_runestone));
				return true;
			} else {
				return false;
			}

		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		world.playSound(pos.getX(), pos.getY() + 0.5D, pos.getZ(), ModSounds.runic_tenebris_core_activated,
				SoundCategory.BLOCKS, 1.0F, 1.0F, true);
	}

	@Override
	public int getLightValue(IBlockState state) {
		return 14;
	}

	@Override
	public void animateTick(IBlockState state, World world, BlockPos pos, Random random) {
		for (int l = 0; l < 2; l++) {
			double d0 = pos.getX() + random.nextFloat();
			double d1 = pos.getY() + random.nextFloat();
			double d2 = pos.getZ() + random.nextFloat();
			double d3 = (random.nextFloat() - 0.5D) * 0.5000000014901161D;
			double d4 = (random.nextFloat() - 0.5D) * 0.5000000014901161D;
			double d5 = (random.nextFloat() - 0.5D) * 0.5000000014901161D;
			world.spawnParticle(Particles.LARGE_SMOKE, d0, d1, d2, d3, d4, d5);
		}
		world.playSound(pos.getX(), pos.getY() + 0.5D, pos.getZ(), ModSounds.runic_tenebris_core_ambient,
				SoundCategory.BLOCKS, 1.0F, 1.0F, true);

	}
}
