package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

public interface IngredientJPARepository extends CrudRepository<Ingredient, String> {
}
