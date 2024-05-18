package com.bookstore.entity.authorization;

import com.bookstore.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_roles")
public class RolesEntity extends BaseEntity {
    private String name;
}
