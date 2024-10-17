package tacos.configuration;

import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import tacos.Order;

import javax.jms.Destination;
import java.util.Map;

@Configuration
public class JmsConfiguration {

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("taco-cloud.order.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter () {
        var converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = Map.of("order", Order.class);
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }
}
