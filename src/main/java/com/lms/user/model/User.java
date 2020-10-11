package com.lms.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lms.loan.model.Loan;

import lombok.Data;

@Entity
@Table(name = "user_tbl")
public class User implements Serializable {

	protected static final long serialVersionUID = 753347697649004940L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	protected Integer userId;

	@Column(name = "name")
	protected String name;

	@Column(name = "email")
	protected String email;

	@Column(name = "phone_no")
	protected long phoneNo;

	@Column(name = "overal_limit")
	protected double overalLimit;

	@Column(name = "utilized_limit")
	protected double utilizedLimit;

	@Column(name = "available_limit")
	protected double availableLimit;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Set<Loan> loans = new HashSet<>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public double getOveralLimit() {
		return overalLimit;
	}

	public void setOveralLimit(double overalLimit) {
		this.overalLimit = overalLimit;
	}

	public double getUtilizedLimit() {
		return utilizedLimit;
	}

	public void setUtilizedLimit(double utilizedLimit) {
		this.utilizedLimit = utilizedLimit;
	}

	public double getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(double availableLimit) {
		this.availableLimit = availableLimit;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", overalLimit=" + overalLimit + ", utilizedLimit=" + utilizedLimit + ", availableLimit="
				+ availableLimit + ", loans=" + loans + "]";
	}

}