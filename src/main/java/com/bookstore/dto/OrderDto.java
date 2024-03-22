package com.bookstore.dto;

import com.bookstore.dto.base.BaseDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.OrderItemEntity;
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

    @NotNull
    private List<OrderItemEntity> orderItemEntity;

    @NotNull
    private CustomerEntity customer;
}