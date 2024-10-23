package tacos.email;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class OrderSubmitMessageHandler implements GenericHandler<Order> {
    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    @Override
    public Object handle(Order order, MessageHeaders messageHeaders) {
        restTemplate.postForObject(apiProperties.getUrl(), order, String.class);
        return null;
    }
}
