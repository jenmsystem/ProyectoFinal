package pe.com.project.bank.service;

import pe.com.project.bank.model.Consumption;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IConsumptionService {
    Flux<Consumption> findAll();

    Mono<Consumption> save(Consumption consumption);

    Mono<Consumption> update(Consumption consumption);

    Flux<Consumption> findConsumptionById(Long id);

    Mono<Void> delete(Integer id);
}
