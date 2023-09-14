package com.mattymatty.spacebarcrafting.mixins;

import com.mattymatty.spacebarcrafting.SpaceBarCrafting;
import com.mattymatty.spacebarcrafting.interfaces.CraftingMemory;
import com.mattymatty.spacebarcrafting.mixins.accessors.CraftingScreenHandlerAccessor;
import com.mattymatty.spacebarcrafting.mixins.accessors.PlayerScreenHandlerAccessor;
import com.mattymatty.spacebarcrafting.mixins.accessors.RecipeBookWidgetAccessor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CraftingScreen.class)
public abstract class CraftingScreenMixin extends HandledScreen<CraftingScreenHandler> {

    @Shadow @Final private RecipeBookWidget recipeBook;

    private CraftingScreenMixin(CraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Unique
    public boolean handleKeyPress(){
        assert SpaceBarCrafting.client != null;
        assert SpaceBarCrafting.client.player != null;
        assert SpaceBarCrafting.client.interactionManager != null;
        if (!SpaceBarCrafting.client.player.isSpectator()) {

            Recipe<?> recipe = ((CraftingMemory)((CraftingScreenHandlerAccessor)this.handler).getResult()).spacebarCrafting$getRecipeMemory();

            if (recipe != null && ((RecipeBookWidgetAccessor)this.recipeBook).getGhostSlots().getRecipe() != recipe) {
                ((RecipeBookWidgetAccessor)this.recipeBook).getGhostSlots().reset();
                SpaceBarCrafting.client.interactionManager.clickRecipe(SpaceBarCrafting.client.player.currentScreenHandler.syncId, recipe, Screen.hasShiftDown());
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_SPACE){
            if (handleKeyPress())
                return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        assert SpaceBarCrafting.client != null;
        assert SpaceBarCrafting.client.player != null;
        ((CraftingMemory)((CraftingScreenHandlerAccessor)this.handler).getResult()).spacebarCrafting$clearRecipeMemory();
        super.close();
    }
}
