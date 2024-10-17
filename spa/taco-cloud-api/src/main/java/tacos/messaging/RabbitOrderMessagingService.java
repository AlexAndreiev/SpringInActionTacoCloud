package tacos.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import tacos.Order;

@RequiredArgsConstructor
public class RabbitOrderMessagingService {
    private final RabbitTemplate rabbitTemplate;

    public void sendOrder(Order order) {
//        var converter = rabbitTemplate.getMessageConverter();
//        var props = new MessageProperties();
//        props.setHeader("X_ORDER_SOURCE", "WEB");
//        var message = converter.toMessage(order, props);
//        rabbitTemplate.send("taco-cloud.order", message);

        rabbitTemplate.convertAndSend("taco-cloud.order", order,
            msg -> {
                msg.getMessageProperties().setHeader("X_ORDER_SOURCE", "WEB");
                return msg;
            });
    }

    public Order receiveOrder() {
//        var message = rabbitTemplate.receive("taco-cloud.orders");
//        return message != null
//            ? (Order) rabbitTemplate.getMessageConverter().fromMessage(message)
//            : null;
        return rabbitTemplate.receiveAndConvert("taco-cloud.orders",
            new ParameterizedTypeReference<>() {});
    }

    @RabbitListener(queues = "taco-cloud.orders")
    public void receiveOrderListener() {
        // so action
    }

}
