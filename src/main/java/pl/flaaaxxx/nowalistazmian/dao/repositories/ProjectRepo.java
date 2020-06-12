package pl.flaaaxxx.nowalistazmian.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.nowalistazmian.dao.model.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
    Optional<Project> findByNumber(String number);
    Optional<Project> findById(Long id);
    List<Project> findAll();
    void deleteById(Long id);
    List<Project> findAllByOrderByNumberAsc();
    void delete(Project project);
}

