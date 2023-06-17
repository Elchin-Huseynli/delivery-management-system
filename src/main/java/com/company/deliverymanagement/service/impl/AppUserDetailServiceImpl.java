package com.company.deliverymanagement.service.impl;
import com.company.deliverymanagement.dto.request.AppUserRequestDto;
import com.company.deliverymanagement.dto.response.AppUserResponseDto;
import com.company.deliverymanagement.entity.AppUserDetails;
import com.company.deliverymanagement.entity.model.*;
import com.company.deliverymanagement.exception.AppUserNotFoundException;
import com.company.deliverymanagement.repository.AppUserRepository;
import com.company.deliverymanagement.repository.ConfirmationTokenRepository;
import com.company.deliverymanagement.repository.ForgetPasswordTokenRepository;
import com.company.deliverymanagement.repository.RoleRepository;
import com.company.deliverymanagement.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailServiceImpl implements UserDetailsService , AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final ModelMapper modelMapper;
    private final ForgetPasswordTokenRepository forgetPasswordTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsernameAndIsEnabled(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found! " + username));
        
        return new AppUserDetails(appUser);
    }


    public Boolean hasCustomerId(Authentication authentication,Long customer_id) {
        String username= authentication.getName();
        Optional<AppUser> byUsername=appUserRepository.findByUsername(username);
        return  byUsername.isPresent() && Objects.equals(byUsername.get().getId(),customer_id);
    }

//    public Boolean hasCustomerId(Authentication authentication) {
//
//        String username= authentication.getName();
//        Optional<AppUser> byUsername=appUserRepository.findByUsername(username);
//        byUsername.get().getRoles().get(0) == "DRIVER";
//        return  byUsername.isPresent() && Objects.equals(byUsername.get().getId(),customer_id);
//    }

    @Override
    public List<AppUserResponseDto> findAll() {
        return appUserRepository.findAll().stream()
                .map(appUser -> modelMapper.map(appUser, AppUserResponseDto.class))
                .collect(Collectors.toList());


    }

    @Override
    public AppUserResponseDto findById(Long id) {
        return modelMapper.map(
                appUserRepository.findById(id).orElseThrow(AppUserNotFoundException::new)
                ,AppUserResponseDto.class);
    }

///////////////////////////////////////////////////

    @Override
    public ResponseEntity<?> saveUser(AppUserRequestDto appUserRequestDto) {
        AppUser appUser = modelMapper.map(appUserRequestDto,AppUser.class);
        if (appUserRepository.existsByEmail(appUser.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        Role role = Role.builder()
                .name("CUSTOMER")
                .build();
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(List.of(roleRepository.save(role)));
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .confirmedAt(LocalDateTime.now())
                .user(appUser)
                .confirm(true)
                .build();
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(appUser.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:1357/confirm-account?token="+confirmationToken.getToken());

        confirmationTokenRepository.save(confirmationToken);
        emailService.sendEmail(mailMessage);


        System.out.println("Confirmation Token: " + confirmationToken.getToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }


    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByToken(confirmationToken);

        log.info("confirmation token : " + confirmationToken);

        if(token != null)
        {
            AppUser appUser = appUserRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            log.info("Find email : " + token.getUser().getEmail());
            appUser.setIsEnabled(true);
            appUserRepository.save(appUser);
            log.info("Email verified successfully!");
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    public void initiatePasswordReset(String email) {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);

            ForgetPasswordToken passwordResetToken = new ForgetPasswordToken();
            passwordResetToken.setConfirm("true");
            passwordResetToken.setCreatedAt(LocalDateTime.now());
            passwordResetToken.setToken(token);
            passwordResetToken.setUser(user);
            passwordResetToken.setExpiryDate(expiryDate);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Complete Forget Password!");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:1357/forgot-password?token="+passwordResetToken.getToken());

            emailService.sendEmail(mailMessage);

            forgetPasswordTokenRepository.save(passwordResetToken);

            // Send password reset email with the token
            // Implement email sending logic here
        }
    }

    public boolean verifyPasswordResetToken(String token) {
        Optional<ForgetPasswordToken> tokenOptional = forgetPasswordTokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            ForgetPasswordToken passwordResetToken = tokenOptional.get();
            LocalDateTime expiryDate = passwordResetToken.getExpiryDate();

            if (LocalDateTime.now().isBefore(expiryDate)) {
                return true;
            }
        }
        return false;
    }

    public void resetPassword(String token, String newPassword) {
        Optional<ForgetPasswordToken> tokenOptional = forgetPasswordTokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            ForgetPasswordToken passwordResetToken = tokenOptional.get();
            AppUser user = passwordResetToken.getUser();

            user.setPassword(passwordEncoder.encode(newPassword));
            appUserRepository.save(user);

            forgetPasswordTokenRepository.delete(passwordResetToken);
        }
    }
}