package com.inquiry.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.inquiry.loan.entity.LoanStatus; // add this import

@Schema(description = "Loan Inquiry DTO - holds customer inquiry details.")
public class LoanInquiryDto {

    private Long id;

    @NotEmpty
    @Schema(description = "Applicant name", example = "Varad Mule")
    private String name;

    @NotEmpty
    @Size(min = 10)
    @Schema(description = "Mobile number", example = "9876543210")
    private String mobileNumber;

    @NotEmpty
    @Email(message = "Invalid email address")
    @Schema(description = "Email address", example = "varad@example.com")
    private String email;

    @NotEmpty
    @Schema(description = "Address", example = "123 XYZ Road, Pune")
    private String address;

    @NotEmpty
    @Schema(description = "Work type", example = "Job")
    private String workType;

    @NotEmpty
    @Schema(description = "Loan Type requested", example = "Home Loan")
    private String loanType;

    @NotNull
    @Schema(description = "Annual income", example = "500000")
    private Double annualIncome;

    @NotNull
    @Schema(description = "Past loan history", example = "No")
    private Boolean pastLoan;

    @NotEmpty
    @Schema(description = "PAN card number", example = "ABCDE1234F")
    private String panCard;

    // ======= NEW: expose status to frontend =======
    @Schema(description = "Current status of the inquiry", example = "PENDING")
    private LoanStatus status;

    // ======= OPTIONAL: expose who created it (useful for debugging & filtering) =======
    private String createdByUsername;

    // Constructors
    public LoanInquiryDto() {}

    public LoanInquiryDto(Long id, String name, String mobileNumber, String email, String address, String workType,
            String loanType, Double annualIncome, Boolean pastLoan, String panCard) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.workType = workType;
        this.loanType = loanType;
        this.annualIncome = annualIncome;
        this.pastLoan = pastLoan;
        this.panCard = panCard;
    }

    // Getters / Setters (include new ones)
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
    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }
    public Double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Double annualIncome) { this.annualIncome = annualIncome; }
    public Boolean getPastLoan() { return pastLoan; }
    public void setPastLoan(Boolean pastLoan) { this.pastLoan = pastLoan; }
    public String getPanCard() { return panCard; }
    public void setPanCard(String panCard) { this.panCard = panCard; }

    // ===== new getter/setter for status =====
    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }

    // optional createdByUsername getter/setter
    public String getCreatedByUsername() { return createdByUsername; }
    public void setCreatedByUsername(String createdByUsername) { this.createdByUsername = createdByUsername; }
}
