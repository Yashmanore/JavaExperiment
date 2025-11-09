package com.example.students.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:8080") // allow same host; change if needed
public class StudentRestController {

    private final Map<Long, StudentDto> store = Collections.synchronizedMap(new LinkedHashMap<>());
    private final AtomicLong idGen = new AtomicLong(1);

    // DTO class (can be in separate file)
    public static class StudentDto {
        public Long id;
        public String rollNumber;
        public String firstName;
        public String lastName;
        public String email;

        public StudentDto() {}
        public StudentDto(Long id, String rollNumber, String firstName, String lastName, String email) {
            this.id = id; this.rollNumber = rollNumber; this.firstName = firstName;
            this.lastName = lastName; this.email = email;
        }
    }

    @GetMapping
    public List<StudentDto> list() {
        return new ArrayList<>(store.values());
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto input) {
        long id = idGen.getAndIncrement();
        StudentDto s = new StudentDto(id, input.rollNumber, input.firstName, input.lastName, input.email);
        store.put(id, s);
        return ResponseEntity.status(201).body(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        StudentDto removed = store.remove(id);
        if (removed == null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
