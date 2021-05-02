package io.zerogone.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
