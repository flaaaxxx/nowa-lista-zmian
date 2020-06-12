package pl.flaaaxxx.nowalistazmian.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.nowalistazmian.dao.model.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByProjectId(Long id);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAllByProjectIdOrderByNumberVehicleAsc(Long id);
    void delete(Vehicle vehicle);
    int countAllByProjectId(Long id);
}
