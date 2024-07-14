package org.application.kingphobe.repository;

import org.application.kingphobe.model.UserFavourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavouriteRepository extends JpaRepository<UserFavourites, Integer> {

    List<UserFavourites> findByUserUserId(Integer userId);

    Optional<UserFavourites> findByUserUserIdAndProductProductId(Integer userId, Integer productId);
}

