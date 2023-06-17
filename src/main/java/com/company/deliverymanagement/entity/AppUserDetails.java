package com.company.deliverymanagement.entity;
import com.company.deliverymanagement.entity.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;

public class AppUserDetails extends AppUser implements UserDetails {
    public AppUserDetails(AppUser appUser){
        super.setName(appUser.getName());
        super.setSurname(appUser.getSurname());
        super.setUsername(appUser.getUsername());
        super.setPassword(appUser.getPassword());
        super.setBirthday(appUser.getBirthday());
        super.setEmail(appUser.getEmail());
        super.setPhoneNumber(appUser.getPhoneNumber());
        super.setIsEnabled(appUser.getIsEnabled());
        super.setRoles(appUser.getRoles());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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
        return this.getIsEnabled();
    }
}


