package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import pe.com.project.bank.model.Transaction;
import pe.com.project.bank.repository.ISaleRepository;
import pe.com.project.bank.repository.ITransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransactionServiceImpl implements ITransactionService{

    @Autowired
    ITransactionRepository iTransactionRepository;

    @Override
    public Flux<Transaction> findAll() {
        return iTransactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return iTransactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return iTransactionRepository.findById(transaction.getId().intValue())
                .flatMap(x -> iTransactionRepository.save(transaction));
    }

    @Override
    public Flux<Transaction> findTransactionById(Long id) {
        return iTransactionRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iTransactionRepository.deleteById(id);
    }
}
