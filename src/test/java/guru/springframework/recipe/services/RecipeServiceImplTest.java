package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeList() {
        Set<Recipe> recipes = recipeService.getRecipeList();
        assertEquals(0, recipes.size());

        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(new Recipe());
        when(recipeService.getRecipeList()).thenReturn(recipesData);
        recipes = recipeService.getRecipeList();
        assertEquals(1, recipes.size());

        // Verify if recipeRepository.findAll() where called exactly two times in the code above.
        // You have to pass as argument a mock object.
        verify(recipeRepository, times(2)).findAll();
    }
}