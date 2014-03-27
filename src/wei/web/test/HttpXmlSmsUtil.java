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
 * 大汉三通提供的短信接口,采用HTTP方式进行通信.
 * 
 * @author wei
 * @2014-01-14
 */
@SuppressWarnings("unused")
public class HttpXmlSmsUtil {

	public static String phone = "18503078704"; // 要发送的手机号码
	public static String content = "2014-1-15,深圳福田，A电梯发生电梯关人，请速度救援"; // 短信内容
	// public static String sign = "【后置签名】"; // 短信签名，该签名必须提前报备
	public static String sign = ""; // 短信签名，该签名必须提前报备
	public static String msgid = ""; // 自定义msgid
	public static String subcode = ""; // 扩展字号
	public static String sendtime = ""; // 定时发送时间，时间格式201305051230
	public static String url = "http://3tong.net"; // 无限通使用地址
	public static String userName = "dh20861";
	public static String password = "dd123.com";
//	public static String userName = "dh21056";
//	public static String password = "21056.yt";

	/** 日志 **/
	public static final Logger log = Logger.getLogger(HttpXmlSmsUtil.class);

	// public static String url="http://www.10690300.com"; //三网通使用地址

	// ############################此部分参数需要修改
	public static void main(String[] args) {
		// 发送短信
		System.out.println("*************发送短信*************");
		//send("【希之光】","18503078704",content);
		send("18503078704",content);
		// 获取状态报告
		//System.out.println("*************状态报告*************");
		//getReport();
		// 获取上行
		//System.out.println("*************获取上行*************");
		//getSms();
		// 获取余额
		//System.out.println("*************获取余额*************");
		//getBalance();
	}

	// MD5 加密函数
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
	 * 发送短信方法使用document 对象方法封装XML 字符串
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
			log.info("信息发送成功!接收手机号:"+phone+";发送内容:"+content);
			System.out.println(new Date()+"信息发送成功!接收手机号:"+phone+";发送内容:"+content);
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
			log.info("信息发送成功!接收手机号:"+to+";发送内容:"+content);
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
			log.info("信息发送成功!接收手机号:"+to+";发送内容:"+content);
			System.out.println(new Date()+"信息发送成功!接收手机号:"+to+";发送内容:"+content);
			return true;
		}
		return false;
	}

	// 状态报告
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

	// 查询余额
	
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

	// 获取上行
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
	 * 执行一个HTTP POST 请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL 地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	private static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置Post 数据
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
	 * 使用document 对象封装XML
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
