package pl.flaaaxxx.nowalistazmian.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.nowalistazmian.dao.model.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    Item save(Item item);
    Optional<Item> findById(Long id);
    List<Item> findAllByChangeId(Long id);
    int countAllByChangeId(Long id);
    int countAllByChangeProjectId(Long id);
}
