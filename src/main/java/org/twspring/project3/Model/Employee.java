package org.twspring.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

//VARIABLES
    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String position;


    @Column(columnDefinition = "DOUBLE NOT NULL")
    private double salary;


//RELATIONSHIPS
    @OneToOne
    @MapsId
    private User user;
}
