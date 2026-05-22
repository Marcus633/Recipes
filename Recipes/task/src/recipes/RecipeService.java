package recipes;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public long saveRecipe(Recipe recipe) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user  = new User();
        user.setEmail(details.getUsername());
        user.setPassword(details.getPassword());
        user.getRecipeList().add(recipe);
        recipe.setUser(user);
        recipeRepository.save(recipe);
        return recipe.getRecipe_id();
    }

    public Optional<Recipe> getRecipe(long id) {
        return recipeRepository.findById(id);
    }

    public boolean deleteRecipe(long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateRecipe(Recipe recipe, long id) {
        if (recipeRepository.existsById(id)) {
            Recipe updateRecipe = recipeRepository.findById(id).get();
            updateRecipe.setDate(LocalDateTime.now());
            updateRecipe.setName(recipe.getName());
            updateRecipe.setCategory(recipe.getCategory());
            updateRecipe.setDescription(recipe.getDescription());
            updateRecipe.setDirections(recipe.getDirections());
            updateRecipe.setIngredients(recipe.getIngredients());
            recipeRepository.save(updateRecipe);
            return true;
        }
        return false;
    }

    public String searchCategory(String category) {
        Optional<List<Recipe>> list = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        return list.map(JSONArray::toJSONString).orElseGet(() -> JSONArray.toJSONString(Collections.emptyList()));
    }

    public String searchName(String name) {
        Optional<List<Recipe>> list = recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
        return list.map(JSONArray::toJSONString).orElseGet(() -> JSONArray.toJSONString(Collections.emptyList()));
    }


}
