package pe.com.project.bank.service;

import pe.com.project.bank.model.Sale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISaleService {
    Flux<Sale> findAll();

    Mono<Sale> save(Sale sale);

    Mono<Sale> update(Sale sale);

    Flux<Sale> findSaleById(Long id);

    Mono<Void> delete(Integer id);
}
