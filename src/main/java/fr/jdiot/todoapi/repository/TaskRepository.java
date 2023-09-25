package fr.jdiot.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.jdiot.todoapi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
