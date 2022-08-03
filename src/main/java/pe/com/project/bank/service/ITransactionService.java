package pe.com.project.bank.service;

import pe.com.project.bank.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    Flux<Transaction> findAll();

    Mono<Transaction> save(Transaction transaction);

    Mono<Transaction> update(Transaction transaction);

    Flux<Transaction> findTransactionById(Long id);

    Mono<Void> delete(Integer id);
}
