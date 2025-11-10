package com.inquiry.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_inquiries")
public class LoanInquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Enumerated(EnumType.STRING)
	private LoanStatus status;
	private String name;
	private String mobileNumber;
	private String email;
	private String address;
	private String workType;
	private String loanType;
	private Double annualIncome;
	private Boolean pastLoan;
	private String panCard;
	private String createdByUsername;
	
	
  
    public LoanInquiry() {
    }

  
// Getters and Setters for all fields...
    
    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getWorkType() { return workType; }
    public void setWorkType(String workType) { this.workType = workType; }
    
    // 💡 Getter/Setter for new field
    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }
    
    public Double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Double annualIncome) { this.annualIncome = annualIncome; }
    public Boolean getPastLoan() { return pastLoan; }
    public void setPastLoan(Boolean pastLoan) { this.pastLoan = pastLoan; }
    public String getPanCard() { return panCard; }
    public void setPanCard(String panCard) { this.panCard = panCard; }
    
    // 💡 Getter/Setter for ownership field
    public String getCreatedByUsername() { return createdByUsername; }
    public void setCreatedByUsername(String createdByUsername) { this.createdByUsername = createdByUsername; }
}