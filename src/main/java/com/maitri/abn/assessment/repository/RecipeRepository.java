package com.maitri.abn.assessment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maitri.abn.assessment.model.entity.Recipe;

/**
 * The Interface RecipeRepository.
 */
@Repository
@Transactional
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
