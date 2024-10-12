package tacos.hateos;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import tacos.Ingredient;
import tacos.Taco;

import java.util.Date;
import java.util.List;

@Getter
public class TacoModel extends RepresentationModel<TacoModel> {
    private final String name;
    private final CollectionModel<IngredientModel> ingredients;
    private final Date createdAt;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = new IngredientModelAssembler().toCollectionModel(taco.getIngredients());
    }
}
