package pe.com.project.bank.service;

import pe.com.project.bank.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Flux<Client> findAll();

    Mono<Client> save(Client client);

    Mono<Client> update(Client client);

    Flux<Client> findClientById(Long id);

    Mono<Void> delete(Integer id);

    Flux<Client> searchByDocument(String document);
}
