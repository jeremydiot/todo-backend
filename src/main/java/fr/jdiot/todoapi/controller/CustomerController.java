package fr.jdiot.todoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.jdiot.todoapi.model.Customer;
import fr.jdiot.todoapi.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<List<Customer>>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {

        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not exist"));

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.saveOrUpdate(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable int id, @RequestBody Customer customer) {
        Customer customerToSave = customerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not exist"));

        if (customer.getEmail() != null)
            customerToSave.setEmail(customer.getEmail());
        if (customer.getTasks() != null)
            customerToSave.setTasks(customer.getTasks());
        if (customer.getUserName() != null)
            customerToSave.setUserName(customer.getUserName());

        return new ResponseEntity<Customer>(customerService.saveOrUpdate(customerToSave), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        customerService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
