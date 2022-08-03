package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.Movement;
import pe.com.project.bank.repository.IMovementRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementServiceImpl implements IMovementService{

    @Autowired
    IMovementRepository iMovementRepository;

    @Override
    public Flux<Movement> findAll() {
        return iMovementRepository.findAll();
    }

    @Override
    public Mono<Movement> save(Movement movement) {
        return iMovementRepository.save(movement);
    }

    @Override
    public Mono<Movement> update(Movement movement) {
        return iMovementRepository.findById(movement.getId().intValue())
                .flatMap(x -> iMovementRepository.save(movement));
    }

    @Override
    public Flux<Movement> findMovementById(Long id) {
        return iMovementRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iMovementRepository.deleteById(id);
    }

    @Override
    public Flux<Movement> findByClientIdAndProductId(Long affiliatePassiveId,Long clientId) {
        return iMovementRepository.findAll().filter(x -> x.getAffiliatePassiveId().equals(affiliatePassiveId) && x.getClientId().equals(clientId));
    }
}
