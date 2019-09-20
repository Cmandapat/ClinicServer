package com.clinicallyinsane.ClinicServer.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "user_type",columnDefinition ="varchar(10) default 'PAT'")
    private String userType;

    @Column
    private String password;

    @Column(name = "login_status",columnDefinition="int(11) default '0'")
    private int loginStatus = 0;

    @OneToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name = "login_id",unique = true)
    private UserProfile userProfile;

}
