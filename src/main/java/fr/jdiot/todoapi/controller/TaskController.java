package fr.jdiot.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.jdiot.todoapi.model.Customer;
import fr.jdiot.todoapi.model.Task;
import fr.jdiot.todoapi.service.CustomerService;
import fr.jdiot.todoapi.service.TaskService;

@RestController
@RequestMapping("/customer/{customerId}/task")
public class TaskController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Task> create(@PathVariable int customerId, @RequestBody Task task) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        task.setCustomer(customer);

        System.out.println(customer);

        return new ResponseEntity<>(taskService.saveOrUpdate(task), HttpStatus.OK);
    }
}
