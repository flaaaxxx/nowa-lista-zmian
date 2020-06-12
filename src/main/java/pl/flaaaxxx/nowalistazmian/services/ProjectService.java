package pl.flaaaxxx.nowalistazmian.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.flaaaxxx.nowalistazmian.dao.model.Project;
import pl.flaaaxxx.nowalistazmian.dao.model.Vehicle;
import pl.flaaaxxx.nowalistazmian.dao.repositories.ProjectRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepo projectRepo;
    private VehicleService vehicleService;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired
    public void setVehicleService(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }
/////////////////////////////////////////////////////////////////////////////////////
// zapisanie projektu
/////////////////////////////////////////////////////////////////////////////////////

    public Project save(Project project) {
        // szukanie w bazie danych projektu o tym samym numerze
        Optional<Project> projectTemp = projectRepo.findByNumber(project.getNumber());

        // jeśli znaleziono projekt o takim samym numerze, ale innym id - błąd
        if (projectTemp.isPresent() && !projectTemp.get().getId().equals(project.getId())) {
            System.out.println("---------------------------------");
            System.out.println("Projekt o podanym numerze istnieje w bazie danych.");
            System.out.println("---------------------------------");
            return null;
        }

        projectRepo.save(project);
        return project;
    }

/////////////////////////////////////////////////////////////////////////////////////
// usunięcie projektu
/////////////////////////////////////////////////////////////////////////////////////

    public Project delete(Long id) {
        Optional<Project> project = findById(id);
        if (project.isPresent()) {
            vehicleService.deleteAll(id);
            projectRepo.delete(project.get());
        }
        return project.orElse(null);
    }

/////////////////////////////////////////////////////////////////////////////////////
// szukanie projektu
/////////////////////////////////////////////////////////////////////////////////////

    public Optional<List<Project>> findAll() {
        return Optional.ofNullable(projectRepo.findAll());
    }

    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(projectRepo.findById(id).orElse(null));
    }

    public Optional<Project> findByName(String name) {
        return Optional.ofNullable(projectRepo.findByName(name)).orElse(null);
    }

    public Optional<Project> findByNumber(String number) {
        return Optional.ofNullable(projectRepo.findByNumber(number)).orElse(null);
    }

/////////////////////////////////////////////////////////////////////////////////////
// aktualizacja pola ilości pojazdów na podstawie rzeczywistej ilości pojazdów w bazie danych
/////////////////////////////////////////////////////////////////////////////////////

    public void updateNrProject(Long id) {
        Optional<Project> project = projectRepo.findById(id);
        project.ifPresent(value -> {
            value.setNrVehicles(vehicleService.countAllByProjectId(value.getId()));
            save(value);
        });
    }


}
