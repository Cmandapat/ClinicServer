package com.clinicallyinsane.ClinicServer.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    private String id;

    @Column(name = "user_type",columnDefinition ="varchar(10) default 'PAT'")
    private String userType;

    @NotNull
    @Column
    private String password;

    @Column(name = "login_status",columnDefinition="int(11) default '0'")
    private int loginStatus = 0;

    @Transient
    private final static int ADMIN_STATUS_CODE = 2;

    @Transient
    private final static int REPORTER_STATUS_CODE = 3;





    @OneToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name = "id",unique = true)
    private UserProfile userProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String accountTypeChecker(int code) {
        if (code == ADMIN_STATUS_CODE) {
            return "ADMIN";
        } else if(code == REPORTER_STATUS_CODE) {
            return "REP";
        } else {
            return "PAT";
        }
    }

}
