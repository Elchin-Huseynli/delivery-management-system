package com.company.deliverymanagement.controller;

import com.company.deliverymanagement.dto.request.AppUserRequestDto;
import com.company.deliverymanagement.dto.response.AppUserResponseDto;
import com.company.deliverymanagement.service.impl.AppUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final AppUserDetailServiceImpl appUserDetailService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody AppUserRequestDto user) {
        log.info("Registering user: {}", user.getUsername());
        return appUserDetailService.saveUser(user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        log.info("Confirming user account with token: {}", confirmationToken);
        return appUserDetailService.confirmEmail(confirmationToken);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("@appUserDetailServiceImpl.hasCustomerId(@authentication,#customerId)")
    public Optional<AppUserResponseDto> customer(@PathVariable Long customerId) {
        log.info("Retrieving customer with ID: {}", customerId);
        return Optional.ofNullable(appUserDetailService.findById(customerId));
    }

    @PostMapping("/forget-password")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) {
        log.info("Initiating password reset for email: {}", email);
        appUserDetailService.initiatePasswordReset(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reset-password")
    public ResponseEntity<Void> verifyToken(@RequestParam String token) {
        log.info("Verifying password reset token: {}", token);
        if (appUserDetailService.verifyPasswordResetToken(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        log.info("Resetting password with token: {}", token);
        appUserDetailService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }
}
