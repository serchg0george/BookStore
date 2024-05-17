package com.bookstore.controller;

import com.bookstore.dto.OrderDto;
import com.bookstore.dto.OrderItemToOrderDto;
import com.bookstore.dto.response.GenericResponse;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bookstore.controller.ResponseStatusConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PutMapping
    public ResponseEntity<OrderDto> setOrderItemToOrder(@RequestBody OrderItemToOrderDto itemToOrderDto) {
        OrderDto orderDto = orderService.setOrderItemToOrder(itemToOrderDto.orderItemId(), itemToOrderDto.orderId());
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderDto) {
        orderService.create(orderDto);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GenericResponse<OrderDto>> getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(orderService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderService.getbyId(orderId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") Long orderId,
                                              @Valid @RequestBody OrderDto orderDto) {
        orderService.update(orderDto, orderId);
        return new ResponseEntity<>(UPDATED_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

}
