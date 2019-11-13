package com.learn.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.learn.utilities.MonitoringMail;
import com.learn.utilities.TestConfig;

public class TestHostAdd {
	
	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {

		
		MonitoringMail mail = new MonitoringMail();
		String msgBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/DataDrivenFrameworkProject/Extent_20Report/";
		System.out.println(msgBody);
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, msgBody);
	}
}
