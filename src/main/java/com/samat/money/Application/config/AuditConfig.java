//package com.samat.money.Application.config;
//
//import kg.med.licensing.Util.util.SpringSecurityAuditorAwareServiceUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * @author Zhumaev.Ali
// * @gmail seasonvar21@gmail.com
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaAuditing(auditorAwareRef = "aware")
//public class AuditConfig {
//    @Bean
//    public AuditorAware<String> aware() {
//        return new SpringSecurityAuditorAwareServiceUtil();
//    }
//}
