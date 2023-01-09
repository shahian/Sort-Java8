package com.shahian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String name;
    private int code;
    private double price;
    private Date date;
    private LocalDate localDate;
    private LocalDateTime localDateTime;

}
