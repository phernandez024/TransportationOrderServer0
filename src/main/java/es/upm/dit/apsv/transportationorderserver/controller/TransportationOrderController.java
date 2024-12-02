package es.upm.dit.apsv.transportationorderserver.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.upm.dit.apsv.transportationorderserver.model.TransportationOrder;
import es.upm.dit.apsv.transportationorderserver.repository.TransportationOrderRepository;

@RestController
public class TransportationOrderController {
    private final TransportationOrderRepository repository;

    public TransportationOrderController(TransportationOrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transportationorders")
    public List<TransportationOrder> all() {
        return (List<TransportationOrder>) repository.findAll();
    }

    @PostMapping("/transportationorders")
    public TransportationOrder newOrder(@RequestBody TransportationOrder newOrder) {
        return repository.save(newOrder);
    }

    @GetMapping("/transportationorders/{truck}")
    public ResponseEntity<TransportationOrder> getByTruck(@PathVariable String truck) {
        Optional<TransportationOrder> order = repository.findById(truck);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/transportationorders")
    public ResponseEntity<TransportationOrder> update(@RequestBody TransportationOrder updatedOrder) {
        TransportationOrder order = repository.save(updatedOrder);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/transportationorders/{truck}")
    public void deleteOrder(@PathVariable String truck) {
    repository.deleteById(truck);
}
}