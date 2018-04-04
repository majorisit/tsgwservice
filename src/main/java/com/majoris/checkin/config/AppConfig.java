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

	public static Connection getConnection() throws Exception {
		try {
			// db parameters
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:C://sqllite//test.db";
			// create a connection to the database
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		mailSender.setUsername("washingtontamilsangam4@gmail.com");
		mailSender.setPassword("kuzhu_4$");
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
