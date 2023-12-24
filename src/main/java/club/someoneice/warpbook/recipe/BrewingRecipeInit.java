package club.someoneice.warpbook.recipe;

import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;

public class BrewingRecipeInit {
    public static void init() {
        BrewingRecipeRegistry.addRecipe(new RecipeWarpPotion());
    }
}
