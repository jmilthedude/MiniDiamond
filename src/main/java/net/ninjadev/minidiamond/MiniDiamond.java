package net.ninjadev.minidiamond;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.ninjadev.minidiamond.util.GlintEnchantment;

import java.util.Map;

public class MiniDiamond implements ModInitializer {
    public static final String MOD_ID = "minidiamond";

    private static final GlintEnchantment GLINT_ENCHANTMENT = new GlintEnchantment();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "glint_enchantment"), GLINT_ENCHANTMENT);
    }

    public static ItemStack getMiniDiamond() {
        ItemStack miniDiamond = new ItemStack(Items.IRON_NUGGET);
        miniDiamond.setCustomName(Text.literal("Mini Diamond").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.DARK_AQUA))));
        miniDiamond.addEnchantment(GLINT_ENCHANTMENT, 1);
        NbtCompound nbt = miniDiamond.getOrCreateNbt();
        nbt.putBoolean("miniDiamond", true);
        miniDiamond.setNbt(nbt);
        return miniDiamond;
    }
}
