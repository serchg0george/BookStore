package com.bookstore.dtos;

import com.bookstore.dtos.base.BaseDto;
import com.bookstore.entities.CustomerEntity;
import com.bookstore.entities.OrderItemEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends BaseDto {

    @NotEmpty
    private List<OrderItemEntity> orderItemEntity;

    @NotNull
    private CustomerEntity customer;
}