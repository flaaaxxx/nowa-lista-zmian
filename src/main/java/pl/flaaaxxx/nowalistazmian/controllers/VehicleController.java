package pl.flaaaxxx.nowalistazmian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.nowalistazmian.dao.model.Vehicle;
import pl.flaaaxxx.nowalistazmian.services.VehicleService;

import java.util.List;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(path = "/vehicle/add-vehicle")
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @PostMapping(path = "/vehicle/add-vehicles")
    public List<Vehicle> saveAll(@RequestBody List<Vehicle> vehicleList) {
        return vehicleService.saveAll(vehicleList);
    }

    @GetMapping(path = "/vehicle/find-all/{id}")
    public List<Vehicle> findAllByProjectIdOrderByNumberVehicleAsc(@PathVariable(value = "id") Long id) {
        return vehicleService.findAllByProjectIdOrderByNumberVehicleAsc(id);
    }

    @DeleteMapping(path = "/vehicle/delete/{id}")
    public Vehicle delete(@PathVariable(value = "id") Long id) {
        return vehicleService.delete(id);
    }

    @DeleteMapping(path = "/vehicle/delete/project/{id}")
    public List<Vehicle> deleteAll(@PathVariable(value = "id") Long id) {
        return vehicleService.deleteAll(id);
    }

    @GetMapping(path = "/vehicle/count/{id}")
    public int countAllByProjectId(@PathVariable(value = "id") Long id) {
        return vehicleService.countAllByProjectId(id);
    }
}
