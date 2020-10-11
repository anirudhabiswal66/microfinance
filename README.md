# Loan Management System for a microfinance company
---------------------------------------------------

#### 1. User Module
 - Upload the users via excel which will have the fields in the user module.
  
#### 2. Loan Module
 - On creating a loan, the details of the loan will be calculated dynamically like principle amount and the EMI’s
 
#### 3. EMI Calculation Module
- The EMI’s have to be calculated based on the loan details.
  


| End Points | URL |
| ------ | ------ |
| Upload User | [localhost:8080/excel/upload][PlDb] |
| Create Loan | [localhost:8080/loan/create-loan/{userId}?loanTitle=LoanTitle&interestRate=12&principle=1000&tenure=12][PlGh] |
| Check EMI | [localhost:8080/emi/calculate?loanTitle=LoanTitle&interestRate=12&principle=1000&tenure=10][PlGd] |
