package com.notification.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Test;

import com.notification.NotificationMessagesListener;
import com.notification.exceptions.ParsingException;

public class TestNotificationTestCase extends TestCase{

	public void testBasicMessage(){
		System.setProperty("interimLogCount","10");
		System.setProperty("processCapacity","50");
		System.setProperty("notificationFile","src/main/resources/notifications.json");
		NotificationMessagesListener notificationMessagesListener = new NotificationMessagesListener();
		try {
			notificationMessagesListener.process();
		} catch (ParsingException e) {
			fail();
		}
		
	}

	
	public void testMixedMessage(){
		System.setProperty("interimLogCount","10");
		System.setProperty("processCapacity","50");
		System.setProperty("notificationFile","src/main/resources/mixednotifications.json");
		NotificationMessagesListener notificationMessagesListener = new NotificationMessagesListener();
		try {
			notificationMessagesListener.process();
		} catch (ParsingException e) {
			fail();
		}
	}

}
