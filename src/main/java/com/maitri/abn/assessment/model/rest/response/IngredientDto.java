package com.maitri.abn.assessment.model.rest.response;

import com.maitri.abn.assessment.model.entity.Ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDto {

	/** The id. */
	private long id;

	/** The ingredient name. */
	private String ingredientName;

	/** The ingredient desciption. */
	private String ingredientDesciption;

	/**
	 * From model.
	 *
	 * @param ingredient the ingredient
	 * @return the ingredient response
	 */
	public static IngredientDto fromModel(Ingredient ingredient) {
		IngredientDto ingredientResponse = new IngredientDto();
		ingredientResponse.setIngredientDesciption(ingredient.getIngredientDesciption());
		ingredientResponse.setIngredientName(ingredient.getIngredientName());
		ingredientResponse.setId(ingredient.getId());
		return ingredientResponse;
	}
	
	/**
	 * To model.
	 *
	 * @return the ingredient
	 */
	public Ingredient toModel() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(this.id);
		ingredient.setIngredientDesciption(this.ingredientDesciption);
		ingredient.setIngredientName(this.ingredientName);
		return ingredient;
	}
}
