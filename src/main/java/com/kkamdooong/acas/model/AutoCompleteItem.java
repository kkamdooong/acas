package com.kkamdooong.acas.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(indexes = {
        @Index(name = "keyword_index", columnList = "keyword"),
        @Index(name = "weight_index", columnList = "weight")
})
public class AutoCompleteItem {
    @Id
    private String id;
    private String keyword;
    private String title;
    private String data;
    private String weight;

    public AutoCompleteItem copy() {
        AutoCompleteItem copied = new AutoCompleteItem();
        copied.setTitle(this.title);
        copied.setData(this.data);
        copied.setWeight(this.weight);

        return copied;
    }
}
