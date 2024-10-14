package tacos;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientJPARepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientJPARepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(@NotNull String s) {
        var ingredient = ingredientRepository.findById(s);
        return ingredient.orElse(null);
    }
}
