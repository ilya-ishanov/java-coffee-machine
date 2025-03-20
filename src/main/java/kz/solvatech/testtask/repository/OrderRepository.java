package kz.solvatech.testtask.repository;

import kz.solvatech.testtask.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(nativeQuery = true, value = """
        SELECT recipe_id
        FROM orders
        GROUP BY recipe_id
        ORDER BY COUNT(recipe_id) DESC
        LIMIT 1
    """)
    Long findPopularRecipe();
}
