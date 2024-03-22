package com.bookstore.dto;

import jakarta.validation.constraints.NotNull;

public record OrderItemToOrderDto(@NotNull Long orderItemId,
                                  @NotNull Long orderId) {
}
