package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.data.IngredientJPARepository;
import tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientJPARepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientJPARepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String s) {
        var ingredient = ingredientRepository.findById(s);
        return ingredient.get();
    }
}
