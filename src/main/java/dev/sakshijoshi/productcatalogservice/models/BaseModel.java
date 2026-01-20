package dev.sakshijoshi.productcatalogservice.models;

import java.util.Date;

public abstract class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
