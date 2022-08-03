package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.User;
import pe.com.project.bank.service.IUserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping
    public Flux<User> findAll(){ return iUserService.findAll(); }

    @GetMapping("/{id}")
    public Flux<User> findUserById(@PathVariable("id") Long id){ return  iUserService.findUserById(id);}

    @PostMapping
    public Mono<User> save(@RequestBody User user){ return iUserService.save(user); }

    @PutMapping
    public Mono<User> update(@RequestBody User user){
        return iUserService.update(user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iUserService.delete(id); }
}
