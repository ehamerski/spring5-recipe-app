package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipeList() {
        Set<Recipe> rslt = new HashSet<>();
        recipeRepository.findAll().forEach(rslt::add);
        return rslt;
    }
}
