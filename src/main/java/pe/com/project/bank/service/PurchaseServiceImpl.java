package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.Purchase;
import pe.com.project.bank.repository.IPurchaseRepository;
import pe.com.project.bank.repository.ISaleRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PurchaseServiceImpl implements IPurchaseService{

    @Autowired
    IPurchaseRepository iPurchaseRepository;

    @Override
    public Flux<Purchase> findAll() {
        return iPurchaseRepository.findAll();
    }

    @Override
    public Mono<Purchase> save(Purchase purchase) {
        return iPurchaseRepository.save(purchase);
    }

    @Override
    public Mono<Purchase> update(Purchase purchase) {
        return iPurchaseRepository.findById(purchase.getId().intValue())
                .flatMap(x -> iPurchaseRepository.save(purchase));
    }

    @Override
    public Flux<Purchase> findPurchaseById(Long id) {
        return iPurchaseRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iPurchaseRepository.deleteById(id);
    }
}
