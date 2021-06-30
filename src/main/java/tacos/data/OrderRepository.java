package tacos.data;

import tacos.Order;
import tacos.security.User;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);
}
