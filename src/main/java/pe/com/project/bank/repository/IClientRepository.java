package pe.com.project.bank.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.project.bank.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IClientRepository extends ReactiveCrudRepository<Client, Integer> {
    @Query("{'document': ?0}")
    Flux<Client> searchByDocument(String document);
    @Query("{'id': ?0}")
    Mono<Void> delete(Integer id);
}
