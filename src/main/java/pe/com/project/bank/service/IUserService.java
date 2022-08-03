package pe.com.project.bank.service;

import pe.com.project.bank.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Flux<User> findAll();

    Mono<User> save(User user);

    Mono<User> update(User user);

    Flux<User> findUserById(Long id);

    Mono<Void> delete(Integer id);
}
