package net.ninjadev.minidiamond.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.ninjadev.minidiamond.MiniDiamond;

public class DiamondRecipe implements CraftingRecipe {

    public DiamondRecipe() {}

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        ItemStack ingredient = MiniDiamond.getMiniDiamond();
        int size = inventory.size();
        if (size < 9) return false;
        for (int i = 0; i < size; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) return false;
            if (!ItemStack.areEqual(stack, ingredient)) return false;
        }
        return true;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return this.getResult(registryManager);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 9;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return new ItemStack(Items.DIAMOND);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.SHAPED;
    }
    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }
}
