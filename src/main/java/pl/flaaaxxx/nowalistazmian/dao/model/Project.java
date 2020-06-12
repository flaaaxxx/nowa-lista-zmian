package pl.flaaaxxx.nowalistazmian.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String number;
    private int nrVehicles;
    private int nrWagons;
    private String version;
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<Vehicle> vehiclesSet;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<Change> changeSet;

    public Project() {
    }

    public Project(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Project(String name, String number, int nrVehicles, int nrWagons, String version, String url) {
        this.name = name;
        this.number = number;
        this.nrVehicles = nrVehicles;
        this.nrWagons = nrWagons;
        this.version = version;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Vehicle> getVehiclesSet() {
        return vehiclesSet;
    }

    public void setVehiclesSet(Set<Vehicle> vehicleSet) {
        this.vehiclesSet = vehicleSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNrVehicles() {
        return nrVehicles;
    }

    public void setNrVehicles(int nrVehicles) {
        this.nrVehicles = nrVehicles;
    }

    public int getNrWagons() {
        return nrWagons;
    }

    public void setNrWagons(int nrWagons) {
        this.nrWagons = nrWagons;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
