package com.parts.carpartsapi.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceAction implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String actname;
    @Column (name = "ServStartDate")
    private LocalDate servStartDate;
    @Column (name = "ServEndDate")
    private LocalDate servEndDate;
    @JsonBackReference
       @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CarPart carParts;

     /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceAction)) return false;
        return id != null && id.equals(((ServiceAction) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }*/
}
