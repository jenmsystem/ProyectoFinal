package pe.com.project.bank.service;

import pe.com.project.bank.model.AffiliatePassive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAffiliatePassiveService {
    Flux<AffiliatePassive> findAll();

    Mono<AffiliatePassive> save(AffiliatePassive passive);

    Mono<AffiliatePassive> update(AffiliatePassive passive);

    Flux<AffiliatePassive> findPassiveById(Long id);

    Mono<Void> delete(Integer id);
}
