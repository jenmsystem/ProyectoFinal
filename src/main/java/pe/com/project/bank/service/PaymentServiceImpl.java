package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.Payment;
import pe.com.project.bank.repository.IPaymentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    IPaymentRepository iPaymentRepository;

    @Override
    public Flux<Payment> findAll() {
        return iPaymentRepository.findAll();
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        return iPaymentRepository.save(payment);
    }

    @Override
    public Mono<Payment> update(Payment payment) {
        return iPaymentRepository.findById(payment.getId().intValue())
                .flatMap(x -> iPaymentRepository.save(payment));
    }

    @Override
    public Flux<Payment> findPaymentById(Long id) {
        return iPaymentRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iPaymentRepository.deleteById(id);
    }
}
