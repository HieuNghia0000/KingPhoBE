package org.application.kingphobe.repository;

import org.application.kingphobe.model.UserFavourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavouriteRepository extends JpaRepository<UserFavourites, Integer> {
}
