package com.bookstore.dtos;

import com.bookstore.dtos.base.BaseDto;
import com.bookstore.entities.BookEntity;
import com.bookstore.entities.OrderEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto extends BaseDto {

    @NotNull
    private OrderEntity order;

    @NotNull
    private BookEntity book;

    @NotNull
    private Integer quantity;
}