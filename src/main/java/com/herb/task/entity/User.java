package com.herb.task.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    private boolean status = true;
}
