package org.application.kingphobe.repository;

import org.application.kingphobe.model.Cart;
import org.application.kingphobe.model.Product;
import org.application.kingphobe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser_UserIdAndProduct_ProductId(int userId, int productId);

    List<Cart> findByUser_UserId(int userId);

    Cart findByUserAndProduct(User user, Product product);
}
