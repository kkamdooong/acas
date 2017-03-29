package com.kkamdooong.acas.repository;

import com.kkamdooong.acas.model.AutoCompleteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcasRepository extends JpaRepository<AutoCompleteItem, Integer> {
    List<AutoCompleteItem> findTop30ByKeywordOrderByWeightAsc(String keyword);
}
