package net.ninjadev.minidiamond.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.ninjadev.minidiamond.MiniDiamond;

public class MiniDiamondRecipe implements Recipe<CraftingInventory> {
    private final Identifier id;

    public MiniDiamondRecipe(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return this.id;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int size = inventory.size();
        if (size < 9) return false;
        for (int i = 0; i < size; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) return false;
            if (!ItemStack.areEqual(stack, MiniDiamond.getMiniDiamond())) return false;
        }
        return true;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return width == 3 && height == 3;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return new ItemStack(Items.DIAMOND);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }

    public static class Type implements RecipeType<MiniDiamondRecipe> {
        
    }
}
