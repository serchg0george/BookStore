package com.bookstore.controllers;

import com.bookstore.dtos.OrderItemDto;
import com.bookstore.dtos.response.GenericResponse;
import com.bookstore.services.OrderItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bookstore.controllers.ResponseStatusConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order_item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<String> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto) {
        orderItemService.create(orderItemDto);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GenericResponse<OrderItemDto>> getAllOrderItems(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(orderItemService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("id") Long orderItemId) {
        return new ResponseEntity<>(orderItemService.getById(orderItemId), HttpStatus.OK);
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
