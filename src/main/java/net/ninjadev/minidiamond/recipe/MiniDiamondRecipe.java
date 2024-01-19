package net.ninjadev.minidiamond.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;
import net.ninjadev.minidiamond.MiniDiamond;

public class MiniDiamondRecipe implements CraftingRecipe {

    public MiniDiamondRecipe() {
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        int nonEmpty = 0;
        ItemStack last = ItemStack.EMPTY;
        ItemStack diamond = new ItemStack(Items.DIAMOND);
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!ItemStack.areItemsEqual(stack, diamond)) {
                nonEmpty++;
                last = stack;
            }
        }
        if (nonEmpty > 1) return false;
        return ItemStack.areItemsEqual(diamond, last);
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return this.getResult(registryManager);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 4;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        ItemStack miniDiamond = MiniDiamond.getMiniDiamond();
        miniDiamond.setCount(9);
        return miniDiamond;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.SHAPELESS;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }
}
