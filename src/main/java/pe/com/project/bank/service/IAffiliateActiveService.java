package pe.com.project.bank.service;

import pe.com.project.bank.model.AffiliateActive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAffiliateActiveService {
    Flux<AffiliateActive> findAll();

    Mono<AffiliateActive> save(AffiliateActive active);

    Mono<AffiliateActive> update(AffiliateActive active);

    Flux<AffiliateActive> findActiveById(Long id);

    Mono<Void> delete(Integer id);
}
