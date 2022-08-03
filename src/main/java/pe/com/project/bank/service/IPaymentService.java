package pe.com.project.bank.service;

import pe.com.project.bank.model.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPaymentService {
    Flux<Payment> findAll();

    Mono<Payment> save(Payment payment);

    Mono<Payment> update(Payment payment);

    Flux<Payment> findPaymentById(Long id);

    Mono<Void> delete(Integer id);
}
