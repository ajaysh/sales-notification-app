package com.notification;

import java.io.File;
import java.io.IOException;
import java.util.List;

import src.notification.message.bean.BasicMessage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.model.SalesBook;


/**
 * Singleton class to process given notification json file and print summary of sales.
 * @author 
 *
 */
public class NotificationProcessor {
    private static NotificationProcessor instance=null;
    private int INTERMITENT_LOG_COUNT = Integer.parseInt(System.getProperty("interimLogCount"));
    private int PROCESS_CAPACITY = Integer.parseInt(System.getProperty("processCapacity"));
    
    private SalesBook salesBook; //hold all sales transactions

    private NotificationProcessor(){
    }
    
    public static NotificationProcessor getInstance() {
    	if(instance == null){
    		synchronized (NotificationProcessor.class) {
				if(instance == null){
					instance = new NotificationProcessor();
				}
			}
    		
    	}
        return instance;
    }
  
    /**
     * process notification file and convert all notifications into List of objects. 
     * @param notificationsFile
     * @return Messages list
     */
    public List<BasicMessage> parse(String notificationsFile) {
        List<BasicMessage> messages = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            messages = mapper.readValue(new File(notificationsFile), new TypeReference<List<BasicMessage>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return messages;
    }
    
    /**
     * process messages and load into SalesBook.
     * @param basicMessages
     * @return
     */
    public boolean process(List<BasicMessage> basicMessages) {
        int processedMessages = 0;
        salesBook = new SalesBook();
        INTERMITENT_LOG_COUNT = Integer.parseInt(System.getProperty("interimLogCount"));
        
        for(BasicMessage message : basicMessages) {
        	processedMessages++;
        	//process messages
        	salesBook.updateSalesBook(processedMessages,message);
        	if(processedMessages % INTERMITENT_LOG_COUNT == 0){  //time to print interim status of sales
        		salesBook.printInterimLog(processedMessages);
        	}
        	
        	if(processedMessages % PROCESS_CAPACITY == 0){ //max capacity, stop process.
        		int skipCount = basicMessages.size() - 50;
        		System.out.println("Processed "+"50"+" messages, ignoring remaing "+skipCount+" messages.");
        		break;
        	}
        }
        //print final sales totals and Adjustment messages.
        salesBook.printInterimLog(processedMessages);
        salesBook.printAdjustedLog(processedMessages);
        
        return true; //all done.
    }
    
 
    
}
