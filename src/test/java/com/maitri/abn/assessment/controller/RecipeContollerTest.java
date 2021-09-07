package com.maitri.abn.assessment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maitri.abn.assessment.model.entity.Ingredient;
import com.maitri.abn.assessment.model.entity.Recipe;
import com.maitri.abn.assessment.model.rest.response.IngredientDto;
import com.maitri.abn.assessment.model.rest.response.RecipeDto;
import com.maitri.abn.assessment.service.RecipeService;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeContoller.class)
class RecipeContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecipeService recipeService;

	private List<RecipeDto> recipes;
	private RecipeDto recipe;
	private Recipe recipeEntity;
	private RecipeDto recipeDto;

	@BeforeEach
	public void setUp() {
		recipe = new RecipeDto();
		recipe.setId(1);
		recipe.setCookingInstruction("test");
		recipe.setCreateDate(new Date());
		recipe.setNumberOfPerson(2);
		recipe.setVegetarian(true);
		List<IngredientDto> ingredients = new ArrayList<>();
		IngredientDto ingredient1 = new IngredientDto();
		ingredient1.setId(1);
		ingredient1.setIngredientDesciption("test");
		ingredient1.setIngredientName("test");
		IngredientDto ingredient2 = new IngredientDto();
		ingredient2.setId(2);
		ingredient2.setIngredientDesciption("test");
		ingredient2.setIngredientName("test");
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		recipe.setIngredients(ingredients);

		recipes = new ArrayList<RecipeDto>();
		recipes.add(recipe);

		Recipe recipeEntity = new Recipe();
		recipeEntity.setId(1);
		recipeEntity.setCookingInstruction("test");
		recipeEntity.setCreateDate(new Date());
		recipeEntity.setNumberOfPerson(2);
		recipeEntity.setVegetarian(true);
		List<Ingredient> ingredientEntities = new ArrayList<>();
		Ingredient ingredientEntity1 = new Ingredient();
		ingredientEntity1.setId(1);
		ingredientEntity1.setIngredientDesciption("test");
		ingredientEntity1.setIngredientName("test");
		Ingredient ingredientEntity2 = new Ingredient();
		ingredientEntity2.setId(2);
		ingredientEntity2.setIngredientDesciption("test");
		ingredientEntity2.setIngredientName("test");
		ingredientEntities.add(ingredientEntity1);
		ingredientEntities.add(ingredientEntity2);
		recipeEntity.setIngredients(ingredientEntities);

		recipeDto = new RecipeDto();
		recipeDto.setCookingInstruction("test");
		recipeDto.setNumberOfPerson(2);
		recipeDto.setVegetarian(true);
		List<IngredientDto> ingredientRequests = new ArrayList<>();
		IngredientDto ingredientDto1 = new IngredientDto();
		ingredientDto1.setIngredientDesciption("test");
		ingredientDto1.setIngredientName("test");
		IngredientDto ingredientDto2 = new IngredientDto();
		ingredientDto2.setIngredientDesciption("test");
		ingredientDto2.setIngredientName("test");
		ingredientRequests.add(ingredientDto1);
		ingredientRequests.add(ingredientDto2);
		recipeDto.setIngredients(ingredientRequests);
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testGetAll() throws Exception {

		Mockito.when(recipeService.getAll()).thenReturn(recipes);

		mockMvc.perform(get("/api/v1/recipe/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testFindById() throws Exception {

		long id = 1;
		Mockito.when(recipeService.findById(id)).thenReturn(recipe);

		mockMvc.perform(get("/api/v1/recipe/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testDeleteRecipeById() throws Exception {
		long id = 1;

		doNothing().when(recipeService).deleteRecipe(id);

		mockMvc.perform(delete("/api/v1/recipe/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testCreateRecipe() throws Exception {

		Mockito.when(recipeService.createRecipe(recipeEntity)).thenReturn(recipe);

		mockMvc.perform(
				post("/api/v1/recipe/").content(asJsonString(recipeDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(201));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testCreateRecipe_invalidRecipeName() throws Exception {

		Mockito.when(recipeService.createRecipe(recipeEntity)).thenReturn(recipe);

		RecipeDto recipeDtoInput = recipeDto;
		recipeDtoInput.setName("this is to test long name for recipe");
		mockMvc.perform(post("/api/v1/recipe/").content(asJsonString(recipeDtoInput))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testUpdateAccount() throws Exception {

		long id = 1;

		Mockito.when(recipeService.createRecipe(recipeEntity)).thenReturn(recipe);

		mockMvc.perform(put("/api/v1/recipe/{id}", id).content(asJsonString(recipeDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));
	}

	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
