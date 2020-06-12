package pl.flaaaxxx.nowalistazmian.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "changes")
public class Change {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String number;
    private String title;
    private String description;

    @Lob
    @JsonIgnore
    private String statusJson;

    @Transient // hibernate ignores this field
    private Map<Long, String> statusMap= new HashMap<>();

    @Transient
    private List<Item> itemList = new ArrayList<>();

    @JsonIgnoreProperties({"name", "nrVehicles", "nrWagons", "version", "url", "number"})
    @ManyToOne
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "change")
    private Set<Item> item;

    public Change() {
    }

    public Change(String number, String title, String description, Project project, Map<Long, String> statusMap, List<Item> itemList) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.project = project;
        this.statusMap = statusMap;
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    public String getStatusJson() {
        return statusJson;
    }

    public void setStatusJson(String items) {
        this.statusJson = items;
    }

    public Map<Long, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Long, String> statusMap) {
        this.statusMap = statusMap;
    }


    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
