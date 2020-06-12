package pl.flaaaxxx.nowalistazmian.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.nowalistazmian.dao.model.Change;
import pl.flaaaxxx.nowalistazmian.dao.model.Item;
import pl.flaaaxxx.nowalistazmian.services.ChangeService;
import pl.flaaaxxx.nowalistazmian.services.ItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    private final ItemService itemService;
    private final ChangeService changeService;

    public ItemController(ItemService itemService, ChangeService changeService) {
        this.itemService = itemService;
        this.changeService = changeService;
    }

    @PostMapping(path = "/item/add")
    public Item save(@RequestBody Item item) {

        for (int j=0; j<5; j++) {
            Map<Long, String> statusMap = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                statusMap.put(Long.parseLong(String.valueOf(i)), "ok");
            }
            try {
                item.setStatusJson(new ObjectMapper().writeValueAsString(statusMap));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            item.setId(Long.parseLong(String.valueOf(j)));
            Change change = changeService.findById(2L);
            item.setChange(change);
            itemService.save(item);
        }
//        return itemRepo.save(item);
        return null;
//        return itemService.save(item);
    }

    @PostMapping(path = "/item/add-all")
    public List<Item> saveAll(@RequestBody List<Item> itemList) {
        return itemService.saveAll(itemList);
    }

    @GetMapping(path = {"/item/{id}"})
    public Item findById(@PathVariable(value = "id") Long id) {
        return itemService.findById(id).orElse(null);
    }

    @GetMapping(path = {"/item/change/{id}"})
    public List<Item> findAllByChangeId(@PathVariable(value = "id") Long id) {
        return itemService.findAllByChangeId(id);
    }

}
