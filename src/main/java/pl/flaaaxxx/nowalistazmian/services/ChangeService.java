package pl.flaaaxxx.nowalistazmian.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.flaaaxxx.nowalistazmian.dao.model.Change;
import pl.flaaaxxx.nowalistazmian.dao.model.Item;
import pl.flaaaxxx.nowalistazmian.dao.repositories.ChangeRepo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChangeService {

    private ChangeRepo changeRepo;
    private ItemService itemService;

    @Autowired
    public ChangeService(ChangeRepo changeRepo, ItemService itemService) {
        this.changeRepo = changeRepo;
        this.itemService = itemService;
    }

    public Change save(Change change) {
        Map<Long, String> statusMap = new HashMap<>();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 300; i++) {
                statusMap.put(Long.parseLong(String.valueOf(i)), "ok");
            }
            try {
                change.setStatusJson(new ObjectMapper().writeValueAsString(statusMap));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            change.setId(Long.parseLong(String.valueOf(j)));
            changeRepo.save(change);
        }
        return null;
    }

    public List<Change> findByNumber(String number) {
        return changeRepo.findByNumber(number);
    }

    public List<Change> findAllByProjectId(Long id) {
        List<Change> changeList = changeRepo.findAllByProjectId(id);
        changeList.forEach(
                change -> {
                    change.setItemList(itemService.findAllByChangeId(change.getId())); // przypisanie wszystkich sztuklist do zmiany
                    try {
                        // zamiana statusów zapisanych jako String na Map<Long, String>
                        change.setStatusMap(new ObjectMapper().readValue(change.getStatusJson(), new TypeReference<Map<Long, String>>(){}));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return changeList;
    }

    public Change findById(Long id) {
        changeRepo.findById(id).ifPresent(change -> change.setItemList(itemService.findAllByChangeId(change.getId())));
        return changeRepo.findById(id).orElse(null);
    }
}
