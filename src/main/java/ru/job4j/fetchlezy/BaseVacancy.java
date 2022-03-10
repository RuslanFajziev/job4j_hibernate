package ru.job4j.fetchlezy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basevacancy")
public class BaseVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Vacancy> vacancyList = new ArrayList<>();

    public static BaseVacancy of(String nameP) {
        BaseVacancy baseVacancy = new BaseVacancy();
        baseVacancy.name = nameP;
        return baseVacancy;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancyList.add(vacancy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    @Override
    public String toString() {
        return "BaseVacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vacancyList=" + vacancyList
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseVacancy that = (BaseVacancy) o;
        return id == that.id && Objects.equals(name, that.name)
                && Objects.equals(vacancyList, that.vacancyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacancyList);
    }
}