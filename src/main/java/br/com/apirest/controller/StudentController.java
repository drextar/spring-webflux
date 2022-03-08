package br.com.apirest.controller;

import br.com.apirest.document.Student;
import br.com.apirest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    public Flux<Student> getStudent() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public Mono<Student> getStudentId(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<Student> save(@RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Student>> getStudentByEvents(){

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<Student> events = service.findAll();
        return Flux.zip(interval, events);

    }

}
