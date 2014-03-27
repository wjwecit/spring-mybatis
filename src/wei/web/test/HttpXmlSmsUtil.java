/**
 * 
 */
package wei.web.test;

import java.security.MessageDigest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * ����ͨ�ṩ�Ķ��Žӿ�,����HTTP��ʽ����ͨ��.
 * 
 * @author wei
 * @2014-01-14
 */
@SuppressWarnings("unused")
public class HttpXmlSmsUtil {

	public static String phone = "18503078704"; // Ҫ���͵��ֻ�����
	public static String content = "2014-1-15,���ڸ��A���ݷ������ݹ��ˣ����ٶȾ�Ԯ"; // ��������
	// public static String sign = "������ǩ����"; // ����ǩ������ǩ��������ǰ����
	public static String sign = ""; // ����ǩ������ǩ��������ǰ����
	public static String msgid = ""; // �Զ���msgid
	public static String subcode = ""; // ��չ�ֺ�
	public static String sendtime = ""; // ��ʱ����ʱ�䣬ʱ���ʽ201305051230
	public static String url = "http://3tong.net"; // ����ͨʹ�õ�ַ
	public static String userName = "dh20861";
	public static String password = "dd123.com";
//	public static String userName = "dh21056";
//	public static String password = "21056.yt";

	/** ��־ **/
	public static final Logger log = Logger.getLogger(HttpXmlSmsUtil.class);

	// public static String url="http://www.10690300.com"; //����ͨʹ�õ�ַ

	// ############################�˲��ֲ�����Ҫ�޸�
	public static void main(String[] args) {
		// ���Ͷ���
		System.out.println("*************���Ͷ���*************");
		//send("��ϣ֮�⡿","18503078704",content);
		send("18503078704",content);
		// ��ȡ״̬����
		//System.out.println("*************״̬����*************");
		//getReport();
		// ��ȡ����
		//System.out.println("*************��ȡ����*************");
		//getSms();
		// ��ȡ���
		//System.out.println("*************��ȡ���*************");
		//getBalance();
	}

	// MD5 ���ܺ���
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}

	/**
	 * ���Ͷ��ŷ���ʹ��document ���󷽷���װXML �ַ���
	 */
	private static void send() {
		String posturl = url + "/http/sms/Submit";
		Map<String, String> params = new LinkedHashMap<String, String>();
		/*
		 * String message = "<?xml version='1.0' encoding='UTF-8'?><message>" +
		 * "<account>" + userName + "</account>" + "<password>" +
		 * MD5Encode(password) + "</password>" + "<msgid></msgid><phones>" +
		 * phone + "</phones><content>" + content + "</content>" +
		 * "<sign>"+sign+
		 * "</sign><subcode></subcode><sendtime></sendtime></message>";
		 */
		String message = DocXml(userName, MD5Encode(password), msgid, phone, content, sign,
				subcode, sendtime);
		System.out.println(message);
		params.put("message", message);
		String resp = doPost(posturl, params);

		System.out.println(resp);
		String regex=".*(?:<result>0</result>){1}.*";
		if (resp.matches(regex)) {
			log.info("��Ϣ���ͳɹ�!�����ֻ���:"+phone+";��������:"+content);
			System.out.println(new Date()+"��Ϣ���ͳɹ�!�����ֻ���:"+phone+";��������:"+content);
		}
	}

	/**
	 * send message to specified phone number.
	 * 
	 * @param to
	 *            the receiver phone
	 * @param content
	 *            message content
	 * @return true if send success else false
	 */
	public static boolean send(String to, String content) {
		String posturl = url + "/http/sms/Submit";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = DocXml(userName, MD5Encode(password), msgid, to, content, sign, subcode,sendtime);
		log.info(message);
		params.put("message", message);
		String resp = doPost(posturl, params);
		log.info(resp);
		String regex = ".*(?:<result>0</result>){1}.*";
		if (resp.matches(regex)) {
			log.info("��Ϣ���ͳɹ�!�����ֻ���:"+to+";��������:"+content);
			return true;
		}
		return false;
	}
	
	/**
	 * send message to specified phone number.
	 * @param sign 
	 * 			  signature of this message
	 * @param to
	 *            the receiver phone
	 * @param content
	 *            message content
	 * @return true 
	 * 			  if send success else false
	 */
	public static boolean send(String sign, String to, String content) {
		String posturl = url + "/http/sms/Submit";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = DocXml(userName, MD5Encode(password), msgid, to, content, sign, subcode,sendtime);
		log.info(message);
		System.out.println(new Date()+message);
		params.put("message", message);
		String resp = doPost(posturl, params);
		log.info(resp);
		System.out.println(new Date()+resp);
		String regex = ".*(?:<result>0</result>){1}.*";
		if (resp.matches(regex)) {
			log.info("��Ϣ���ͳɹ�!�����ֻ���:"+to+";��������:"+content);
			System.out.println(new Date()+"��Ϣ���ͳɹ�!�����ֻ���:"+to+";��������:"+content);
			return true;
		}
		return false;
	}

	// ״̬����
	private static void getReport() {
		String posturl = url + "/http/sms/Report";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message>" + "<account>" + userName
				+ "</account>" + "<password>" + MD5Encode(password) + "</password>"
				+ "<msgid></msgid><phone></phone></message>";
		params.put("message", message);
		String resp = doPost(posturl, params);
		System.out.println(resp);
	}

	// ��ѯ���
	
	private static void getBalance() {
		String posturl = url + "/http/sms/Balance";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message><account>" + userName
				+ "</account>" + "<password>" + MD5Encode(password) + "</password></message>";
		params.put("message", message);
		System.out.println(message);
		String resp = doPost(posturl, params);
		System.out.println(resp);
	}

	// ��ȡ����
	private static void getSms() {
		String posturl = url + "/http/sms/Deliver";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message><account>" + userName
				+ "</account>" + "<password>" + MD5Encode(password) + "</password></message>";
		params.put("message", message);
		String resp = doPost(posturl, params);
		System.out.println(resp);
	}

	/**
	 * ִ��һ��HTTP POST ���󣬷���������Ӧ��HTML
	 * 
	 * @param url
	 *            �����URL ��ַ
	 * @param params
	 *            ����Ĳ�ѯ����,����Ϊnull
	 * @return ����������Ӧ��HTML
	 */
	private static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// ����Post ����
		if (!params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			postMethod.setRequestBody(data);
		}
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * ʹ��document �����װXML
	 * 
	 * @param userName
	 * @param pwd
	 * @param id
	 * @param phone
	 * @param contents
	 * @param sign
	 * @param subcode
	 * @param sendtime
	 * @return
	 */
	public static String DocXml(String userName, String pwd, String msgid, String phone,
			String contents, String sign, String subcode, String sendtime) {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element message = doc.addElement("message");
		Element account = message.addElement("account");
		account.setText(userName);
		Element password = message.addElement("password");
		password.setText(pwd);
		Element msgid1 = message.addElement("msgid");
		msgid1.setText(msgid);
		Element phones = message.addElement("phones");
		phones.setText(phone);
		Element content = message.addElement("content");
		content.setText(contents);
		Element sign1 = message.addElement("sign");
		sign1.setText(sign);
		Element subcode1 = message.addElement("subcode");
		subcode1.setText(subcode);
		Element sendtime1 = message.addElement("sendtime");
		sendtime1.setText(sendtime);
		return message.asXML();
	}

}
