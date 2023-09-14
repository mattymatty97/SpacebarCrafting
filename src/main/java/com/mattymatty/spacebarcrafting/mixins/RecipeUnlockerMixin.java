package com.mattymatty.spacebarcrafting.mixins;

import com.mattymatty.spacebarcrafting.interfaces.CraftingMemory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeUnlocker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RecipeUnlocker.class)
public interface RecipeUnlockerMixin extends CraftingMemory{
    @Inject(method = "unlockLastRecipe", at = @At("HEAD"))
    default void save_last_recipe(PlayerEntity player, List<ItemStack> ingredients, CallbackInfo ci){
        this.spacebarCrafting$saveLastRecipe();
    }
}
