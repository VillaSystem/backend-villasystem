package com.example.backendvillasystem.orders_management.interfaces.rest;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import com.example.backendvillasystem.orders_management.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.UpdateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.DeleteOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders_management.domain.services.OrderCommandService;
import com.example.backendvillasystem.orders_management.domain.services.OrderQueryService;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.OrderResource;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.CreateOrderResource;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.UpdateOrderResource;
import com.example.backendvillasystem.orders_management.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import com.example.backendvillasystem.orders_management.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.example.backendvillasystem.orders_management.interfaces.rest.transform.UpdateOrderCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    public OrderController(OrderQueryService orderQueryService, OrderCommandService orderCommandService) {
        this.orderQueryService = orderQueryService;
        this.orderCommandService = orderCommandService;
    }

    @Operation(
            summary = "Create an order",
            description = "Create an order with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        Optional<Orders> orderItem = orderCommandService
                .handle(CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource));
        return orderItem.map(i -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(i), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<List<OrderResource>> getAllOrders() {
        var orders = orderQueryService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(orderResources);
    }

    @Operation(
            summary = "Get order by ID",
            description = "Retrieve a specific order by its unique ID"
    )
    @GetMapping("{id}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long id) {
        Optional<Orders> orderItem = orderQueryService.handle(new GetOrderByIdQuery(id));
        return orderItem.map(i -> ResponseEntity.ok(OrderResourceFromEntityAssembler.toResourceFromEntity(i)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get orders with parameters",
            description = "Retrieve orders with optional query parameters"
    )
    @GetMapping
    public ResponseEntity<?> getOrdersWithParameters(@Parameter(name = "params", hidden = true)
                                                     @RequestParam Map<String, String> params) {
        return getAllOrders();
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Update an order", description = "Update an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResource> updateOrder(@PathVariable Long orderId,
                                                     @RequestBody UpdateOrderResource resource) {
        var updateOrderCommand = UpdateOrderCommandFromResourceAssembler.toCommandFromResource(orderId, resource);
        var updatedOrder = orderCommandService.handle(updateOrderCommand);
        if (updatedOrder.isEmpty()) return ResponseEntity.notFound().build();
        var updatedOrderEntity = updatedOrder.get();
        var updatedOrderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(updatedOrderEntity);
        return ResponseEntity.ok(updatedOrderResource);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Delete an order", description = "Delete an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        var deleteOrderCommand = new DeleteOrderCommand(orderId);
        orderCommandService.handle(deleteOrderCommand);
        return ResponseEntity.ok("Order deleted");
    }
}
