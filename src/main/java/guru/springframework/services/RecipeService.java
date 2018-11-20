package guru.springframework.services;

import java.util.Set;

import guru.springframework.domain.Recipe;

public interface RecipeService {

	Recipe findById(Long l);
	Set<Recipe> getRecipes();
}
