package com.example.madprojectbrownies;

public class Recipe {

    String recipeID;
    String recipeName;
    String ingredient;
    String method;

    public Recipe() {
    }

    public Recipe(String recipeID, String recipeName, String ingredient, String method) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.ingredient = ingredient;
        this.method = method;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
