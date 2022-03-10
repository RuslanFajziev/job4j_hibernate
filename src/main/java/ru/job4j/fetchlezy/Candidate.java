package ru.job4j.fetchlezy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private BaseVacancy baseVacancy;

    public static Candidate of(String nameP, BaseVacancy baseVacancyP) {
        Candidate candidate = new Candidate();
        candidate.name = nameP;
        candidate.baseVacancy = baseVacancyP;
        return candidate;
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

    public BaseVacancy getBaseVacancy() {
        return baseVacancy;
    }

    public void setBaseVacancy(BaseVacancy baseVacancy) {
        this.baseVacancy = baseVacancy;
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", baseVacancy=" + baseVacancy
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
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Objects.equals(name, candidate.name)
                && Objects.equals(baseVacancy, candidate.baseVacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, baseVacancy);
    }
}