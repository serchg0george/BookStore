package com.bookstore.controller;

import com.bookstore.dto.OrderDto;
import com.bookstore.entity.OrderEntity;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.service.OrderService;
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
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long orderId,
                                                @Valid @RequestBody OrderDto orderDto) {
        OrderEntity orderEntity = orderMapper.mapDtoToEntity(orderDto);
        orderEntity.setId(orderId);
        OrderDto updatedOrder = orderService.updateOrder(orderMapper.mapEntityToDto(orderEntity));
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

}
