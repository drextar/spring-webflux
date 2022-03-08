package br.com.apirest.service;

import br.com.apirest.document.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Flux<Student> findAll();
    Mono<Student> findById(String id);
    Mono<Student> save(Student student);

}
