package com.notification.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.notification.message.bean.AdjustmentMessage;
import src.notification.message.bean.BasicMessage;
import src.notification.message.bean.DetailedMessage;

import com.notification.util.OperationType;


public class SalesBook {
	
	Map<String,List<Transaction>> records = new HashMap<String,List<Transaction>>(); 
	
	List<String> adjustmentMessages = new LinkedList<String>();
	
	/**
	 * Add notification/Adjustment message to sales book.
	 * @param message
	 * @return
	 */
    public boolean updateSalesBook(int messageNum, BasicMessage message){
	   List<Transaction> transactions = records.get(message.getProductName());
	   Double sellingPrice = Double.valueOf(message.getSellingPrice());
	   if(transactions == null){ //adding product for first time.
		   transactions = new ArrayList<Transaction>();
		   records.put(message.getProductName(), transactions);
	   }
		   
	   if(message instanceof DetailedMessage){
		   long count = ((DetailedMessage)message).getCount();
		   transactions.add(new Transaction(messageNum, false, count, sellingPrice));
	   }else if(message instanceof AdjustmentMessage){
		   OperationType operation = ((AdjustmentMessage)message).getOperationType();
		   adjustSalesTransactions(operation,sellingPrice,message);
	   }else{
		   transactions.add(new Transaction(messageNum, false, 1, sellingPrice));
	   }
	   
	   return true;
   }
   
   
    /**
     * Adjust selling price with given operation to all sales of given product type.
     * @param op
     * @param sellingPrice
     * @param message
     */
   public void adjustSalesTransactions(OperationType op,Double sellingPrice,BasicMessage message){
	   List<Transaction> transactions = records.get(message.getProductName());
	   
	   if(transactions == null || transactions.isEmpty()){
		   System.out.println("Warning:No product sold for given adjustment for product: "+message.getProductName());
	   }
	   
	   switch(op){
	   case ADD:
		   transactions.stream().filter(t-> t.isAdjustmentNotification()==false).
		   				forEach(t->t.setSellingPrice(t.getSellingPrice() + sellingPrice));
		
		   break;	   
		   
   	   case SUBTRACT:
		   transactions.stream().filter(t-> t.isAdjustmentNotification()==false).
				forEach(t->t.setSellingPrice(t.getSellingPrice() - sellingPrice));

		   break;
	   
	   case MULTIPLY:
		   transactions.stream().filter(t-> t.isAdjustmentNotification()==false).
				forEach(t->t.setSellingPrice(t.getSellingPrice() * sellingPrice));

		   break;
	   
       default:
           System.out.println("Unsupported operation "+op+" during processing.");
           break;

	   }
	   //add Adjustment notification
	   transactions.add(new Transaction(1, true, 0, sellingPrice));
	   adjustmentMessages.add("Adjusted product:"+message.getProductName()+" with operation:"+op+
			                  " by value:"+sellingPrice);
   } 
   
   
   public void printInterimLog(int n){
	   System.out.println("Sales number for each product type after "+n+" notifications");
	   System.out.println("================================");
	   System.out.format("%20s%20s%n","PRODUCT","TOTAL_SALES");
	   System.out.println("================================");
	   for (String product : records.keySet()) {
		   List<Transaction> transactions = records.get(product);
		   double totalSellingPrice = transactions.stream().
				   					  filter(t-> t.isAdjustmentNotification()==false).
				   					  mapToDouble(s-> s.getSellingPrice()* s.getCount()).sum();
		   System.out.format("%20s%20f%n", product, totalSellingPrice);
	   }
	   System.out.println("=======================================");
    }
   
   public void printAdjustedLog(int n){
	   System.out.println("\n\nAdjustment LOGS after processing all "+n+" notifications");
	   System.out.println("=======================================");
	   if(adjustmentMessages.isEmpty()){
		   System.out.println("No Adjustment records present.");
	   }else{
		   adjustmentMessages.stream().forEach(System.out::println);
	   }
	   System.out.println("=======================================");
   }
   
    
}
