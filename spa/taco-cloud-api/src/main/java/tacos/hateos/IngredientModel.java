package tacos.hateos;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.Ingredient;

@Getter
public class IngredientModel extends RepresentationModel<IngredientModel> {
    private final String name;
    private final Ingredient.Type type;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
