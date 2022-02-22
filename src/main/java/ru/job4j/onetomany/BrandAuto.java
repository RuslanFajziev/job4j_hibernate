package ru.job4j.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brandauto")
public class BrandAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelAuto> models = new ArrayList<>();

    public static BrandAuto of(String name) {
        BrandAuto brandAuto = new BrandAuto();
        brandAuto.name = name;
        return brandAuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelAuto> getModels() {
        return models;
    }

    public void setModels(List<ModelAuto> models) {
        this.models = models;
    }

    public void addModel(ModelAuto modelAuto) {
        this.models.add(modelAuto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BrandAuto brandAuto = (BrandAuto) o;
        return id == brandAuto.id && Objects.equals(name, brandAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}