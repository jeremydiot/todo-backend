package fr.jdiot.todoapi.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();

    T saveOrUpdate(T o);

    Optional<T> findById(int id);

    boolean delete(int id);

}
