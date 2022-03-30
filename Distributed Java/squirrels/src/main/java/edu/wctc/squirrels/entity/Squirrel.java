package edu.wctc.squirrels.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name="Squirrel")
public class Squirrel {
    @Id
    @Column(name="squirrel_id")
    private int id;
    @Column(name="species")
    private String species;
    @Column(name="common_name")
    private String commonName;
    @Column(name="habitat")
    private String habitat;
    @Column(name="image_file_name")
    private String imageFileName;
    @Column(name="ranking")
    private int ranking;
}
