package com.notification;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import src.notification.message.bean.BasicMessage;

import com.notification.exceptions.ParsingException;

public class NotificationMessagesListener {

	public void process() throws ParsingException{

		//JSON File to process
		String notificationFile = System.getProperty("notificationFile");
		System.out.println("START of Notification Message Listener.");
		
		if(isInvalidFilePath(notificationFile)){
		    System.out.println("ERROR: Given file path(s) does not exist or in-accessible. "
		    		+ "Please correct and re-run.");
	        System.exit(1);
		}

		// Load notification messages in memory.
		NotificationProcessor  notificationProcessor = NotificationProcessor.getInstance();
        List<BasicMessage> messages = notificationProcessor.parse(notificationFile);
        if(messages == null) {
        	throw new ParsingException("Parsing exception in file:"+notificationFile);
        }
        
        //Process Notification messages
        notificationProcessor.process(messages);
        System.out.println("END of Notification Message Listener.\n");
	}
	
	/**
	 * 
	 * @param filePath
	 * @return true : if path is invalid
	 *         false : if path is valid.
	 */
    private static boolean isInvalidFilePath(String filePath) {
        try {
            Path path = Paths.get(filePath);

            if(!Files.exists(path) || Files.notExists(path)) { // does not exist
                return true;
            }

            if(!Files.isRegularFile(path)) { //no a regular file
                return true;
            }

            if(!Files.isReadable(path)) { //not accessible.
                return true;
            }
        } catch (InvalidPathException | NullPointerException exception) {
            return true; 
        }

        return false;
    }
}
