package recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@Validated
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long>> saveRecipe(@Valid @RequestBody Recipe recipe){
        return ResponseEntity.ok(Map.of("id", recipeService.saveRecipe(recipe)));
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id){
        Optional<Recipe> recipe = recipeService.getRecipe(id);
        return recipe.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@AuthenticationPrincipal UserDetails details, @PathVariable int id){
        if (recipeService.getRecipe(id).isPresent()) {
            if(!details.getUsername().equals(recipeService.getRecipe(id).get().getUser().getEmail())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return recipeService.deleteRecipe(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@AuthenticationPrincipal UserDetails details, @Valid @RequestBody Recipe recipe, @PathVariable long id){
        if (recipeService.getRecipe(id).isPresent()) {
            if(!details.getUsername().equals(recipeService.getRecipe(id).get().getUser().getEmail())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return recipeService.updateRecipe(recipe,id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/recipe/search/")
    public ResponseEntity<String> searchRecipe(@RequestParam(required = false) Optional<String> category, @RequestParam(required = false) Optional<String> name){
        if((category.isPresent() && name.isPresent() || (!category.isPresent() && !name.isPresent()))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if (!category.isEmpty()){
            return new ResponseEntity<>(recipeService.searchCategory(category.get()),HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(recipeService.searchName(name.get()), HttpStatus.OK);
        }
    }



}
