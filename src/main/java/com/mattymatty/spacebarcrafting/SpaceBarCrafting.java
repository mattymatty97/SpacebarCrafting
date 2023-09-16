package com.mattymatty.spacebarcrafting;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.Recipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("deprecation")
public class SpaceBarCrafting implements ClientModInitializer {
    public static MinecraftClient client;
    public static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onInitializeClient() {
        LOGGER.info("Spacebar Crafting Loaded!");
        client = (MinecraftClient)FabricLoader.getInstance().getGameInstance();
    }

    public static Recipe<?> lastCraftedRecipe;

}
