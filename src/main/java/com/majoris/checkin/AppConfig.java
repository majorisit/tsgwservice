package com.majoris.checkin;

import java.io.IOException;
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
		System.out.println();
		return factoryBean.getObject();
	}
}
