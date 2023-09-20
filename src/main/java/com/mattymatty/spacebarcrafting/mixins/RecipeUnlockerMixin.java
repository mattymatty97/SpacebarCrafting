package com.mattymatty.spacebarcrafting.mixins;

import com.mattymatty.spacebarcrafting.SpaceBarCrafting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeUnlocker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RecipeUnlocker.class)
public interface RecipeUnlockerMixin{
    @Shadow Recipe<?> getLastRecipe();
    @Inject(method = "unlockLastRecipe", at = @At("HEAD"))
    default void save_last_recipe(PlayerEntity player, List<ItemStack> ingredients, CallbackInfo ci){
        Recipe<?> recipe = getLastRecipe();
        if (recipe!=null)
            SpaceBarCrafting.lastCraftedRecipe = recipe;
    }
}
