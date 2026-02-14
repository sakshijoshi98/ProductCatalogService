package dev.sakshijoshi.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass  //this is parent class don't make any table for this
public abstract class BaseModel {

    @Id // primary key for all tables
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
