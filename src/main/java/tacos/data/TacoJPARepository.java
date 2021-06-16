package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

public interface TacoJPARepository extends CrudRepository<Taco, Long> {
}
