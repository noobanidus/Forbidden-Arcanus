package com.stal111.forbidden_arcanus.init;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.enchantment.ModEnchantment;
import com.stal111.forbidden_arcanus.item.EdelwoodBucketItem;
import com.stal111.forbidden_arcanus.util.ModTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForbiddenArcanus.MOD_ID);

    public static final RegistryObject<Enchantment> PERMAFROST = register("permafrost", new EnchantmentBuilder(Enchantment.Rarity.UNCOMMON, ModEnchantmentType.EDELWOOD_BUCKET, EquipmentSlotType.MAINHAND).isTreasure().build());
    public static final RegistryObject<Enchantment> INDESTRUCTIBLE = register("indestructible", new EnchantmentBuilder(Enchantment.Rarity.RARE, ModEnchantmentType.INDESTRUCTIBLE, EquipmentSlotType.MAINHAND).canApplyTogether(enchantment -> !ModTags.Enchantments.INDESTRUCTIBLE_BLACKLISTED.contains(enchantment)).isTreasure().canBeVillagerTrade(false).canGenerateInLoot(false).build());

    private static <T extends Enchantment> RegistryObject<T> register(String name, T enchantment) {
        return ENCHANTMENTS.register(name, () -> enchantment);
    }

    public static class ModEnchantmentType {
        public static final EnchantmentType EDELWOOD_BUCKET = EnchantmentType.create("EDELWOOD_BUCKET", item -> item instanceof EdelwoodBucketItem);
        public static final EnchantmentType INDESTRUCTIBLE = EnchantmentType.create("INDESTRUCTIBLE_CONFIG_BLACKLIST", item -> item.isDamageable() && !(ModTags.Items.INDESTRUCTIBLE_BLACKLISTED.contains(item)));
    }

    public static class EnchantmentBuilder {

        private final ModEnchantment enchantment;

        public EnchantmentBuilder(Enchantment.Rarity rarity, EnchantmentType enchantmentType, EquipmentSlotType... equipmentSlotTypes) {
            this.enchantment = new ModEnchantment(rarity, enchantmentType, equipmentSlotTypes);
        }

        public EnchantmentBuilder maxLevel(int maxLevel) {
            this.enchantment.maxLevel = maxLevel;
            return this;
        }

        public EnchantmentBuilder minAndMaxEnchantability(int minEnchantability, int maxEnchantability) {
            this.enchantment.minEnchantability = minEnchantability;
            this.enchantment.maxEnchantability = maxEnchantability;
            return this;
        }

        public EnchantmentBuilder isTreasure() {
            enchantment.isTreasure = true;
            return this;
        }

        public EnchantmentBuilder canApplyTogether(Predicate<Enchantment> predicate) {
            enchantment.canApplyTogether = predicate;
            return this;
        }

        public EnchantmentBuilder canBeVillagerTrade(boolean canBeTrade) {
            enchantment.canBeVillagerTrade = canBeTrade;
            return this;
        }

        public EnchantmentBuilder canGenerateInLoot(boolean canGenerate) {
            enchantment.canGenerateInLoot = canGenerate;
            return this;
        }

        public ModEnchantment build() {
            return this.enchantment;
        }
    }
}
