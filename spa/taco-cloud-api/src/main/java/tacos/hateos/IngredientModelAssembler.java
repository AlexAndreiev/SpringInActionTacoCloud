package tacos.hateos;

import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Ingredient;
import tacos.web.controller.IngredientController;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {


    public IngredientModelAssembler() {
        super(IngredientController.class, IngredientModel.class);
    }

    @NotNull
    @Override
    public IngredientModel toModel(@NotNull Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @NotNull
    @Override
    protected IngredientModel instantiateModel(@NotNull Ingredient entity) {
        return new IngredientModel(entity);
    }
}
