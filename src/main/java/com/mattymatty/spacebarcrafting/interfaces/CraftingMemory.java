package com.mattymatty.spacebarcrafting.interfaces;

import net.minecraft.recipe.Recipe;

public interface CraftingMemory {
    Recipe<?> spacebarCrafting$getRecipeMemory();

    void spacebarCrafting$clearRecipeMemory();
    void spacebarCrafting$setRecipeMemory(Recipe<?> recipe);

    void spacebarCrafting$saveLastRecipe();
}
