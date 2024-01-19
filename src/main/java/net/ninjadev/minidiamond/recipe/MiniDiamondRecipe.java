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

public class DiamondRecipe implements Recipe<CraftingInventory> {
    private final Identifier id = new Identifier(MiniDiamond.MOD_ID, "mini_to_diamond_recipe");
    private final ItemStack ingredient;
    private final ItemStack result;

    public DiamondRecipe(ItemStack ingredient, ItemStack result) {
        this.ingredient = MiniDiamond.getMiniDiamond();
        this.result = new ItemStack(Items.DIAMOND);
    }

    public Identifier getId() {
        return this.id;
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int size = inventory.size();
        if (size < 9) return false;
        for (int i = 0; i < size; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) return false;
            if (!ItemStack.areEqual(stack, this.ingredient)) return false;
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
        return this.result;
    }

    public ItemStack getResult() {
        return getResult(null);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DiamondRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DiamondRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "mini_to_diamond_recipe";

    }
}
