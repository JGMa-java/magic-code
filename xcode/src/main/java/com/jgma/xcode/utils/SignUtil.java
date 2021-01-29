package com.jgma.xcode.utils;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * .接口sign生成工具类 todo 半成品
 * </p>
 * 
 * @author Bruce Lin
 * @version 1.0
 */
public class SignUtil {

	private static final Pattern p = Pattern.compile("\\s*|\t|\r|\n");

//	public static String getSign(String url, String secret) throws Exception {
//		String[] urls = url.split("&");
//		return signing(urls, secret);
//	}

	public static String getSign(Map<String, String> params, String secret)
			throws Exception {

		// 先将参数以其参数名的字典序升序进行排序
		SortedMap<String, String> sortedParams = new TreeMap<String, String>(
				params);
		Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> param : entrys) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			String value = param.getValue();
			if (StringUtils.isNotBlank(value)) {
				sb.append(param.getKey()).append("=").append(value);
			}
		}
		// 使用MD5/HMAC加密
		byte[] bytes = encryptHMAC(sb.toString(), secret);
		// 把二进制转化为大写的十六进制
		return byte2hex(bytes);
	}

//	private static String signing(String[] urls, String secret)
//			throws Exception {
//		Map<String, String> params = new HashMap<String, String>();
//		for (int i = 0; i < urls.length; i++) {
//			String item[] = urls[i].split("=");
//			params.put(item[0], item[1]);
//		}
//		return getSign(params, secret);
//	}

	private static byte[] encryptHMAC(String data, String secret)
			throws Exception {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"),
					"HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes("UTF-8"));
		} catch (Exception gse) {
			throw new Exception("生成签名信息出错！", gse);
		}
		return bytes;
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString().toLowerCase();
	}

//	public static String replaceBlank(String str) {
//		String dest = "";
//		if (str != null) {
//			Matcher matcher = p.matcher(str);
//			dest = matcher.replaceAll("");
//		}
//		return dest;
//	}
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("client_user_id", "1");
		map.put("broker_user_id", "1");
		map.put("type", "1");

		try {
			System.out.println(getSign(map, "ea8a706c4c34a168"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
