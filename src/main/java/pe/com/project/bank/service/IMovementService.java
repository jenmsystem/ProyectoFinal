package pe.com.project.bank.service;

import pe.com.project.bank.model.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovementService {
    Flux<Movement> findAll();

    Mono<Movement> save(Movement movement);

    Mono<Movement> update(Movement movement);

    Flux<Movement> findMovementById(Long id);

    Mono<Void> delete(Integer id);

    Flux<Movement> findByClientIdAndProductId(Long clientId,Long productId);
}
