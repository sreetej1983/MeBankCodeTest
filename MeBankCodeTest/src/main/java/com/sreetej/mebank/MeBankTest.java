package com.sreetej.mebank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class MeBankTest {
	static ArrayList<CustomerTransactions> customertransactions = new ArrayList<CustomerTransactions>();
 	public static void main(String[] args) throws FileNotFoundException, IOException, CsvValidationException {
 		//Reading csv file as input.And Iterating by skipping first line.Also reading inputs from command as Arguments
  		String filePath = args[0];
 		String accountId = args[1];
 		String fromDate = args[2];
 		String toDate = args[3];
		 try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()) { 
			 String[] lineInArray=null;
 			 while ((lineInArray = reader.readNext()) != null) {
				 for (String cell : lineInArray) {
 		                creatingTransactionObject(cell);
		         }
			 } 
		}
 	    customerTransactionDetails(customertransactions,accountId,fromDate,toDate);
 	}
  
 	private static void customerTransactionDetails(ArrayList<CustomerTransactions> customertransactions2,
		String accountId, String fromDate, String toDate) {
  		int nunberOfTransactionsIncluded=0;
 		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 		try {
 			//Converting String to proper date format
			Date fromDateCorrectFormat=formatter.parse(fromDate);
			Date toDateCorrectFormat=formatter.parse(toDate);
			if(toDateCorrectFormat.compareTo(fromDateCorrectFormat)>0) {
				 
			}
 			double amount=0.0;
 			//Var to store list of TxIds under the given time frame.
 			List<String> txIds = new ArrayList<String>();
 			
			//Logic to calculate transactions in a specific time period .
			//Iterating Array list to modify Amount or calculate relative Amount
			for(CustomerTransactions ct : customertransactions2) {
				CustomerTransactions ct1=ct;
				Date createdDate=formatter.parse(ct1.getCreatedAt());
 				if((ct1.getFromAccountId().replaceAll("\\s","")).equalsIgnoreCase(accountId) 
 						&& (createdDate.compareTo(fromDateCorrectFormat)>0 
 						&& createdDate.compareTo(toDateCorrectFormat)<0) 
 						&& ct1.getTransactionType().replaceAll("\\s","").equalsIgnoreCase("PAYMENT")) 
 				{
 					amount-=Double.parseDouble(ct1.getAmount());
 					nunberOfTransactionsIncluded++;
 					txIds.add(ct1.getTransactionId());
				}
 				else if((ct1.getToAccountId().replaceAll("\\s","")).equalsIgnoreCase(accountId) 
 						&& (createdDate.compareTo(fromDateCorrectFormat)>0 
 						&& createdDate.compareTo(toDateCorrectFormat)<0)
 						&& ct1.getTransactionType().replaceAll("\\s","").equalsIgnoreCase("PAYMENT")) 
 				{
 					amount+=Double.parseDouble(ct1.getAmount());
 					nunberOfTransactionsIncluded++;
 					txIds.add(ct1.getTransactionId());
				}
			}
			
			for(CustomerTransactions ct : customertransactions2) {
				CustomerTransactions ct1=ct;
				
 				if((ct1.getFromAccountId().replaceAll("\\s","")).equalsIgnoreCase(accountId) && ct1.getTransactionType().replaceAll("\\s","").equalsIgnoreCase("REVERSAL") && ct1.getRelatedTransaction()!=null && txIds.contains(ct1.getRelatedTransaction().replaceAll("\\s", ""))) {
						amount=amount+Double.parseDouble(ct1.getAmount());
						nunberOfTransactionsIncluded--;
				}
 				else if((ct1.getToAccountId().replaceAll("\\s","")).equalsIgnoreCase(accountId) && ct1.getTransactionType().replaceAll("\\s","").equalsIgnoreCase("REVERSAL") && ct1.getRelatedTransaction()!=null && txIds.contains(ct1.getRelatedTransaction().replaceAll("\\s", ""))) {
					amount=amount-Double.parseDouble(ct1.getAmount());
					nunberOfTransactionsIncluded--;
				}
			}
			//Printing the final Result
			System.out.println("Relative balance for the period is: $"+amount);
			System.out.println("Number of transactions included is:"+nunberOfTransactionsIncluded);

		} catch (ParseException e) {
 			e.printStackTrace();
		}
		
	}
 
	//Creating transaction object and adding it to Array list.All transactions will be stored as arraylist of objects 
	private static void creatingTransactionObject(String cell) {
 		String params[]=null;
		CustomerTransactions customerTransaction=new CustomerTransactions();
		params=cell.split(",");
		if(params.length<7 && !params[5].equalsIgnoreCase("REVERSAL")) {
			customerTransaction.setTransactionId(params[0]);
			customerTransaction.setFromAccountId(params[1]);
			customerTransaction.setToAccountId(params[2]);
			customerTransaction.setCreatedAt(params[3]);
			customerTransaction.setAmount(params[4]);
			customerTransaction.setTransactionType(params[5]);
			customerTransaction.setRelatedTransaction(null);
			customertransactions.add(customerTransaction);
		}
		else {
			
			customerTransaction.setTransactionId(params[0]);
			customerTransaction.setFromAccountId(params[1]);
			customerTransaction.setToAccountId(params[2]);
			customerTransaction.setCreatedAt(params[3]);
			customerTransaction.setAmount(params[4]);
			customerTransaction.setTransactionType(params[5]);
			customerTransaction.setRelatedTransaction(params[6]);
			customertransactions.add(customerTransaction);
 			
		}
 	
	}

}
