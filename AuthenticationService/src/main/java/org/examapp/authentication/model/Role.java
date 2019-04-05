package org.examapp.authentication.model;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ROLE_NAME name;

    public Role() {
    }

    public Role(ROLE_NAME name) {
        this.name = name;
    }

    //region // default getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ROLE_NAME getName() {
        return name;
    }

    public void setName(ROLE_NAME name) {
        this.name = name;
    }

    //endregion


    @Override
    public String toString() {
        return name.name();
    }
}
