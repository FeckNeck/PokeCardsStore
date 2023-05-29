package com.feckneck.pokeCardsserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card extends AbstractEntity{
    private String image;
    private String types;
    private String block;
    private String series;
    private int position;
}
