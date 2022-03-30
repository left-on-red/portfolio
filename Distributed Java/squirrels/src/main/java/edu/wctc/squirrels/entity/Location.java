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
@Table(name="Location")
public class Location {
    @Id
    @Column(name="location_id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="country")
    private String country;
}
