package com.mattymatty.spacebarcrafting.mixins;

import com.mattymatty.spacebarcrafting.interfaces.CraftingMemory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.recipe.Recipe;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CraftingResultInventory.class)
public abstract class CraftingResultInventoryMixin implements CraftingMemory {
    @Shadow public abstract @Nullable Recipe<?> getLastRecipe();

    @Unique
    private Recipe<?> recipeMemory;

    @Override
    public Recipe<?> spacebarCrafting$getRecipeMemory() {
        return recipeMemory;
    }

    @Override
    public void spacebarCrafting$clearRecipeMemory() {
        this.recipeMemory = null;
    }

    @Override
    public void spacebarCrafting$setRecipeMemory(Recipe<?> recipe) {
        if (recipe!=null)
            recipeMemory = recipe;
    }

    @Override
    public void spacebarCrafting$saveLastRecipe() {
        spacebarCrafting$setRecipeMemory(getLastRecipe());
    }

}
