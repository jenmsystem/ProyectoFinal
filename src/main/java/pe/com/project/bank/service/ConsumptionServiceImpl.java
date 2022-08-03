package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.Consumption;
import pe.com.project.bank.repository.IConsumptionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsumptionServiceImpl implements IConsumptionService{

    @Autowired
    private IConsumptionRepository iConsumptionRepository;

    @Override
    public Flux<Consumption> findAll() {
        return iConsumptionRepository.findAll();
    }

    @Override
    public Mono<Consumption> save(Consumption consumption) {
        return iConsumptionRepository.save(consumption);
    }

    @Override
    public Mono<Consumption> update(Consumption consumption) {
        return iConsumptionRepository.findById(consumption.getId().intValue())
                .flatMap(x -> iConsumptionRepository.save(consumption));
    }

    @Override
    public Flux<Consumption> findConsumptionById(Long id) {
        return iConsumptionRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iConsumptionRepository.deleteById(id);
    }
}
