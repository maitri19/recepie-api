package com.maitri.abn.assessment.service;

import java.util.List;

import com.maitri.abn.assessment.model.entity.Recipe;
import com.maitri.abn.assessment.model.rest.response.RecipeDto;

// TODO: Auto-generated Javadoc
/**
 * The Interface RecipeService.
 */
public interface RecipeService {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<RecipeDto> getAll();


	/**
	 * Find by id.
	 *
	 * @param recipeId the recipe id
	 * @return the recipe dto
	 */
	RecipeDto findById(long recipeId);

	/**
	 * Creates the recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	RecipeDto createRecipe(Recipe recipe);

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 */
	void deleteRecipe(long recipeId);

	/**
	 * Update recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	RecipeDto updateRecipe(Recipe recipe);

}
