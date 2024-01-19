package net.ninjadev.minidiamond.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.ninjadev.minidiamond.MiniDiamond;
import net.ninjadev.minidiamond.recipe.DiamondRecipe;
import net.ninjadev.minidiamond.recipe.MiniDiamondRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unchecked")
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "getFirstMatch(Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/world/World;)Ljava/util/Optional;", at = @At("RETURN"), cancellable = true)
    public <C extends Inventory, T extends Recipe<C>> void replace(RecipeType<T> type, C inventory, World world, CallbackInfoReturnable<Optional<RecipeEntry<T>>> cir) {
        if (!type.equals(RecipeType.CRAFTING)) return;

        if (!handleMiniDiamond(inventory, cir)) {
            handleDiamond(inventory, cir);
        }
    }

    @Unique
    private static <C extends Inventory, T extends Recipe<C>> void handleDiamond(C inventory, CallbackInfoReturnable<Optional<RecipeEntry<T>>> cir) {
        ItemStack diamond = new ItemStack(Items.DIAMOND);
        int size = inventory.size();
        int emptyCount = 0;
        int diamondCount = 0;
        for (int i = 0; i < size; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) {
                emptyCount++;
            } else if (ItemStack.areItemsEqual(stack, diamond)) {
                if (diamondCount++ > 1) return;
            }
        }
        if (emptyCount != 8 || diamondCount != 1) return;
        RecipeEntry<T> entry = (RecipeEntry<T>) new RecipeEntry<>(new Identifier(MiniDiamond.MOD_ID, "diamond_to_mini"), new MiniDiamondRecipe());
        cir.setReturnValue(Optional.of(entry));
    }

    @Unique
    private static <C extends Inventory, T extends Recipe<C>> boolean handleMiniDiamond(C inventory, CallbackInfoReturnable<Optional<RecipeEntry<T>>> cir) {
        int size = inventory.size();
        if (!inventory.containsAny(Set.of(Items.IRON_NUGGET))) return false;

        for (int i = 0; i < size; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getItem() != Items.IRON_NUGGET || !stack.hasNbt()) return false;
            NbtCompound nbt = stack.getNbt();
            if (!Objects.requireNonNull(nbt).contains("miniDiamond")) return false;
        }
        RecipeEntry<T> entry = (RecipeEntry<T>) new RecipeEntry<>(new Identifier(MiniDiamond.MOD_ID, "mini_to_diamond"), new DiamondRecipe());
        cir.setReturnValue(Optional.of(entry));
        return true;

    }

}
