package fr.jdiot.todoapi.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String userName;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Task> tasks;
}
