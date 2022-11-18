package com.herb.task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String city;
    private String district;
    private String street;
    private String apart_no;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean status = true;
}
