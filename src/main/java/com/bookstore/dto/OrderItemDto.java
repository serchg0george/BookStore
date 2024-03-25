package com.bookstore.dto;

import com.bookstore.dto.base.BaseDto;
import com.bookstore.entity.BookEntity;
import com.bookstore.entity.OrderEntity;
import jakarta.validation.constraints.Max;
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