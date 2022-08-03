package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.AffiliateActive;
import pe.com.project.bank.repository.IAffiliateActiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AffiliateActiveServiceImpl implements IAffiliateActiveService {
    @Autowired
    IAffiliateActiveRepository iActiveRepository;

    @Override
    public Flux<AffiliateActive> findAll() {
        return iActiveRepository.findAll();
    }

    @Override
    public Mono<AffiliateActive> save(AffiliateActive active) {
        return iActiveRepository.save(active);
    }

    @Override
    public Mono<AffiliateActive> update(AffiliateActive active) {
        return iActiveRepository.findById(active.getId().intValue())
                .flatMap(x -> iActiveRepository.save(active));
    }

    @Override
    public Flux<AffiliateActive> findActiveById(Long id) {
        return iActiveRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iActiveRepository.deleteById(id);
    }
}
