package edu.wctc.mvc;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Word {
    private int id;
    private String name;
    private String description;
}
