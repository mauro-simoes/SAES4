package com.apisae.api.models.user;


import com.apisae.api.enums.RoleUser;
import com.apisae.api.models.reponse.Reponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "utilisateur")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nom",nullable = false)
    private String nom;

    @Column(name = "prenom",nullable = false)
    private String prenom;

    @Column(name = "ville",nullable = false)
    private String ville;

    @Column(name = "date_de_naissance",nullable = false)
    private Date dateNaissance;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reponse> choix;

    @Column(name = "mot_de_passe",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    private Boolean enabled;

    private Boolean locked;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public User(String email, String nom, String prenom, String password, String ville, Date dateNaissance){
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.ville = ville;
        this.dateNaissance = dateNaissance;
        this.roleUser = RoleUser.USER;
        this.enabled = true;
        this.locked = false;
    };

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleUser.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {return password;}

}
