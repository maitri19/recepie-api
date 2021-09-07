package com.maitri.abn.assessment.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new recipe.
 */
@Data
@Entity(name = "recipe")
@EnableAutoConfiguration
public class Recipe {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	/** The create date. */
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	Date createDate;

	/** The is vegetarian. */
	@Column(name = "vegetarian")
	boolean isVegetarian;

	/** The number of person. */
	@Column(name = "number_of_person")
	int numberOfPerson;

	/** The name. */
	@Column(name = "name")
	String name;

	/** The cooking instruction. */
	@Column(name = "cooking_instructions")
	String cookingInstruction;

	/** The ingredients. */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = false)
	@Column(name = "ingredients")
	List<Ingredient> ingredients;
}
