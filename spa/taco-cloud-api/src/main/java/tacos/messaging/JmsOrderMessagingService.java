package tacos.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import tacos.Order;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
@RequiredArgsConstructor
public class JmsOrderMessagingService {
    private final JmsTemplate jmsTemplate;
    private final Destination orderQueue;
    private final MessageConverter messageConverter;

    public void sendOrder(Order order) {
//        jmsTemplate.send(session -> session.createObjectMessage(order));
//        jmsTemplate.send(orderQueue,
//            session -> session.createObjectMessage(order));
//        jmsTemplate.send("taco-cloud.order.queue",
//            session -> session.createObjectMessage(order));
        jmsTemplate.convertAndSend(order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

    public Order receiveOrder() throws JMSException {
//        var message = jmsTemplate.receive();
//        return (Order) messageConverter.fromMessage(message);
        return (Order) jmsTemplate.receiveAndConvert();
    }

    @JmsListener(destination = "taco-cloud.order.queue")
    public void receiveOrderListener(Order order) {
        // some actions
    }

}
