package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeServiceImpl;

public class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock RecipeRepository recipeRepository;

  @Mock RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock RecipeCommandToRecipe recipeCommandToRecipe;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    recipeService =
        new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test(expected = NotFoundException.class)
  public void getRecipeByIdTestNotFound() {
    Optional<Recipe> recipeOptional = Optional.empty();
    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1l);
  }

  @Test
  public void getRecipesTest() throws Exception {
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipesData = new HashSet<>();
    recipesData.add(recipe);

    when(recipeService.getRecipes()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(1, recipes.size());

    verify(recipeRepository, times(1)).findAll();
    verify(recipeRepository, never()).findById(anyLong());
  }

  @Test
  public void testDeleteById() throws Exception {
    // given
    Long idToDelete = Long.valueOf(2L);
    recipeService.deleteById(idToDelete);

    // no 'when', since method has void return type

    // then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }
}
