package com.olmez.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class BaseObject implements Serializable, Comparable<BaseObject> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected LocalDateTime createdTime = LocalDateTime.now();
    protected LocalDateTime lastModifiedTime;
    protected boolean deleted = false;

    @ManyToOne
    protected User lastModifiedBy;

    @PreUpdate
    public void onPreUpdate() {
        lastModifiedTime = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public int compareTo(BaseObject obj) {
        return hasId(obj) ? this.id.compareTo(obj.getId()) : 0;
    }

    private boolean hasId(BaseObject obj) {
        return (obj != null) && (id != null) && (obj.getId() != null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BaseObject)) {
            return false;
        }
        BaseObject bo = (BaseObject) obj;
        return hasId(bo) && (this.id.equals(bo.getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 79;
        return prime * result + ((id == null) ? 0 : id.hashCode());
    }

}
