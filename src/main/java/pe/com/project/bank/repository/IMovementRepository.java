package pe.com.project.bank.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.project.bank.model.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IMovementRepository extends ReactiveCrudRepository<Movement, Integer> {
    @Query("{'id': ?0}")
    Mono<Void> delete(Integer id);

    @Query("{''affiliatePassiveId': ?1, clientId': ?0}")
    Flux<Movement> findByClientIdAndProductId(Long affiliatePassiveId,Long clientId);
}
