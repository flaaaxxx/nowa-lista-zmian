package pl.flaaaxxx.nowalistazmian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.nowalistazmian.dao.model.Project;
import pl.flaaaxxx.nowalistazmian.services.ProjectService;
import pl.flaaaxxx.nowalistazmian.services.VehicleService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final VehicleService vehicleService;

    @Autowired
    public ProjectController(ProjectService projectService, VehicleService vehicleService) {
        this.projectService = projectService;
        this.vehicleService = vehicleService;
    }


/////////////////////////////////////////////////////////////////////////////////////
// lista projektów
/////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/project/show")
    public List<Project> allProjects() {
        return projectService.findAll().orElse(null);
        // http://localhost:8080/show-all-projects-angular
    }

/////////////////////////////////////////////////////////////////////////////////////
// szukanie projektu po id
/////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(path = "/project/id/{id}")
    public Project findById(@PathVariable(value = "id") Long id) {
        return projectService.findById(id).orElse(null);
    }

/////////////////////////////////////////////////////////////////////////////////////
// dodanie nowego projektu
/////////////////////////////////////////////////////////////////////////////////////

    @PostMapping(path = "/project/add")
    public Project save(@RequestBody Project project) {
        return projectService.save(project);
    }

/////////////////////////////////////////////////////////////////////////////////////
// usunięcie projektu
/////////////////////////////////////////////////////////////////////////////////////

    @DeleteMapping(path = "/project/delete/{id}")
    public Project delete(@PathVariable(value = "id", required = true) Long id) {
        Project project = projectService.delete(id);
        if (project != null) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Projekt został skasowany. " + project.getNumber());
            System.out.println("-----------------------------------------------------");
            return project;
        }
        return null;
    }

}
