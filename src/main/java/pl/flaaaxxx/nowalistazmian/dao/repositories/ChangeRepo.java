package pl.flaaaxxx.nowalistazmian.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.nowalistazmian.dao.model.Change;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChangeRepo extends JpaRepository<Change, Long> {
    List<Change> findByNumber(String number);
    List<Change> findByNumberContains(String number);
    List<Change> findByNumberContaining(String number);
    List<Change>  findAllByProjectId(Long id);
    List<Change> findAll();
    Optional<Change> findById(Long id);
    Change save(Change change);
    int countAllByProjectId(Long id);
}
