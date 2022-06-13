package com.recipe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.entity.Recipe;
import com.recipe.exception.RecipeNotFoundException;
import com.recipe.repository.IRecipeRepository;
import com.recipe.services.IRecipeService;

/*@RestController : It is used for making restful web services. And is used at the class level, 
it allows the class to handle the requests made by the client.*/

@RestController

/*@RequestMapping : It is used to map web requests onto specific handler classes 
and/or handler methods.*/

@RequestMapping("/recipes")
public class RecipeController {
	
/*@Autowired : It provides more fine-grained control over where and how autowiring 
should be accomplished.*/

	@Autowired
	IRecipeService recipeService;

	@Autowired
	IRecipeRepository recipeRepository;
	
/*@GetMapping : It is used for mapping HTTP GET requests onto specific handler methods.*/

	@GetMapping("/allRecipes")
	public ResponseEntity<?> getAllRecipes() throws RecipeNotFoundException {

		if (!recipeRepository.findAll().isEmpty()) {

			return new ResponseEntity<>(recipeService.recipesList(), HttpStatus.OK);

		} else {

			throw new RecipeNotFoundException("No recipes found in the list ");

		}
	}

/*@PostMapping : It is used for mapping HTTP POST requests onto specific handler methods.*/
	@PostMapping("/addRecipe")
	public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) throws RecipeNotFoundException {

		Optional<Recipe> opt = recipeRepository.findByName(recipe.getRname());

		if (opt.isPresent()) {
			throw new RecipeNotFoundException("Recipe already exists");

		} else {

			recipeService.addRecipe(recipe);

			return new ResponseEntity<>(" Recipe is SUCCESSFULLY added ", HttpStatus.CREATED);

		}
	}

/*@PutMapping : PUT HTTP method is used to update/modify the resource so @PutMapping 
annotation is used for mapping HTTP PUT requests onto specific handler methods.*/
	
	@PutMapping("/updateRecipe/{rid}")
	public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe) throws RecipeNotFoundException {

		if (recipeRepository.existsById(recipe.getRid())) {

			recipeService.updateRecipe(recipe);

			return new ResponseEntity<>(" Recipe " + recipe.getRid() + " is successfully updated in the list ",
					HttpStatus.ACCEPTED);

		} else {
			throw new RecipeNotFoundException("Recipe with "  + recipe.getRid() +  " is not found in the list ");
		}

	}

/*@DeleteMapping : This annotation maps HTTP DELETE requests onto specific handler methods*/
	@DeleteMapping("/deleteMapping/{rid}")
	public ResponseEntity<String> deleteRecipe(@PathVariable("rid") int rid) throws RecipeNotFoundException {

		Optional<Recipe> opt = recipeRepository.findById(rid);

		if (opt.isPresent()) {

			recipeService.deleteRecipe(rid);

			return new ResponseEntity<>("Recipe with : " + rid + " is successfully deleted fom the list", HttpStatus.OK);
		} else {

			throw new RecipeNotFoundException(" Recipe with : " + rid + " is not found in the list ");

		}
	}

	@GetMapping("/{rid}")
	public ResponseEntity<?> getRecipe(@PathVariable("rid") int rid) throws RecipeNotFoundException {

		Optional<Recipe> opt = recipeRepository.findById(rid);

		if (opt.isPresent()) {

			return new ResponseEntity<>( recipeService.getRecipe(rid), HttpStatus.OK);

		}

		else {

			throw new RecipeNotFoundException(" Recipe with  " + rid + " is not found from the list ");
		}

	}

}
