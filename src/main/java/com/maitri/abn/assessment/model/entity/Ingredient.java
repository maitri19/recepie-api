package com.maitri.abn.assessment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new ingredient.
 */
@Data
@Entity(name = "ingredient")
public class Ingredient {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	/** The ingredient name. */
	@Column(name = "ingredient_name")
	String ingredientName;

	/** The ingredient desciption. */
	@Column(name = "ingredient_desciption")
	String ingredientDesciption;

}
