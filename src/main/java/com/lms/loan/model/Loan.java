package com.lms.loan.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lms.emi.model.Emi;
import com.lms.user.model.User;

import lombok.Data;

@Entity(name = "loan_tbl")
public class Loan implements Serializable {
	private static final long serialVersionUID = 3457499055103766241L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Integer loanId;

	@Column(name = "loan_title")
	private String loanTitle;

	@Column(name = "intrest_rate")
	private double intrestOfRate;

	@Column(name = "tenure")
	private int tenure;

	@Column(name = "loan_amount")
	private double loanAmount;

	@Column(name = "principal")
	private double principal;

	// @JsonIgnore
	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Emi> emis = new LinkedList<Emi>();

	// @JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public String getLoanTitle() {
		return loanTitle;
	}

	public void setLoanTitle(String loanTitle) {
		this.loanTitle = loanTitle;
	}

	public double getIntrestOfRate() {
		return intrestOfRate;
	}

	public void setIntrestOfRate(double intrestOfRate) {
		this.intrestOfRate = intrestOfRate;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public List<Emi> getEmis() {
		return emis;
	}

	public void setEmis(List<Emi> emis) {
		this.emis = emis;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanTitle=" + loanTitle + ", intrestOfRate=" + intrestOfRate + ", tenure="
				+ tenure + ", loanAmount=" + loanAmount + ", principal=" + principal + ", emis=" + emis + ", user="
				+ user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emis == null) ? 0 : emis.hashCode());
		long temp;
		temp = Double.doubleToLongBits(intrestOfRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(loanAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((loanId == null) ? 0 : loanId.hashCode());
		result = prime * result + ((loanTitle == null) ? 0 : loanTitle.hashCode());
		temp = Double.doubleToLongBits(principal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tenure;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (emis == null) {
			if (other.emis != null)
				return false;
		} else if (!emis.equals(other.emis))
			return false;
		if (Double.doubleToLongBits(intrestOfRate) != Double.doubleToLongBits(other.intrestOfRate))
			return false;
		if (Double.doubleToLongBits(loanAmount) != Double.doubleToLongBits(other.loanAmount))
			return false;
		if (loanId == null) {
			if (other.loanId != null)
				return false;
		} else if (!loanId.equals(other.loanId))
			return false;
		if (loanTitle == null) {
			if (other.loanTitle != null)
				return false;
		} else if (!loanTitle.equals(other.loanTitle))
			return false;
		if (Double.doubleToLongBits(principal) != Double.doubleToLongBits(other.principal))
			return false;
		if (tenure != other.tenure)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
