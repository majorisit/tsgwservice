package com.majoris.checkin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import freemarker.template.Configuration;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

@Controller
public class CheckinController {

	private static final String MEMBER_RECORD_NOT_FOUND = "Member Record Not Found";
	private static final String CHECK_IN = "Check-In";
	private static final String CHECK_OUT = "Member Already Checked In";
	/**
	 * Logger
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	/**
	 * Free Marker template configuration
	 */
	@Autowired
	private Configuration freemarkerConfiguration;
	/**
	 * Spring mail sender component.
	 */
	@Autowired
	private JavaMailSender mailSender;
	/**
	 * Freemarker template file
	 */
	private static final String FREEMARKER_TEMPLATE_FILE = "/barcode_email.ftl";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		System.out.println("*********************** Inside Index!!!");
		return "index";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ModelAndView search(@RequestParam("memberId") String memberId) throws Exception {
		System.out.println("*********************** Inside Search!!!" + memberId);
		CsvToBean<MemberCsv> csvToBean = new CsvToBean<MemberCsv>();
		CSVReader csvReader = new CSVReader(new FileReader("C:\\AryaSuryaHome\\tsgw\\MasterMembersList.csv"));
		List<MemberCsv> members = csvToBean.parse(getCsvColumnMappingStrategy(), csvReader);
		MemberCsv member = findMember(members, memberId);
		if (!StringUtils.isEmpty(memberId) && member != null) {
			member.setLabelCheckIn(CHECK_IN);
			CsvToBean<CheckedOutMembersCsv> csvToBeanCheckedOutMemebers = new CsvToBean<CheckedOutMembersCsv>();
			CSVReader csvReaderCheckedOutMemebers = new CSVReader(
					new FileReader("C:\\AryaSuryaHome\\tsgw\\CheckedoutMembers.csv"));
			List<CheckedOutMembersCsv> checkedOutMemebers = csvToBeanCheckedOutMemebers
					.parse(getCheckedOutColumnMappingStrategy(), csvReaderCheckedOutMemebers);
			for (CheckedOutMembersCsv coutMembers : checkedOutMemebers) {
				if (coutMembers.getMemeberId().equals(memberId)) {
					member.setLabelCheckIn(CHECK_OUT);
					break;
				}
			}
		} else {
			member = new MemberCsv();
			member.setLabelCheckIn(MEMBER_RECORD_NOT_FOUND);
		}
		return new ModelAndView("result", "member", member);
	}

	@RequestMapping(value = "/checkin", method = RequestMethod.GET)
	public @ResponseBody ModelAndView checkin(@RequestParam("memberId") String memberId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("wristbands") String wristbands, @RequestParam("addressChanged") String addressChanged,
			@RequestParam("thendralMullai") String thendralMullai) throws Exception {
		LOG.info("*********************** Inside Checkin!!!" + memberId);
		CsvToBean<CheckedOutMembersCsv> csvToBeanCheckedOutMemebers = new CsvToBean<CheckedOutMembersCsv>();
		CSVReader csvReaderCheckedOutMemebers = new CSVReader(
				new FileReader("C:\\AryaSuryaHome\\tsgw\\CheckedoutMembers.csv"));
		List<CheckedOutMembersCsv> checkedOutMemebers = csvToBeanCheckedOutMemebers
				.parse(getCheckedOutColumnMappingStrategy(), csvReaderCheckedOutMemebers);
		List<String[]> newCheckouts = new ArrayList<String[]>();
		newCheckouts
				.add(new String[] { "Id", "FirstName", "LastName", "Wristbands", "AddressChanged", "ThendralMullai" });
		for (CheckedOutMembersCsv coutMember : checkedOutMemebers) {
			if (coutMember.getMemeberId().equals(memberId)) {
				;
			} else {
				newCheckouts.add(new String[] { coutMember.getMemeberId(), coutMember.getFirstName(),
						coutMember.getLastName(), coutMember.getWristbands(), coutMember.getAddressChanged(),
						coutMember.getThendralMullai() });
			}
		}
		newCheckouts.add(new String[] { memberId, firstName, lastName, wristbands, addressChanged, thendralMullai });
		CSVWriter writer = new CSVWriter(new FileWriter("C:\\AryaSuryaHome\\tsgw\\CheckedoutMembers.csv"));
		writer.writeAll(newCheckouts);
		writer.close();
		return search(memberId);
	}

	private MemberCsv findMember(List<MemberCsv> members, String memberId) {
		for (MemberCsv member : members) {
			if (memberId.equals(member.getMemeberId())) {
				return member;
			}
		}
		return null;
	}

	@RequestMapping(path = "/generateBarCodes", method = RequestMethod.GET)
	public void generateBarCodes() throws Exception {
		CsvToBean<MemberCsv> csvToBean = new CsvToBean<MemberCsv>();
		CSVReader csvReader = new CSVReader(new FileReader("C:\\AryaSuryaHome\\tsgw\\email_members.csv"));
		List<MemberCsv> members = csvToBean.parse(getCsvColumnMappingStrategy(), csvReader);
		if (members == null) {
			LOG.info("List is empty. No members recods to process.");
			return;
		}
		// Generate bar code for the members
		for (MemberCsv member : members) {
			String imageName = "C:\\AryaSuryaHome\\tsgw\\barcodes\\" + member.getMemeberId() + ".png";
			BufferedImage image = BarcodeImageHandler.getImage(BarcodeFactory.createCode128(member.getMemeberId()));

			File outputfile = new File(imageName);
			ImageIO.write(image, "png", outputfile);
			try {

				MimeMessage message = mailSender.createMimeMessage();
				message.setFrom(new InternetAddress("washingtontamilsangam1@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(member.getEmail1()));
				if (!StringUtils.isEmpty(member.getEmail2())) {
					message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(member.getEmail2()));
				}
				if (!StringUtils.isEmpty(member.getEmail3())) {
					message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(member.getEmail3()));
				}
				message.setSubject("Access Card - Muththamizh Vizhaa 2017");

				Map<String, Object> memberData = new HashMap<String, Object>();
				memberData.put("memberId", member.getMemeberId());
				memberData.put("firstName", member.getFirstName());
				memberData.put("lastName", member.getLastName());
				memberData.put("veg", StringUtils.isEmpty(member.getVeg()) ? "0" : member.getVeg());
				memberData.put("nonVeg", StringUtils.isEmpty(member.getNonVeg()) ? "0" : member.getNonVeg());
				/**
				 * Process template.
				 */
				String report = FreeMarkerTemplateUtils.processTemplateIntoString(
						freemarkerConfiguration.getTemplate(FREEMARKER_TEMPLATE_FILE, "UTF-8"), memberData);

				MimeMultipart multipart = new MimeMultipart("related");
				// first part, main html part (the html)
				BodyPart messageBodyPart = new MimeBodyPart();
				// add it
				multipart.addBodyPart(messageBodyPart);
				/**
				 * Add Image in Header
				 */
				addImage(multipart, "tsgw_banner", "tsgw_banner.jpg");
				/**
				 * Add Go Green
				 */
				addImage(multipart, "gogreen", "gogreen1.jpg");
				/**
				 * Add Image in Header
				 */
				addImageLocal(multipart, "member_barcode", imageName);
				messageBodyPart.setContent(report, "text/html");
				message.setContent(multipart);
				getMailSender().send(message);

				LOG.info("Email Sent for MemberId => " + member.getMemeberId());

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
		LOG.info("BarCode Generated.");
	}

	/**
	 * CSV to domain object column mapping
	 * 
	 * @return
	 */
	private HeaderColumnNameTranslateMappingStrategy<MemberCsv> getCsvColumnMappingStrategy() {
		HeaderColumnNameTranslateMappingStrategy<MemberCsv> strategy = new HeaderColumnNameTranslateMappingStrategy<MemberCsv>();
		strategy.setType(MemberCsv.class);
		Hashtable<String, String> mapping = new Hashtable<String, String>();
		mapping.put("Id", "memeberId");
		mapping.put("FirstName", "firstName");
		mapping.put("LastName", "lastName");
		mapping.put("SpouseFirstName", "spouseFirstName");
		mapping.put("SpouseLastName", "spouseLastName");
		mapping.put("Email1", "email1");
		mapping.put("Email2", "email2");
		mapping.put("Email3", "email3");
		mapping.put("Phone1", "phone1");
		mapping.put("Phone2", "phone2");
		mapping.put("Phone3", "phone3");
		mapping.put("Address1", "address1");
		mapping.put("Address2", "address2");
		mapping.put("City", "city");
		mapping.put("State", "state");
		mapping.put("ZipCode", "zipCode");
		mapping.put("Country", "country");
		mapping.put("Since", "since");
		mapping.put("Type", "type");
		mapping.put("Veg", "veg");
		mapping.put("VegAmount", "vegAmount");		
		mapping.put("NonVeg", "nonVeg");
		mapping.put("NonVegAmount", "nonVegAmount");		
		mapping.put("Kids", "kids");
		strategy.setColumnMapping(mapping);
		return strategy;
	}

	/**
	 * CSV Checkedout Members
	 * 
	 * @return
	 */
	private HeaderColumnNameTranslateMappingStrategy<CheckedOutMembersCsv> getCheckedOutColumnMappingStrategy() {
		HeaderColumnNameTranslateMappingStrategy<CheckedOutMembersCsv> strategy = new HeaderColumnNameTranslateMappingStrategy<CheckedOutMembersCsv>();
		strategy.setType(CheckedOutMembersCsv.class);
		Hashtable<String, String> mapping = new Hashtable<String, String>();
		mapping.put("Id", "memeberId");
		mapping.put("FirstName", "firstName");
		mapping.put("LastName", "lastName");
		mapping.put("Wristbands", "wristbands");
		mapping.put("AddressChanged", "addressChanged");
		mapping.put("ThendralMullai", "thendralMullai");
		strategy.setColumnMapping(mapping);
		return strategy;
	}

	/**
	 * Add Image into Email body.
	 * 
	 * @param multipart
	 *            Multipart
	 * @param cid
	 *            Image Content ID
	 * @param image
	 *            Image URL/Path/Location in the war file
	 * @throws MessagingException
	 *             Exception
	 * @throws IOException
	 *             Exception
	 */
	private void addImage(MimeMultipart multipart, final String cid, final String image)
			throws MessagingException, IOException {
		// MimeBodyPart imagePart = new MimeBodyPart();
		// imagePart.setHeader("Content-ID", cid);
		// imagePart.setDisposition(MimeBodyPart.INLINE);
		// // attach the image file
		// File file = new
		// File(getClass().getClassLoader().getResource(image).getPath());
		// imagePart.attachFile(file.getAbsolutePath());
		// multipart.addBodyPart(imagePart);

		MimeBodyPart imgBodyPart = new MimeBodyPart();
		File file = new File(getClass().getClassLoader().getResource(image).getPath());
		imgBodyPart.attachFile(file.getAbsolutePath());
		imgBodyPart.setContentID('<' + cid + '>');
		imgBodyPart.setDisposition(MimeBodyPart.INLINE);
		imgBodyPart.setHeader("Content-Type", "image/png");

		multipart.addBodyPart(imgBodyPart);
	}

	/**
	 * Add Image into Email body.
	 * 
	 * @param multipart
	 *            Multipart
	 * @param cid
	 *            Image Content ID
	 * @param image
	 *            Image URL/Path/Location in the war file
	 * @throws MessagingException
	 *             Exception
	 * @throws IOException
	 *             Exception
	 */
	private void addImageLocal(MimeMultipart multipart, final String cid, final String image) throws Exception {
		// MimeBodyPart imagePart = new MimeBodyPart();
		// imagePart.setHeader("Content-ID", cid);
		// imagePart.setDisposition(MimeBodyPart.INLINE);
		// // attach the image file
		// File file = new File(image);
		// imagePart.attachFile(file);
		// multipart.addBodyPart(imagePart);

		MimeBodyPart imgBodyPart = new MimeBodyPart();
		File file = new File(image);
		imgBodyPart.attachFile(file);
		imgBodyPart.setContentID('<' + cid + '>');
		imgBodyPart.setDisposition(MimeBodyPart.INLINE);
		imgBodyPart.setHeader("Content-Type", "image/png");

		multipart.addBodyPart(imgBodyPart);
	}

	public Configuration getFreemarkerConfiguration() {
		return freemarkerConfiguration;
	}

	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}
