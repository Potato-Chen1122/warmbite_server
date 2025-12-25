package com.warmbite.controller;

import com.warmbite.dto.Response;
import com.warmbite.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public Response<Set<String>> getFavorites(@RequestParam String userId) {
        Set<String> ids = favoriteService.getFavoriteIds(userId);
        return Response.buildSuccess(ids);
    }

    @PostMapping("/toggle")
    public Response<String> toggleFavorite(@RequestParam String userId,@RequestParam String recipeId) {
        switch (favoriteService.toggleFavorite(userId, recipeId)) {
            case 1:
                return Response.buildSuccess("已收藏");
            case 0:
                return Response.buildSuccess("已取消收藏");
            default:
                return Response.buildFailure("收藏操作失效", "500");
        }
    }
}
