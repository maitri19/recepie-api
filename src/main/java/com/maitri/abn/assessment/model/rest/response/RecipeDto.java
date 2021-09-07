package com.maitri.abn.assessment.model.rest.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maitri.abn.assessment.model.entity.Recipe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeDto {

	/** The id. */
	private long id;

	/** The name. */
	@Length(min = 3, max = 20)
	private String name;

	/** The create date. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createDate;

	/** The is vegetarian. */
	private boolean isVegetarian;

	/** The number of person. */
	@Max(value = 50)
	private int numberOfPerson;

	/** The cooking instruction. */
	@Length(min = 3, max = 100)
	private String cookingInstruction;

	/** The ingredients. */
	@Valid
	@NotEmpty
	@Size(min = 1)
	private List<IngredientDto> ingredients;

	/**
	 * From model.
	 *
	 * @param recipe the recipe
	 * @return the recipe response
	 */
	public static RecipeDto fromModel(Recipe recipe) {
		RecipeDto recipeResponse = new RecipeDto();
		recipeResponse.setCookingInstruction(recipe.getCookingInstruction());
		recipeResponse.setName(recipe.getName());
		recipeResponse.setCreateDate(recipe.getCreateDate());
		recipeResponse.setId(recipe.getId());
		recipeResponse.setNumberOfPerson(recipe.getNumberOfPerson());
		recipeResponse.setVegetarian(recipe.isVegetarian());
		recipeResponse.setIngredients(
				recipe.getIngredients().stream().map(IngredientDto::fromModel).collect(Collectors.toList()));
		return recipeResponse;
	}
	
	/**
	 * To model.
	 *
	 * @return the recipe
	 */
	public Recipe toModel() {
		Recipe recipe = new Recipe();
		recipe.setCookingInstruction(this.cookingInstruction);
		recipe.setCreateDate(new Date());
		recipe.setNumberOfPerson(this.numberOfPerson);
		recipe.setVegetarian(this.isVegetarian);
		recipe.setName(this.name);
		recipe.setIngredients(ingredients.stream().map(IngredientDto::toModel).collect(Collectors.toList()));
		return recipe;
	}

	/**
	 * To model.
	 *
	 * @param recipeId the recipe id
	 * @return the recipe
	 */
	public Recipe toModel(long recipeId) {
		Recipe recipe = this.toModel();
		recipe.setId(recipeId);
		return recipe;
	}	
}
