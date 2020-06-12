package pl.flaaaxxx.nowalistazmian.dao.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberVehicle;

    @JsonIgnoreProperties({"number", "name", "nrVehicles", "nrWagons", "version", "url" })
    @ManyToOne
    private Project project;

    public Vehicle() {
    }

    public Vehicle(int numberVehicle, Project project) {
        this.numberVehicle = numberVehicle;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberVehicle() {
        return numberVehicle;
    }

    public void setNumberVehicle(int numberVehicle) {
        this.numberVehicle = numberVehicle;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
