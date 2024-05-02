package com.bookstore.controller;

import com.bookstore.dto.OrderItemDto;
import com.bookstore.mapper.OrderItemMapper;
import com.bookstore.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookstore.controller.ResponseStatusConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order_item")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    @PostMapping
    public ResponseEntity<String> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto) {
        orderItemService.create(orderItemDto);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        return new ResponseEntity<>(orderItemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("id") Long orderItemId) {
        return new ResponseEntity<>(orderItemService.getbyId(orderItemId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateOrderItem(@PathVariable("id") Long orderItemId,
                                                  @Valid @RequestBody OrderItemDto orderItemDto) {
        orderItemService.update(orderItemDto, orderItemId);
        return new ResponseEntity<>(UPDATED_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long orderItemId) {
        orderItemService.delete(orderItemId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

}
