package cn.monstar.payment.domain.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/11/24 下午5:50
 */
public class StringUtil {

	public static final int RANDOM_MIX = 0;
	public static final int RANDOM_ONLY_NUMBER = 1;
	public static final int RANDOM_ONLY_LETTER = 2;

	private static final String BASE_STR1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String BASE_STR2 = "0123456789";
	private static final String BASE_STR3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 随机生成指定长度的字符串
	 * generate random string with length
	 *
	 * @param length String length
	 * @param type   String type
	 * @return
	 */
	public static String getRandomString(int length, int type) {
		String base;
		if (type == RANDOM_MIX) {
			base = BASE_STR1;
		} else if (type == RANDOM_ONLY_NUMBER) {
			base = BASE_STR2;
		} else if (type == RANDOM_ONLY_LETTER) {
			base = BASE_STR3;
		} else {
			base = BASE_STR1;
		}

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * group number using default ',' and size 4
	 *
	 * @param bg
	 * @return '¥ 1,0000.00'
	 */
	public static String groupFormat(BigDecimal bg) {
		if (bg == null) {
			return "0.00";
		}
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.CHINA);
		formatter.setGroupingSize(4);
		return formatter.format(bg);
	}

	/**
	 * uuid生成器
	 * @return
	 */
	public static String uuidGenerator(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}



}
