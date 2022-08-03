package pe.com.project.bank.service;

import pe.com.project.bank.model.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPurchaseService {
    Flux<Purchase> findAll();

    Mono<Purchase> save(Purchase purchase);

    Mono<Purchase> update(Purchase purchase);

    Flux<Purchase> findPurchaseById(Long id);

    Mono<Void> delete(Integer id);
}
