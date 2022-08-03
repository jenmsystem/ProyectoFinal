package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.AffiliatePassive;
import pe.com.project.bank.repository.IAffiliatePassiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AffiliatePassiveServiceImpl implements IAffiliatePassiveService {

    @Autowired
    IAffiliatePassiveRepository iPassiveRepository;

    @Override
    public Flux<AffiliatePassive> findAll() {
        return iPassiveRepository.findAll();
    }

    @Override
    public Mono<AffiliatePassive> save(AffiliatePassive passive) {
        return iPassiveRepository.save(passive);
    }

    @Override
    public Mono<AffiliatePassive> update(AffiliatePassive passive) {
        return iPassiveRepository.findById(passive.getId().intValue())
                .flatMap(x -> iPassiveRepository.save(passive));
    }

    @Override
    public Flux<AffiliatePassive> findPassiveById(Long id) {
        return iPassiveRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iPassiveRepository.deleteById(id);
    }
}
