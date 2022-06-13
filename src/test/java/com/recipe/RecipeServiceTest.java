package com.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.recipe.entity.Recipe;
import com.recipe.repository.IRecipeRepository;
import com.recipe.services.IRecipeService;


/*@SpringBootTest: This annotation is useful when we need to bootstrap the entire container. 
The annotation works by creating the ApplicationContext that will be utilized in our tests*/
@SpringBootTest

public class RecipeServiceTest {
	
	@Autowired
	IRecipeService recipeService;

/*@MockBean: It allow to mock a class or an interface and to record and verify behaviors on it.*/	
	@MockBean
	IRecipeRepository recipeRepository;
	
	public static Recipe recipe,recipe1,recipe2;
	
/*@BeforeAll: It is used to signal that the annotated method should be executed before all 
tests in the current test class.*/	
	@BeforeAll
	public static void setUp() {
		recipe=new Recipe();
		recipe1=new Recipe();
		recipe2=new Recipe();
		
		
		recipe.setRid(1);
		recipe.setRname("Chicken fry");
		recipe.setCreatedOn(LocalDate.now());
		recipe.setDS(4);
		
		recipe1.setRid(4);
		recipe1.setRname("Chicken Biryani");
		recipe1.setCreatedOn(LocalDate.now());
		recipe1.setDS(6);
	}
	
/*@Test: This annotation is the important part, where we write code/business logic. If something 
needs to be automated, that particular code needs to be inserted into the test method. The test method then executes @Test by passing attributes.*/	
	@Test
	
/*@DisplayName: This is used to declare a custom display name for the annotated test 
class or test method. */	
	@DisplayName("Test case of create Recipe")
	void createRecipeTest() {
		
		recipeRepository.save(recipe);
		assertNotNull(recipe);

	}
	
	
	@Test
	@DisplayName("Positive Test case of create Recipe")
	void createRecipeTest1() {
		
	when(recipeRepository.save(recipe)).thenReturn(recipe);
	assertEquals(recipe, recipeService.addRecipe(recipe));
		
	}
	
	@Test
	@DisplayName("Negative Test case of create Recipe")
	void createRecipeTest2() {
		
	when(recipeRepository.save(recipe)).thenReturn(recipe);
	assertNotEquals(recipe1, recipeService.addRecipe(recipe));
		
	}
	
	
	@Test
	@DisplayName("positive test case of get all Recipes")
	void getAllRecipesTest() {
		
		List<Recipe> listRecipe = new ArrayList<>();
		
		listRecipe.add(recipe);
		listRecipe.add(recipe1);
		listRecipe.add(recipe2);
		
		when(recipeRepository.findAll()).thenReturn(listRecipe);
		assertEquals(listRecipe.size(), recipeService.recipesList().size());
	}
	
	@Test
	@DisplayName("Negative test case of get all Recipes")
	void getAllRecipesTest1() {
		
		List<Recipe> listRecipe = new ArrayList<>();
		
		listRecipe.add(recipe);
		listRecipe.add(recipe1);
		listRecipe.add(recipe2);
		
		when(recipeRepository.findAll()).thenReturn(listRecipe);
		assertNotEquals(4, recipeService.recipesList().size());
	}
	
	@Test
	@DisplayName("Positive test case for get Recipe")
	void getRecipeTest() {
		
		when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe1));
		
		recipe=recipeService.getRecipe(1);
		
		assertEquals(recipe1.toString(), recipe.toString());

	}
	
	@Test
	@DisplayName("Negative test case for get Recipe")
	void getRecipeTest1() {
		
		when(recipeRepository.findById(2)).thenReturn(Optional.of(recipe1));
		
		recipe=recipeService.getRecipe(2);
		
		assertNotEquals(recipe2.toString(), recipe.toString());

	}
	
	

}
