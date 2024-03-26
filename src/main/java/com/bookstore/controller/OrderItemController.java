package com.bookstore.controller;

import com.bookstore.dto.OrderItemDto;
import com.bookstore.entity.OrderItemEntity;
import com.bookstore.mapper.OrderItemMapper;
import com.bookstore.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookstore.controller.ResponseStatusConstants.CREATED_SUCCESS;
import static com.bookstore.controller.ResponseStatusConstants.DELETED_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order_item")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    @PostMapping
    public ResponseEntity<String> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto) {
        orderItemService.createOrderItem(orderItemDto);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        return new ResponseEntity<>(orderItemService.getAllOrderItems(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("id") Long orderItemId) {
        return new ResponseEntity<>(orderItemService.getOrderItemById(orderItemId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable("id") Long orderItemId,
                                                        @Valid @RequestBody OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = orderItemMapper.mapDtoToEntity(orderItemDto);
        orderItemEntity.setId(orderItemId);
        OrderItemDto updatedOrderItem = orderItemService.updateOrderItem(orderItemMapper.mapEntityToDto(orderItemEntity));
        return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

}
