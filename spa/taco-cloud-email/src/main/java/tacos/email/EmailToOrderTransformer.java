package tacos.email;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.ArrayList;

public class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order> {

    private static final String SUBJECT_KEYWORDS = "TACO ORDER";

    @Override
    protected AbstractIntegrationMessageBuilder<Order> doTransform(Message message) {
        var tacoOrder = processPayload(message);
        return MessageBuilder.withPayload(tacoOrder);
    }

    private Order processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email =
                    ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
                String content = mailMessage.getContent().toString();
                return parseEmailToOrder(email, content);
            }
        } catch (MessagingException e) {
        } catch (IOException e) {}
        return null;
    }

    private Order parseEmailToOrder(String email, String content) {
        var order = new Order(email);
        var lines = content.split("\\r?\\n");
        for (var line : lines) {
            if (!line.trim().isEmpty() && line.contains(":")) {
                var lineSplit = line.split(":");
                var tacoName = lineSplit[0].trim();
                var ingredients = lineSplit[1].trim();
                var ingredientsSplit = ingredients.split(",");
                var ingredientCodes = new ArrayList<String>();
                for (var ingredientName : ingredientsSplit) {
                    var code = lookupIngredientCode(ingredientName.trim());
                    if (code != null) {
                        ingredientCodes.add(code);
                    }
                }

                var taco = new Taco(tacoName);
                taco.setIngredients(ingredientCodes);
                order.addTaco(taco);
            }
        }
        return order;
    }

    private String lookupIngredientCode(String ingredientName) {
        for (var ingredient : ALL_INGREDIENTS) {
            String ucIngredientName = ingredientName.toUpperCase();
            if (LevenshteinDistance.getDefaultInstance().apply(ucIngredientName, ingredient.getName()) < 3 ||
                ucIngredientName.contains(ingredient.getName()) ||
                ingredient.getName().contains(ucIngredientName)) {
                return ingredient.getCode();
            }
        }
        return null;
    }

    private static Ingredient[] ALL_INGREDIENTS = new Ingredient[] {
        new Ingredient("FLTO", "FLOUR TORTILLA"),
        new Ingredient("COTO", "CORN TORTILLA"),
        new Ingredient("GRBF", "GROUND BEEF"),
        new Ingredient("CARN", "CARNITAS"),
        new Ingredient("TMTO", "TOMATOES"),
        new Ingredient("LETC", "LETTUCE"),
        new Ingredient("CHED", "CHEDDAR"),
        new Ingredient("JACK", "MONTERREY JACK"),
        new Ingredient("SLSA", "SALSA"),
        new Ingredient("SRCR", "SOUR CREAM")
    };
}
