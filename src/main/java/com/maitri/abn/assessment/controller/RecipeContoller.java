package com.maitri.abn.assessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maitri.abn.assessment.model.rest.response.RecipeDto;
import com.maitri.abn.assessment.service.RecipeService;

import lombok.AllArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeContoller.
 */
@RestController

/**
 * Instantiates a new recipe contoller.
 *
 * @param recipeService the recipe service
 */
@AllArgsConstructor
@RequestMapping("/api/v1/recipe")
public class RecipeContoller {

	/**  The recipehandler instance of handler class. */
	@Autowired
	RecipeService recipeService;

	/**
	 * Gets the all recipe.
	 *
	 * @return the all
	 */
	@GetMapping("/")
	public ResponseEntity<List<RecipeDto>> getAll() {
		System.out.println("test");
		return ResponseEntity.ok(recipeService.getAll());
	}

	/**
	 * This method returns unique recipe by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<RecipeDto> findById(@PathVariable @Valid long id) {
		return ResponseEntity.ok(recipeService.findById(id));
	}

	/**
	 * This method deletes recipe by given ID.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	public void deleteRecipeById(@PathVariable @Valid long id) {
		recipeService.deleteRecipe(id);
	}

	/**
	 * This method creates recipe with given request body.
	 *
	 * @param recipeDto the recipe dto
	 * @return the response entity
	 */
	@PostMapping("/")
	public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeDto recipeDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipeDto.toModel()));
	}

	/**
	 * This method updates recipe of given id with the information provided in
	 * request body.
	 *
	 * @param recipeDto the recipe dto
	 * @param id            the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<RecipeDto> updateRecipe(@Valid @RequestBody RecipeDto recipeDto,
			@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.updateRecipe(recipeDto.toModel(id)));
	}

}
