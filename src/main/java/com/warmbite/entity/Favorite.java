package com.warmbite.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "favorite")
@Data
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long recipeId;
}
