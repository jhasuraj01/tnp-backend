package com.anorcle.tnp.backend.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({ "passwordHash", "password", "username", "authorities", "accountNonExpired", "accountNonLocked",
    "credentialsNonExpired", "enabled" })
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull(message = "Missing Required Properties: user.id")
  private Integer id;

  @Email(message = "Invalid Value: user.email")
  @Column(unique = true)
  private String email;

  @NotEmpty(message = "Missing Required Properties: user.userGroup")
  private String userGroup;

  @NotEmpty(message = "Missing Required Properties: user.passwordHash")
  private String passwordHash;

  @NotBlank(message = "Missing Required Properties: user.firstName")
  private String firstName;
  private String lastName;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.userGroup));
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.passwordHash;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
