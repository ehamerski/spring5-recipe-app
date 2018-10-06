package guru.springframework.recipe.bootstrap;

import guru.springframework.recipe.domain.*;
import guru.springframework.recipe.helpers.ImageHelper;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final ImageHelper imageHelper;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, ImageHelper imageHelper) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.imageHelper = imageHelper;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Category mexican = categoryRepository.findByDescription("Mexican").get();

        UnitOfMeasure unit = unitOfMeasureRepository.findByDescription("Unit").get();
        UnitOfMeasure tesp = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tbsp = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();

        // Guacamole
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("ELISE BAUER");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setImage(imageHelper.getImageBytes("https://www.simplyrecipes.com/wp-content/uploads/2014/05/guacamole-horiz-a-1600.jpg"));
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.getCategories().add(mexican);
        guacamole.getIngredients().add(createIngredient(guacamole, 2.0, unit, "ripe avocados"));
        guacamole.getIngredients().add(createIngredient(guacamole, 0.5, tesp, "Kosher salt"));
        guacamole.getIngredients().add(createIngredient(guacamole, 1.0, tbsp, "fresh lime juice or lemon juice"));
        guacamole.getIngredients().add(createIngredient(guacamole, 2.0, tbsp, "minced red onion or thinly sliced green onion"));
        guacamole.getIngredients().add(createIngredient(guacamole, 2.0, unit, "serrano chiles, stems and seeds removed, minced"));
        guacamole.getIngredients().add(createIngredient(guacamole, 2.0, tbsp, "cilantro (leaves and tender stems), finely chopped"));
        guacamole.getIngredients().add(createIngredient(guacamole, 1.0, dash, "freshly grated black pepper"));
        guacamole.getIngredients().add(createIngredient(guacamole, 0.5, unit, "ripe tomato, seeds and pulp removed, chopped"));
        guacamole.setDirections(
                ("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.")
        );
        guacamole.setNotes(createNotes(guacamole, "N/A"));
        recipeRepository.save(guacamole);

        // Tacos
        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(15);
        tacos.setServings(6);
        tacos.setSource("SALLY VARGAS");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");
        tacos.setImage(imageHelper.getImageBytes("https://www.simplyrecipes.com/wp-content/uploads/2017/05/2017-05-29-GrilledChickenTacos-2.jpg"));
        tacos.setDifficulty(Difficulty.EASY);
        tacos.getCategories().add(mexican);
        tacos.getIngredients().add(createIngredient(tacos, 2.0, tbsp, "ancho chili powder"));
        tacos.getIngredients().add(createIngredient(tacos, 1.0, tbsp, "dried oregano"));
        tacos.getIngredients().add(createIngredient(tacos, 1.0, tbsp, "dried cumin"));
        tacos.getIngredients().add(createIngredient(tacos, 1.0, tbsp, "sugar"));
        tacos.getIngredients().add(createIngredient(tacos, 0.5, tbsp, "salt"));
        tacos.getIngredients().add(createIngredient(tacos, 1.0, unit, "clove garlic, finely chopped"));
        tacos.getIngredients().add(createIngredient(tacos, 1.0, tbsp, "finely grated orange zest"));
        tacos.getIngredients().add(createIngredient(tacos, 3.0, tbsp, "fresh-squeezed orange juice"));
        tacos.getIngredients().add(createIngredient(tacos, 2.0, tbsp, "olive oil"));
        tacos.getIngredients().add(createIngredient(tacos, 6.0, unit, "skinless, boneless chicken thighs (1 1/4 pounds)"));
        tacos.setDirections(
                ("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.")
        );
        tacos.setNotes(createNotes(tacos, "N/A"));
        recipeRepository.save(tacos);
    }

    private Ingredient createIngredient(Recipe recipe, Double amount, UnitOfMeasure uom, String description) {
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        ingredient.setAmount(new BigDecimal(amount));
        ingredient.setUom(uom);
        ingredient.setDescription(description);
        return ingredient;
    }

    private Notes createNotes(Recipe recipe, String recipeNotes) {
        Notes notes = new Notes();
        notes.setRecipe(recipe);
        notes.setRecipeNotes(recipeNotes);
        return notes;
    }
}
