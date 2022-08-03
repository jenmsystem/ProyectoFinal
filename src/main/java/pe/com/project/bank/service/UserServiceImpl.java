package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.User;
import pe.com.project.bank.repository.IUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public Flux<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Mono<User> save(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public Mono<User> update(User user) {
        return iUserRepository.findById(user.getId().intValue())
                .flatMap(x -> iUserRepository.save(user));
    }

    @Override
    public Flux<User> findUserById(Long id) {
        return iUserRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iUserRepository.deleteById(id);
    }
}
