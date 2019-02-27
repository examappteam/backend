package backend.shared.models;

import backend.shared.enums.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USER")
public class User {
    private String name;
    @Id
    private String email;
    private Role role;

    public User(){

    }

    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
