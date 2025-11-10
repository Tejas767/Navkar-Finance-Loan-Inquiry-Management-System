package com.inquiry.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inquiry.loan.entity.LoanInquiry;

public interface LoanInquiryRepository extends JpaRepository<LoanInquiry, Long> {
	List<LoanInquiry> findByCreatedByUsername(String createdByUsername);
}