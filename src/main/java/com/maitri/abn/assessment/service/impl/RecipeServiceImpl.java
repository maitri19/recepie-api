package com.maitri.abn.assessment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maitri.abn.assessment.exception.RecipeNotFoundException;
import com.maitri.abn.assessment.model.entity.Ingredient;
import com.maitri.abn.assessment.model.entity.Recipe;
import com.maitri.abn.assessment.model.rest.response.RecipeDto;
import com.maitri.abn.assessment.repository.RecipeRepository;
import com.maitri.abn.assessment.service.RecipeService;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeServiceImpl.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	/** The recipe repository. */
	@Autowired
	RecipeRepository recipeRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<RecipeDto> getAll() {
		return recipeRepository.findAll().stream().map(RecipeDto::fromModel).collect(Collectors.toList());
	}

	/**
	 * Find by id.
	 *
	 * @param recipeId the recipe id
	 * @return the recipe dto
	 */
	@Override
	public RecipeDto findById(long recipeId) {
		
		
		Optional<Recipe> recipe =  recipeRepository.findById(recipeId);
		if(recipe.isEmpty()) {
			throw new RecipeNotFoundException("recipe is not found for recipeId=" + recipeId);
		}
		return RecipeDto.fromModel(recipe.get());
	}

	/**
	 * Creates the recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	@Override
	public RecipeDto createRecipe(Recipe recipe) {
		return RecipeDto.fromModel(recipeRepository.save(recipe));
	}

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 */
	@Override
	public void deleteRecipe(long recipeId) {

		Optional<Recipe> recipe =  recipeRepository.findById(recipeId);
		if(recipe.isEmpty()) {
			throw new RecipeNotFoundException("recipe is not found for recipeId=" + recipeId);
		}
		recipeRepository.deleteById(recipeId);
	}

	/**
	 * Update recipe.
	 *
	 * @param model the model
	 * @return the recipe
	 */
	@Override
	public RecipeDto updateRecipe(Recipe model) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(model.getId());
		if (recipeOptional.isEmpty())
			throw new RecipeNotFoundException("recipe is not found for recipeId=" + model.getId());

		Recipe recipe = recipeOptional.get();
		recipe.setCookingInstruction(model.getCookingInstruction());
		recipe.setName(model.getName());
		recipe.setNumberOfPerson(model.getNumberOfPerson());
		recipe.setVegetarian(model.isVegetarian());
		for (Ingredient ingredient : model.getIngredients()) {
			this.updateIngredient(ingredient, recipe.getIngredients());
		}

		return RecipeDto.fromModel(recipeRepository.save(recipe));
		
	}
	
	/**
	 * Update ingredient.
	 *
	 * @param ingredientParam the ingredient param
	 * @param listIngredients the list ingredients
	 * @return the ingredient
	 */
	private void updateIngredient(Ingredient ingredientParam, List<Ingredient> listIngredients) {
		for (Ingredient ingredient : listIngredients) {
			if (ingredient.getId() == ingredientParam.getId()) {
				ingredient.setIngredientDesciption(ingredientParam.getIngredientDesciption());
				ingredient.setIngredientName(ingredientParam.getIngredientName());
			}
		}
	}

}
