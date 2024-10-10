package tacos;

import org.springframework.data.repository.CrudRepository;

public interface TacoJPARepository extends CrudRepository<Taco, Long> {
}
