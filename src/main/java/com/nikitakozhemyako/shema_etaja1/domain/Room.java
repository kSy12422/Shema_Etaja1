package com.nikitakozhemyako.shema_etaja1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Room {
    private int numberFloor; //номер этажа 1
    private int NumberRoom; //номер помещения 7
    private String predestination; //предназначение пом. 2
    private double square; //площадь помещения 3
    private int countDoor; //кол-во дверей 4
    private int countWindow; //кол-во окон 5
    private boolean conditioner; //наличие кондиционера 6
}
