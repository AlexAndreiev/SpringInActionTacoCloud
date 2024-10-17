package tacos.messaging;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import tacos.Order;

@RequiredArgsConstructor
public class KafkaOrderMessagingService {
    private static final Logger log = LoggerFactory.getLogger(KafkaOrderMessagingService.class);
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrder(Order order) {
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }

    public void sendOrderDefault(Order order) {
        kafkaTemplate.sendDefault(order);
    }

    @KafkaListener(topics = "tacocloid.orders.topic")
    public void handle(Order order){
        // some actions
    }

    @KafkaListener(topics = "tacocloid.orders.topic")
    public void handle(Order order, ConsumerRecord<String, Order> record){
        log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
    }

    @KafkaListener(topics = "tacocloid.orders.topic")
    public void handle(Order order, Message<Order> message){
        var headers = message.getHeaders();
        log.info("Received from partition {} with timestamp {}",
            headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
            headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
    }
}
