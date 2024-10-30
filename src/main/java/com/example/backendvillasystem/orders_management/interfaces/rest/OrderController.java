package com.example.backendvillasystem.orders_management.interfaces.rest;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders_management.domain.model.commands.UpdateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrdersByEstadoQuery;
import com.example.backendvillasystem.orders_management.domain.services.OrderCommandService;
import com.example.backendvillasystem.orders_management.domain.services.OrderQueryService;
import com.example.backendvillasystem.orders_management.interfaces.rest.dto.OrderDto;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.CreateOrderResource;
import com.example.backendvillasystem.orders_management.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.example.backendvillasystem.orders_management.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/orders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    public OrderController(OrderQueryService orderQueryService, OrderCommandService orderCommandService) {
        this.orderQueryService = orderQueryService;
        this.orderCommandService = orderCommandService;
    }

    // Endpoint para crear una nueva orden
    @Operation(
            summary = "Create an order",
            description = "Create an order with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderResource resource) {
        CreateOrderCommandFromResourceAssembler assembler = new CreateOrderCommandFromResourceAssembler();
        Order createdOrder = orderCommandService.createOrder(assembler.toCommand(resource));

        // Convertir entidad a DTO usando el ensamblador
        OrderResourceFromEntityAssembler orderResourceAssembler = new OrderResourceFromEntityAssembler();
        return new ResponseEntity<>(orderResourceAssembler.toResource(createdOrder), CREATED);
    }

    // Endpoint para obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderQueryService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Usar ensamblador para convertir cada Order a OrderDto
        OrderResourceFromEntityAssembler assembler = new OrderResourceFromEntityAssembler();
        List<OrderDto> orderResources = orders.stream().map(assembler::toResource).toList();
        return ResponseEntity.ok(orderResources);
    }

    // Endpoint para obtener una orden por ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        GetOrderByIdQuery query = new GetOrderByIdQuery(orderId);
        Order order = orderQueryService.getOrderById(query);

        // Convertir a DTO usando ensamblador
        OrderResourceFromEntityAssembler assembler = new OrderResourceFromEntityAssembler();
        return new ResponseEntity<>(assembler.toResource(order), HttpStatus.OK);
    }

    // Endpoint para listar órdenes por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrderDto>> getOrdersByEstado(@PathVariable String estado) {
        GetOrdersByEstadoQuery query = new GetOrdersByEstadoQuery(estado);
        List<Order> orders = orderQueryService.getOrdersByEstado(query);

        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Usar ensamblador para convertir las órdenes a DTO
        OrderResourceFromEntityAssembler assembler = new OrderResourceFromEntityAssembler();
        List<OrderDto> orderResources = orders.stream().map(assembler::toResource).toList();
        return ResponseEntity.ok(orderResources);
    }

    // Endpoint para actualizar el estado de una orden
    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderCommand command) {
        command = new UpdateOrderCommand(orderId, command.getNewStatus());
        Order order = orderCommandService.updateOrderStatus(command);

        // Convertir a DTO usando ensamblador
        OrderResourceFromEntityAssembler assembler = new OrderResourceFromEntityAssembler();
        return new ResponseEntity<>(assembler.toResource(order), HttpStatus.OK);
    }

    // Endpoint para eliminar una orden
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderCommandService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}