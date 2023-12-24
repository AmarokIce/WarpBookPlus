package club.someoneice.warpbook.recipe;

import club.someoneice.warpbook.init.ItemInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;

public class RecipeWarpPotion implements IBrewingRecipe {
    @Override
    public boolean isInput(ItemStack input) {
        return input.getItem() == Items.POTION;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.getItem() == ItemInit.ENDER_GEM.asItem();
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (!isInput(input) || !isIngredient(ingredient)) return ItemStack.EMPTY;
        return null; // TODO
    }
}
