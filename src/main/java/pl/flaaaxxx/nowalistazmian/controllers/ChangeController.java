package pl.flaaaxxx.nowalistazmian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.nowalistazmian.dao.model.Change;
import pl.flaaaxxx.nowalistazmian.services.ChangeService;

import java.util.List;

@RestController
public class ChangeController {

    private final ChangeService changeService;

    @Autowired
    public ChangeController(ChangeService changeService) {
        this.changeService = changeService;
    }

    @PostMapping(path = "/change/add")
    public Change save(@RequestBody Change change) {
        return changeService.save(change);
    }

    @GetMapping(path = "/change/list/project/{projectId}")
    public List<Change> findAllByProjectId(@PathVariable(value = "projectId") Long projectId) {
        return changeService.findAllByProjectId(projectId);
    }

    @GetMapping(path = "/change/number")
    public List<Change> findByNumber(@PathVariable(value = "number") String number) {
        return changeService.findByNumber(number);
    }

    @GetMapping(path = "/change/{id}")
    public Change findById(@PathVariable(value = "id") Long id) {
        return changeService.findById(id);
    }

}
