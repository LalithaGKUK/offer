package offer.entities;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findProductsByName(String name);
}
