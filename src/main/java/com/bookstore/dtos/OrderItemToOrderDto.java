package com.bookstore.dtos;

import jakarta.validation.constraints.NotNull;

public record OrderItemToOrderDto(@NotNull Long orderItemId,
                                  @NotNull Long orderId) {
}
