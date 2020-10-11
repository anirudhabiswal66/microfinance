package com.lms.emi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.loan.model.Loan;

import lombok.Data;

@Entity(name = "emi_tbl")
public class Emi implements Serializable {

	private static final long serialVersionUID = 2731177334538310478L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emi_id")
	protected Integer emiId;

	@Column(name = "emi_amount")
	protected Double emiAmount;

	@Column(name = "emi_number")
	protected Integer emiNumber;

	@Column(name = "principle_emi")
	protected Double principleEmi;

	@Column(name = "interest_emi")
	protected Double interestEmi;

	@Column(name = "total_emi")
	protected Double totalEmi;

	@Column(name = "emi_date")
	protected LocalDate emiDate;

	@Column(name = "principle_remaining")
	protected Double principleRemaining;

	@ManyToOne()
	@JoinColumn(name = "loan_id")
	protected Loan loan;

	public Integer getEmiId() {
		return emiId;
	}

	public void setEmiId(Integer emiId) {
		this.emiId = emiId;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Integer getEmiNumber() {
		return emiNumber;
	}

	public void setEmiNumber(Integer emiNumber) {
		this.emiNumber = emiNumber;
	}

	public Double getPrincipleEmi() {
		return principleEmi;
	}

	public void setPrincipleEmi(Double principleEmi) {
		this.principleEmi = principleEmi;
	}

	public Double getInterestEmi() {
		return interestEmi;
	}

	public void setInterestEmi(Double interestEmi) {
		this.interestEmi = interestEmi;
	}

	public Double getTotalEmi() {
		return totalEmi;
	}

	public void setTotalEmi(Double totalEmi) {
		this.totalEmi = totalEmi;
	}

	public LocalDate getEmiDate() {
		return emiDate;
	}

	public void setEmiDate(LocalDate emiDate) {
		this.emiDate = emiDate;
	}

	public Double getPrincipleRemaining() {
		return principleRemaining;
	}

	public void setPrincipleRemaining(Double principleRemaining) {
		this.principleRemaining = principleRemaining;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	@Override
	public String toString() {
		return "Emi [emiId=" + emiId + ", emiAmount=" + emiAmount + ", emiNumber=" + emiNumber + ", principleEmi="
				+ principleEmi + ", interestEmi=" + interestEmi + ", totalEmi=" + totalEmi + ", emiDate=" + emiDate
				+ ", principleRemaining=" + principleRemaining + ", loan=" + loan + "]";
	}

}