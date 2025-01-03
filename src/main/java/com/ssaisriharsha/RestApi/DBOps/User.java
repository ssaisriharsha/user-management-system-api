package com.ssaisriharsha.RestApi.DBOps;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Entity
@Table(name="users")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    @NonNull
    private String firstName;

    @Column(name="last_name")
    @NonNull
    private String lastName;

    @NonNull
    @Column(name="email", unique = true)
    private String email;

    @NonNull
    @Column(name="active")
    private Boolean active;

}
