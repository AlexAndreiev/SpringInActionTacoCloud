package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.model.Ingredient;

public interface IngredientJPARepository extends CrudRepository<Ingredient, String> {
}
