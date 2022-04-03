package io.github.itkova.api

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

fun craftShapedRecipe(shape: String, ingredientShape: Array<out Char>, ingredientMaterial: Array<out Material>, result: Material, resultCount: Int = 1): ShapedRecipe {
    var recipe = ShapedRecipe(ItemStack(result, resultCount)).shape(shape)
    if (ingredientShape.size == ingredientMaterial.size) {
        for (i in 0.. ingredientShape.size) {
            recipe.setIngredient(ingredientShape[i], ingredientMaterial[i])
    }
    }
    return recipe
}

fun craftShapelessRecipe(ingredientMaterial: Array<out Material>, result: Material, resultCount: Int = 1): ShapelessRecipe {
    var recipe = ShapelessRecipe(ItemStack(result, resultCount))
    for (i in 0.. ingredientMaterial.size) {
        recipe.addIngredient(ingredientMaterial[i])
    }
    return recipe
}