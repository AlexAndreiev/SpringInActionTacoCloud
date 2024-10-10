package tacos;

import org.springframework.data.repository.CrudRepository;

public interface IngredientJPARepository extends CrudRepository<Ingredient, String> {
}
