package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.Sale;
import pe.com.project.bank.repository.ISaleRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SaleServiceImpl implements ISaleService{
    @Autowired
    ISaleRepository iSaleRepository;

    @Override
    public Flux<Sale> findAll() {
        return iSaleRepository.findAll();
    }

    @Override
    public Mono<Sale> save(Sale sale) {
        return iSaleRepository.save(sale);
    }

    @Override
    public Mono<Sale> update(Sale sale) {
        return iSaleRepository.findById(sale.getId().intValue())
                .flatMap(x -> iSaleRepository.save(sale));
    }

    @Override
    public Flux<Sale> findSaleById(Long id) {
        return iSaleRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iSaleRepository.deleteById(id);
    }
}
