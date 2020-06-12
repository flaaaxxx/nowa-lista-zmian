package pl.flaaaxxx.nowalistazmian.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.flaaaxxx.nowalistazmian.dao.model.Project;
import pl.flaaaxxx.nowalistazmian.dao.model.Vehicle;
import pl.flaaaxxx.nowalistazmian.dao.repositories.VehicleRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private VehicleRepo vehicleRepo;
    private ProjectService projectService;


    @Autowired
    public VehicleService(VehicleRepo vehicleRepo, ProjectService projectService) {
        this.vehicleRepo = vehicleRepo;
        this.projectService = projectService;
    }


/////////////////////////////////////////////////////////////////////////////////////
// utworzenie jednego pojazdu
/////////////////////////////////////////////////////////////////////////////////////

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

/////////////////////////////////////////////////////////////////////////////////////
// utworzenie wszystkich pojazdów do projektu
/////////////////////////////////////////////////////////////////////////////////////

    public List<Vehicle> saveAll(List<Vehicle> vehicleList) {

        if (vehicleList.size() > 0) {
            try {
                Long projectId = vehicleList.get(0).getProject().getId();
                // szukaj wszystkich aktualnie przypisanych pojazdow do projektu
                Optional<List<Vehicle>> currentVehiclesOnProject = Optional.ofNullable(
                        vehicleRepo.findAllByProjectId(projectId));
                // szukaj czy pojazd juz wystepuje na projekcie
                // jeśli tak to usuń go z nowej listy i nie zapisuj dwóch takich samych
                if (currentVehiclesOnProject.isPresent()) {
                    List<Vehicle> vehiclesRepeatedList = vehicleList.stream()
                            .filter(vehicleNew -> currentVehiclesOnProject.get().stream()
                                    .anyMatch(vehicle ->
                                            vehicle.getNumberVehicle() == vehicleNew.getNumberVehicle()))
                            .collect(Collectors.toList());

                    vehicleList.removeAll(vehiclesRepeatedList);
                }
                vehicleRepo.saveAll(vehicleList);
                projectService.updateNrProject(projectId);
            } catch (IllegalArgumentException e) {
                System.out.println("Id nie może być puste.");
            }

        }

        return vehicleList;
    }

    public List<Vehicle> findAllByProjectIdOrderByNumberVehicleAsc(Long id) {
        return vehicleRepo.findAllByProjectIdOrderByNumberVehicleAsc(id);
    }

/////////////////////////////////////////////////////////////////////////////////////
// usunięcie pojazdu o podanym id, aktualizacja ilości pojazdów
/////////////////////////////////////////////////////////////////////////////////////

    public Vehicle delete(Long id) {

        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        vehicle.ifPresent(value -> {
            Long projectId = value.getProject().getId();
            vehicleRepo.delete(value);
            projectService.updateNrProject(projectId);
        });

        return vehicle.orElse(null);
    }

/////////////////////////////////////////////////////////////////////////////////////
// usunięcie wszystkich pojazdów przypisanych do projektu
/////////////////////////////////////////////////////////////////////////////////////

    public List<Vehicle> deleteAll(Long id) {
        List<Vehicle> vehicleList = vehicleRepo.findAllByProjectId(id);
        vehicleRepo.deleteAll(vehicleList);
        return vehicleList;
    }


/////////////////////////////////////////////////////////////////////////////////////
// zwraca ilość pojadów na projekcie
/////////////////////////////////////////////////////////////////////////////////////

    public int countAllByProjectId(Long id) {
        return vehicleRepo.countAllByProjectId(id);
    }
}
