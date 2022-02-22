package ru.job4j.onetomany;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "modelauto")
public class ModelAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static ModelAuto of(String name) {
        ModelAuto modelAuto = new ModelAuto();
        modelAuto.name = name;
        return modelAuto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelAuto modelAuto = (ModelAuto) o;
        return id == modelAuto.id && Objects.equals(name, modelAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}