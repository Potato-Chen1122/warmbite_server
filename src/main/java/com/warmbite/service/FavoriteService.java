package com.warmbite.service;

import com.warmbite.entity.Favorite;
import com.warmbite.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public Set<String> getFavoriteIds(String userId) {
        if(favoriteRepository.findByUserId(Long.valueOf(userId)).isPresent()) {
            System.out.printf("[#]userId %s get\n",userId);
            return favoriteRepository.findByUserId(Long.valueOf(userId)).get().stream().map((Favorite::getRecipeId)).map(String::valueOf).collect(Collectors.toSet());
        }return new HashSet<>();
    }

    public int toggleFavorite(String userId,String recipeId) {
        if(favoriteRepository.findToggle(Long.valueOf(userId),Long.valueOf(recipeId)).isPresent()){
            Favorite favorite=favoriteRepository.findToggle(Long.valueOf(userId),Long.valueOf(recipeId)).get();
            favoriteRepository.delete(favorite);
            System.out.printf("[#]userId %s remove %s\n",userId,recipeId);
            return 0;
        }else {
            Favorite favorite=new Favorite();
            favorite.setUserId(Long.valueOf(userId));
            favorite.setRecipeId(Long.valueOf(recipeId));
            favoriteRepository.save(favorite);
            System.out.printf("[#]userId %s favorite %s\n",userId,recipeId);
            return 1;
        }
    }
}
