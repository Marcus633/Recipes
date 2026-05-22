package recipes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    public Optional<List<Recipe>> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    public Optional<List<Recipe>> findByNameIgnoreCaseContainsOrderByDateDesc(String name);
}
