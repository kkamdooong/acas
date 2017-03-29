package com.kkamdooong.acas.controller;

import com.kkamdooong.acas.model.AutoCompleteItem;
import com.kkamdooong.acas.service.AcasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AcasController {
    private final AcasService service;

    @Autowired
    public AcasController(AcasService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public List<AutoCompleteItem> getItems(@RequestParam String keyword) {

        return service.getItems(keyword.toLowerCase());
    }

    @PostMapping("/items")
    public ResponseEntity addItems(@RequestBody List<AutoCompleteItem> items) {
        List<AutoCompleteItem> addedItems = service.addItems(items);

        return ResponseEntity.created(URI.create("/api/v1/items")).body(addedItems);
    }
}
