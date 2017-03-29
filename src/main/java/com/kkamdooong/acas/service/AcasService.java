package com.kkamdooong.acas.service;

import com.kkamdooong.acas.model.AutoCompleteItem;
import com.kkamdooong.acas.repository.AcasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcasService {
    private final AcasRepository repository;

    @Autowired
    public AcasService(AcasRepository repository) {
        this.repository = repository;
    }

    public List<AutoCompleteItem> getItems(String keyword) {
        return repository.findTop30ByKeywordOrderByWeightAsc(keyword);
    }

    public List<AutoCompleteItem> addItems(List<AutoCompleteItem> items) {
        List<AutoCompleteItem> normalizedItems = new ArrayList<>();

        for (AutoCompleteItem item : items) {
            String normalizedTitle = Normalizer.normalize(item.getTitle().toLowerCase(), Normalizer.Form.NFD);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < normalizedTitle.length(); i ++) {
                String keyword = stringBuilder.append(normalizedTitle.charAt(i)).toString();

                AutoCompleteItem normalizedItem = item.copy();
                normalizedItem.setId(keyword + "_" + normalizedTitle);
                normalizedItem.setKeyword(keyword);

                normalizedItems.add(normalizedItem);
            }
        }

        return repository.save(normalizedItems);
    }
}
