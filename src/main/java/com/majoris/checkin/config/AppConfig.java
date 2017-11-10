package com.majoris.checkin.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 
 * @author Arivu
 *
 */
@org.springframework.context.annotation.Configuration
public class AppConfig {

	private static Connection connection = null;

	public static Connection getConnection() throws Exception {
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> Excel Connection - Starts >>>>>>>>>>>>>>>>>>>>>>>>> ");
			Class.forName("org.relique.jdbc.csv.CsvDriver");			
			if (connection == null) {
					// connection =
				// fillo.getConnection("/home/arivumani_ramalingam/tsgwservice/src/main/resources/x.xls");
				// Connection connection =
				// fillo.getConnection("C:\\Arivu\\git\\tsgwservice4\\src\\main\\resources\\x.xls");
				connection = DriverManager.getConnection("jdbc:relique:csv:C:\\Users\\SuryaArya\\git\\tsgwservice\\src\\main\\resources\\");
//				connection = fillo.getConnection("C:\\Users\\SuryaArya\\git\\tsgwservice\\src\\main\\resources\\ActiveMembers1.xls");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> Excel Connection - Ends >>>>>>>>>>>>>>>>>>>>>>>>> "
						+ connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.starttls.required", "true");
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(Integer.parseInt("587"));
		// mailSender.setProtocol(env.getProperty("spring.mail.protocol"));
		mailSender.setUsername("washingtontamilsangam1@gmail.com");
		mailSender.setPassword("kuzhu_1$");
		return mailSender;
	}

	@Bean
	public Configuration freemarkerConfiguration() throws IOException, TemplateException {
		FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
		factoryBean.setPreferFileSystemAccess(false);
		factoryBean.setTemplateLoaderPath("classpath:/");
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}
