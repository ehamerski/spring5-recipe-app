package guru.springframework.recipe.bootstrap;

import guru.springframework.recipe.domain.*;
import guru.springframework.recipe.helpers.ImageHelper;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
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
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading bootstrap data...");
        recipeRepository.saveAll(getRecipes());
        log.debug("Saving recipes...");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        log.debug("Selecting categories...");
        Category mexican = getCategory("Mexican");
        Category american = getCategory("American");

        log.debug("Selecting units of measure...");
        UnitOfMeasure unit = getUnitOfMeasure("Unit");
        UnitOfMeasure tesp = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure tbsp = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure dash = getUnitOfMeasure("Dash");
        UnitOfMeasure cup = getUnitOfMeasure("Cup");
        UnitOfMeasure pint = getUnitOfMeasure("Pint");

        // Guacamole
        log.debug("Creating 'Perfect Guacamole' recipe...");
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("ELISE BAUER");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setImage(imageHelper.getImageBytes("https://www.simplyrecipes.com/wp-content/uploads/2014/05/guacamole-horiz-a-1600.jpg"));
        guacamole.setDifficulty(Difficulty.EASY);
        log.debug("Adding categories...");
        guacamole.getCategories().add(mexican);
        guacamole.getCategories().add(american);
        log.debug("Adding ingredients...");
        guacamole.addIngredient(new Ingredient(new BigDecimal(2.00), unit, "ripe avocados"))
                 .addIngredient(new Ingredient(new BigDecimal(0.50), tesp, "Kosher salt"))
                 .addIngredient(new Ingredient(new BigDecimal(1.00), tbsp, "fresh lime juice or lemon juice"))
                 .addIngredient(new Ingredient(new BigDecimal(2.00), tbsp, "minced red onion or thinly sliced green onion"))
                 .addIngredient(new Ingredient(new BigDecimal(2.00), unit, "serrano chiles, stems and seeds removed, minced"))
                 .addIngredient(new Ingredient(new BigDecimal(2.00), tbsp, "cilantro (leaves and tender stems), finely chopped"))
                 .addIngredient(new Ingredient(new BigDecimal(1.00), dash, "freshly grated black pepper"))
                 .addIngredient(new Ingredient(new BigDecimal(0.50), unit, "ripe tomato, seeds and pulp removed, chopped"));
        log.debug("Adding directions...");
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        log.debug("Adding notes...");
        guacamole.setNotes(new Notes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!"));
        log.debug("Storing recipe...");
        recipes.add(guacamole);

        // Tacos
        log.debug("Creating 'Spicy Grilled Chicken Tacos' recipe...");
        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(15);
        tacos.setServings(6);
        tacos.setSource("SALLY VARGAS");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");
        tacos.setImage(imageHelper.getImageBytes("https://www.simplyrecipes.com/wp-content/uploads/2017/05/2017-05-29-GrilledChickenTacos-2.jpg"));
        tacos.setDifficulty(Difficulty.EASY);
        log.debug("Adding categories...");
        tacos.getCategories().add(mexican);
        tacos.getCategories().add(american);
        log.debug("Adding ingredients...");
        tacos.addIngredient(new Ingredient(new BigDecimal(2.00), tbsp, "ancho chili powder"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), tbsp, "dried oregano"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), tbsp, "dried cumin"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), tbsp, "sugar"))
             .addIngredient(new Ingredient(new BigDecimal(0.50), tbsp, "salt"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), unit, "clove garlic, finely chopped"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), tbsp, "finely grated orange zest"))
             .addIngredient(new Ingredient(new BigDecimal(3.00), tbsp, "fresh-squeezed orange juice"))
             .addIngredient(new Ingredient(new BigDecimal(2.00), tbsp, "olive oil"))
             .addIngredient(new Ingredient(new BigDecimal(6.00), unit, "skinless, boneless chicken thighs (1 1/4 pounds)"))
             .addIngredient(new Ingredient(new BigDecimal(8.00), unit, "small corn tortillas"))
             .addIngredient(new Ingredient(new BigDecimal(3.00), cup , "packed baby arugula (3 ounces)"))
             .addIngredient(new Ingredient(new BigDecimal(2.00), unit, "medium ripe avocados, sliced"))
             .addIngredient(new Ingredient(new BigDecimal(4.00), unit, "radishes, thinly sliced"))
             .addIngredient(new Ingredient(new BigDecimal(0.50), pint, "cherry tomatoes, halved"))
             .addIngredient(new Ingredient(new BigDecimal(0.25), unit, "red onion, thinly sliced"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), unit, "Roughly chopped cilantro"))
             .addIngredient(new Ingredient(new BigDecimal(0.50), cup , "sour cream thinned with 1/4 cup milk"))
             .addIngredient(new Ingredient(new BigDecimal(1.00), unit, "lime, cut into wedges"));
        log.debug("Adding directions...");
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
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
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        log.debug("Adding notes...");
        tacos.setNotes(new Notes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!"));
        log.debug("Storing recipe...");
        recipes.add(tacos);

        return recipes;
    }

    private Category getCategory(String description) {
        Optional<Category> opt = categoryRepository.findByDescription(description);
        if (!opt.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }
        return opt.get();
    }

    private UnitOfMeasure getUnitOfMeasure(String description) {
        Optional<UnitOfMeasure> opt = unitOfMeasureRepository.findByDescription(description);
        if (!opt.isPresent()) {
            throw new RuntimeException("Expected unit-of-measure not found");
        }
        return opt.get();
    }
}
