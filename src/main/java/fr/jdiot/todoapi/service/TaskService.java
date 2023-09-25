package fr.jdiot.todoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jdiot.todoapi.model.Task;
import fr.jdiot.todoapi.repository.TaskRepository;

@Service
public class TaskService implements IService<Task> {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveOrUpdate(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public boolean delete(int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
