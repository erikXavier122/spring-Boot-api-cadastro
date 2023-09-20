package com.api.cadastro.domain.model.v1;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity implements Serializable {

    public static final long SerialVersionUID = 1l;

    @JsonFormat(pattern = "dd-MM-yyyy")
    protected String dateCreated;

    @JsonFormat(pattern = "dd-MM-yyyy")
    protected String dateUpdate;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = LocalDate.now().toString();
    }

    protected void onUpdate() {
        this.dateUpdate = LocalDate.now().toString();
    }

    private String getCreated() {
        return dateCreated;
    }

    public String getDateUpdate() {
        return this.dateUpdate == null ? getCreated() : this.dateUpdate;
    }


}
