package pl.flaaaxxx.nowalistazmian.dao.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String listItem;
    private String oldIndexListItem;
    private String newIndexListItem;
    private String drawing;
    private String oldIndexDrawing;
    private String newIndexDrawing;

    @JsonIgnore
    @Lob // LOB or Large Object refers to a variable length datatype for storing large objects.
    private String statusJson;

    @Transient // hibernate ignores this field
    private Map<Long, String> statusMap = new HashMap<>();

    @JsonIgnore
    @ManyToOne
    private Change change;

    public Item() {
    }

    public Item(String listItem,
                String oldIndexListItem,
                String newIndexListItem,
                String drawing,
                String oldIndexDrawing,
                String newIndexDrawing,
                Map<Long, String> statusMap) {
        this.listItem = listItem;
        this.oldIndexListItem = oldIndexListItem;
        this.newIndexListItem = newIndexListItem;
        this.drawing = drawing;
        this.oldIndexDrawing = oldIndexDrawing;
        this.newIndexDrawing = newIndexDrawing;
        this.statusMap = statusMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListItem() {
        return listItem;
    }

    public void setListItem(String listItem) {
        this.listItem = listItem;
    }

    public String getOldIndexListItem() {
        return oldIndexListItem;
    }

    public void setOldIndexListItem(String oldIndexListItem) {
        this.oldIndexListItem = oldIndexListItem;
    }

    public String getNewIndexListItem() {
        return newIndexListItem;
    }

    public void setNewIndexListItem(String newIndexListItem) {
        this.newIndexListItem = newIndexListItem;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public String getOldIndexDrawing() {
        return oldIndexDrawing;
    }

    public void setOldIndexDrawing(String oldIndexDrawing) {
        this.oldIndexDrawing = oldIndexDrawing;
    }

    public String getNewIndexDrawing() {
        return newIndexDrawing;
    }

    public void setNewIndexDrawing(String newIndexDrawing) {
        this.newIndexDrawing = newIndexDrawing;
    }

    public String getStatusJson() {
        return statusJson;
    }

    public void setStatusJson(String statusJson) {
        this.statusJson = statusJson;
    }

    public Map<Long, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Long, String> statusMap) {
        this.statusMap = statusMap;
    }

    public Change getChange() {
        return change;
    }

    public void setChange(Change change) {
        this.change = change;
    }
}
