package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;

public interface OrderJPARepository extends CrudRepository<Order, Long> {
}
