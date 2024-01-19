package net.ninjadev.minidiamond.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class GlintEnchantment extends Enchantment {
    public GlintEnchantment() {
        super(Enchantment.Rarity.RARE, null, EquipmentSlot.values());
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return false;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }
}
