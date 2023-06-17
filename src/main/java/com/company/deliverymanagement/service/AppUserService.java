package com.company.deliverymanagement.service;

import com.company.deliverymanagement.dto.request.AppUserRequestDto;
import com.company.deliverymanagement.dto.response.AppUserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AppUserService {
    ResponseEntity<?> saveUser(AppUserRequestDto appUser);
    ResponseEntity<?> confirmEmail(String confirmationToken);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<AppUserResponseDto> findAll();

    AppUserResponseDto findById(Long id);

}
