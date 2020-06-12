package pl.flaaaxxx.nowalistazmian.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.flaaaxxx.nowalistazmian.dao.model.Change;
import pl.flaaaxxx.nowalistazmian.dao.model.Item;
import pl.flaaaxxx.nowalistazmian.dao.repositories.ItemRepo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Item save(Item item) {
        return itemRepo.save(item);
    }

    public List<Item> saveAll(List<Item> itemList) {
        return itemRepo.saveAll(itemList);
    }

    public Optional<Item> findById(Long id) {
        Optional<Item> item = itemRepo.findById(id);
        item.ifPresent(item1 -> {
                    try {
                        item1.setStatusMap(new ObjectMapper().readValue(item1.getStatusJson(), new TypeReference<Map<Long, String>>() {
                        }));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return item;
    }

    public List<Item> findAllByChangeId(Long id) {
        List<Item> itemList = itemRepo.findAllByChangeId(id);
        itemList.forEach(
                item -> {
                    try {
                        // zamiana statusów zapisanych jako String na Map<Long, String>
                        item.setStatusMap(new ObjectMapper().readValue(item.getStatusJson(), new TypeReference<Map<Long, String>>() {
                        }));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return itemList;
    }
}
