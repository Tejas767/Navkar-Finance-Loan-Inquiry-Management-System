package com.inquiry.loan.service;

import java.util.List;

import com.inquiry.loan.dto.LoanInquiryDto;
import com.inquiry.loan.entity.LoanStatus;

public interface LoanInquiryService {
	LoanInquiryDto createInquiry(LoanInquiryDto loanInquiryDto);

	List<LoanInquiryDto> allInquiries();

	LoanInquiryDto getInquiry(Long id);

	LoanInquiryDto updateInquiry(Long id, LoanInquiryDto loanInquiryDto);
	List<LoanInquiryDto> getMyInquiries();

	void deleteInquiry(Long id);
	
    // Added for AdminDashboard functionality
    LoanInquiryDto updateInquiryStatus(Long id, LoanStatus status);
}