package com.company.deliverymanagement.service.impl;

import com.company.deliverymanagement.entity.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
@RequiredArgsConstructor
@Service
public class DashboardService {

    @Autowired
    private EntityManager entityManager;

    public final Company getAdminDashboardStats() {

        Company stats = new Company();
        int totalCustomerCount = calculateTotalCustomerCount();
        int totalDriverCount = calculateTotalDriverCount();
        double dailyRevenue = calculateDailyRevenue();
        int dailyOrderCount = calculateDailyOrderCount();
        double monthlyRevenue = calculateMonthlyRevenue();
        int monthlyOrderCount = calculateMonthlyOrderCount();
        double yearlyRevenue = calculateYearlyRevenue();
        int yearlyOrderCount = calculateYearlyOrderCount();

        stats.setTotalCustomerCount(totalCustomerCount);
        stats.setTotalDriverCount(totalDriverCount);
        stats.setDailyRevenue(dailyRevenue);
        stats.setDailyOrderCount(dailyOrderCount);
        stats.setMonthlyRevenue(monthlyRevenue);
        stats.setMonthlyOrderCount(monthlyOrderCount);
        stats.setYearlyRevenue(yearlyRevenue);
        stats.setYearlyOrderCount(yearlyOrderCount);
        return stats;
    }

    private int calculateTotalCustomerCount() {
        Query query = entityManager.createQuery("SELECT COUNT(c) FROM AppUser c");
        return ((Long) query.getSingleResult()).intValue();
    }

    private int calculateTotalDriverCount() {
        Query query = entityManager.createQuery("SELECT COUNT(d) FROM Driver d");
        return ((Long) query.getSingleResult()).intValue();
    }

    private double calculateDailyRevenue() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        Query query = entityManager.createQuery("SELECT SUM(o.totalBudget) FROM Company o WHERE o.createdAt BETWEEN :startOfDay AND :endOfDay");
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);

        Double result = (Double) query.getSingleResult();
        return result != null ? result : 0.0;
    }

    private int calculateDailyOrderCount() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        Query query = entityManager.createQuery("SELECT COUNT(o) FROM Company o WHERE o.createdAt BETWEEN :startOfDay AND :endOfDay");
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);

        return ((Long) query.getSingleResult()).intValue();
    }

    private double calculateMonthlyRevenue() {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
        LocalDateTime endOfMonth = lastDayOfMonth.atTime(23, 59, 59);

        Query query = entityManager.createQuery("SELECT SUM(o.totalBudget) FROM Company o WHERE o.createdAt BETWEEN :startOfMonth AND :endOfMonth");
        query.setParameter("startOfMonth", startOfMonth);
        query.setParameter("endOfMonth", endOfMonth);

        Double result = (Double) query.getSingleResult();
        return result != null ? result : 0.0;
    }

    private int calculateMonthlyOrderCount() {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
        LocalDateTime endOfMonth = lastDayOfMonth.atTime(23, 59, 59);

        Query query = entityManager.createQuery("SELECT COUNT(o) FROM Company o WHERE o.createdAt BETWEEN :startOfMonth AND :endOfMonth");
        query.setParameter("startOfMonth", startOfMonth);
        query.setParameter("endOfMonth", endOfMonth);

        return ((Long) query.getSingleResult()).intValue();
    }

    private double calculateYearlyRevenue() {
        LocalDate firstDayOfYear = LocalDate.now().withDayOfYear(1);
        LocalDate lastDayOfYear = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
        LocalDateTime startOfYear = firstDayOfYear.atStartOfDay();
        LocalDateTime endOfYear = lastDayOfYear.atTime(23, 59, 59);

        Query query = entityManager.createQuery("SELECT SUM(o.totalBudget) FROM Company o WHERE o.createdAt BETWEEN :startOfYear AND :endOfYear");
        query.setParameter("startOfYear", startOfYear);
        query.setParameter("endOfYear", endOfYear);

        Double result = (Double) query.getSingleResult();
        return result != null ? result : 0.0;
    }

    private int calculateYearlyOrderCount() {
        LocalDate firstDayOfYear = LocalDate.now().withDayOfYear(1);
        LocalDate lastDayOfYear = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
        LocalDateTime startOfYear = firstDayOfYear.atStartOfDay();
        LocalDateTime endOfYear = lastDayOfYear.atTime(23, 59, 59);

        Query query = entityManager.createQuery("SELECT COUNT(o) FROM Company o WHERE o.createdAt BETWEEN :startOfYear AND :endOfYear");
        query.setParameter("startOfYear", startOfYear);
        query.setParameter("endOfYear", endOfYear);

        return ((Long) query.getSingleResult()).intValue();
    }
}
