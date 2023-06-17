package com.company.deliverymanagement.controller;

import com.company.deliverymanagement.entity.model.Company;
import com.company.deliverymanagement.service.impl.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public Company getAdminDashboardStats() {
        return dashboardService.getAdminDashboardStats();
    }
}

