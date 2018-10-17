package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "descript";

    private CategoryToCategoryCommand convter;

    @Before
    public void setUp() {
        convter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(convter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = convter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}