package com.lms.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lms.emi.model.Emi;
import com.lms.loan.model.Loan;
import com.lms.user.model.User;

public class LMSUtils {
	public static List<Emi> calculateEMI(Double principle, Integer tenure, Double interest) {
		DecimalFormat df = new DecimalFormat("#.##");
		List<Emi> setEmis = new ArrayList<>();
		LocalDate loanCerationDate = LocalDate.now();

		Double interestRate = interest;
		float simpleIntesest = calculateSimpleIntesest(principle, interestRate, tenure);

		double principalEMI = Double.parseDouble(df.format(principle / tenure));
		double intrestEMI = Math.round(simpleIntesest / tenure);
		double totalEmi = Double.parseDouble(df.format(principalEMI + intrestEMI));
		double principleRemaining = principle + simpleIntesest;

		for (int i = 1; i <= tenure; i++) {
			Emi emi = null;
			emi = new Emi();

			loanCerationDate = LocalDate.now().plusMonths(i - 1);
			principleRemaining = (Double.parseDouble(df.format(principleRemaining - totalEmi)));

			emi.setEmiNumber(i);
			emi.setEmiAmount(principle);
			emi.setPrincipleEmi(principalEMI);
			emi.setInterestEmi(intrestEMI);
			emi.setTotalEmi(totalEmi);
			emi.setEmiDate(loanCerationDate);
			emi.setPrincipleRemaining(principleRemaining);

			setEmis.add(emi);
		}
		return setEmis;
	}

	public static void main(String[] args) {
		System.out.println(calculateSimpleIntesest(1000.0, 12.0, 13));
	}

	public static float calculateSimpleIntesest(Double principle, Double interestRate, Integer tenure) {
		return (float) ((principle * interestRate * tenure.floatValue() / 12) / 100);
	}

	public static void showEmiDetails(List<Emi> calculateEMI, Integer tenure, Double principle, Double interestRate) {
		float simpleIntesest = calculateSimpleIntesest(principle, interestRate, tenure);
		System.out.println(
				"======================================================================================================================");
		System.out.println("1. Loan creation date : " + calculateEMI.get(0).getEmiDate());
		System.out.println("2. Principal Amount : " + calculateEMI.get(0).getPrincipleEmi());
		System.out.println("3. No Of EMI’s : " + tenure);

		System.out.println("4. Total payable amount : " + principle + " (Principal) + " + simpleIntesest
				+ " (Interest for " + tenure + " months) = " + (principle + simpleIntesest));
		System.out.println("5. EMI Details :\n");

		calculateEMI.forEach(emi -> {
			System.out.printf((char) (emi.getEmiNumber() + 96) + ". EMI No : " + emi.getEmiNumber()
					+ ", Principal EMI : " + emi.getPrincipleEmi() + ", Interest EMI = "
					+ Math.round(emi.getInterestEmi()) + ", Total EMI : " + emi.getTotalEmi() + ", EMI Date : "
					+ emi.getEmiDate() + ", Principal remaining : %.2f", emi.getPrincipleRemaining());
			System.out.println();
		});
		System.out.println(
				"======================================================================================================================");
	}

	public static List<User> parseExcelFile(InputStream is) {
		List<User> users = new ArrayList<User>();
		XSSFWorkbook wb = null;
		Sheet sheet = null;
		Iterator<Row> roows = null;
		try {
			wb = new XSSFWorkbook(is);
			sheet = wb.getSheetAt(0);
			roows = sheet.iterator();

			while (roows.hasNext()) {
				Row currentRow = roows.next();
				if (currentRow.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cellInRow = currentRow.cellIterator();
				User user = new User();
				int cellIndex = 0;

				while (cellInRow.hasNext()) {
					Cell currentCell = cellInRow.next();
					int columnIndex = currentCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						user.setName(currentCell.getStringCellValue());
						cellIndex++;
						break;
					case 1:
						user.setEmail(currentCell.getStringCellValue());
						cellIndex++;
						break;
					case 2:
						user.setPhoneNo((long) currentCell.getNumericCellValue());
						cellIndex++;
						break;
					case 3:
						user.setOveralLimit((long) currentCell.getNumericCellValue());
						cellIndex++;
						break;
					case 4:
						user.setUtilizedLimit((double) currentCell.getNumericCellValue());
						cellIndex++;
						break;
					case 5:
						user.setAvailableLimit((double) currentCell.getNumericCellValue());
						cellIndex++;
						break;

					default:
						break;
					}
				}
				users.add(user);
			}
			wb.close();
			return users;
		} catch (IOException e) {
			throw new RuntimeException("error: " + e.getMessage());
		}
	}

	public static Loan calculateEmi(Loan loan) {
		DecimalFormat df = new DecimalFormat("#.##");
		double principal = loan.getPrincipal();
		double tenure = loan.getTenure();

		double rateOfIntrest = (loan.getIntrestOfRate() > 0.0) ? loan.getIntrestOfRate() : 12;

		double principalEMI = Double.parseDouble(df.format(principal / tenure));
		double intrest = (principal * (tenure / 12) * rateOfIntrest) / 100;
		double intrestEMI = intrest / tenure;
		double emi = Double.parseDouble(df.format(principalEMI + intrestEMI));
		double totalAmount = principal + intrest;
		double remainingAmount = totalAmount;

		System.out.println("principalEMI : " + principalEMI + "\n" + "intrest : " + intrest);
		System.out.println("EMI Per Month : " + emi);

		for (int i = 0; i < tenure; i++) {
			remainingAmount = remainingAmount - emi;
			System.out.printf("EMI No : " + (i + 1) + ", Principal EMI : " + principalEMI + ", Interest EMI = "
					+ Math.round(intrestEMI) + ", Total EMI : " + emi + ", EMI Date : "
					+ ", Principal remaining : %.2f", remainingAmount);
			System.out.println();
		}
		loan.setIntrestOfRate(rateOfIntrest);
		return loan;
	}
}
