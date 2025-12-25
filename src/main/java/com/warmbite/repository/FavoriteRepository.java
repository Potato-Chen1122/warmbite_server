package com.warmbite.repository;

import com.warmbite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, String> {
    Optional<List<Favorite>> findByUserId(Long userId);

    @Query("select f from Favorite f where f.userId=:userId and f.recipeId=:recipeId")
    Optional<Favorite> findToggle(Long userId,Long recipeId);
}
