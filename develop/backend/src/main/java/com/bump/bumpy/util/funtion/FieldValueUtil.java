package com.bump.bumpy.util.funtion;

import com.bump.bumpy.security.principal.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FieldValueUtil {
	
	public static String doubleToPlainString(Double value, @NotNull int scale) {
		if(value == null) {
			return null;
		}
		else {
			return new BigDecimal(value).setScale(scale, RoundingMode.HALF_EVEN).stripTrailingZeros().toPlainString();
		}
	}
	
	public static String doubleToPlainString(Double value) {
		return doubleToPlainString(value, 12);
	}
	
	public static String integerToString(Integer value) {
		if(value == null) {
			return null;
		}
		else {
			return value.toString();
		}
	}

	public static String longToString(Long value) {
		if(value == null) {
			return null;
		}
		else {
			return value.toString();
		}
	}
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd");
	}
	//yyyy-mm-dd 형태
	public static String dateToString(Date date, String pattern) {
		//변경이 필요할 수 있음
		if(date == null) {
			return "";
		}
		else {
			return new SimpleDateFormat(pattern).format(date);
		}
	} 
	public static Date stringToDate(String date) {
		return stringToDate(date, "yyyy-MM-dd");
	}
	public static Date stringToDate(String date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static boolean yesOrNoFieldToBoolean(String value) {
		if(value.isBlank()) return false;
		return value.equals("Y");
	}

	/**
	 * BigDecimal Data Rescaling
	 * @param target 변경할 데이터
	 * @param newScale 스케일 사이즈
	 * @param roundingMode 스케일 모드
	 * @return Rescaling Data
	 */
	public static BigDecimal bigDecimalRescale(BigDecimal target, int newScale, RoundingMode roundingMode){
		return target != null ? target.setScale(newScale, roundingMode) : null;
	}

	/**
	 * BigDecimal Data ReFormatting
	 * @param target 변경할 데이터
	 * @param format 포멧(#, 0)
	 * @return Reformatting Data
	 */
	public static String bigDecimalReformat(BigDecimal target, String format){
		return target != null ? new DecimalFormat(format).format(target) : null;
	}

	/**
	 * @param prev 비교할 값
	 * @param today 오늘 값
	 * @return BigDecimal 수소점 세자리 버림 뺄셈 계산
	 */
	public static BigDecimal bigDecimalDiff(BigDecimal prev, BigDecimal today) {
		if(prev == null || today == null) {
			return null;
		}
		else {
			return today.subtract(prev).setScale(3, RoundingMode.DOWN);
		}
	}

	/**
	 * @param value BigDecimal로 변환할 값
	 * @return String -> BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object value) {
		if(value == null) return null;

		if(value instanceof BigDecimal) {
			return (BigDecimal)value;
		}
		else {
			return new BigDecimal(value.toString());
		}
	}

	/**
	 * @param date Day Of Week 로 변환할 날짜 객체
	 * @return Date -> int(Day of Week - 1(Sun) ~ 7(Sat))
	 */
	public static int dateToDayOfWeekValue(Date date){
		if(date == null) return -1;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * @param dow 요일명칭으로 변환할 Day Of Week Value
	 * @return int(Day of Week - 1(Sun) ~ 7(Sat)) -> String(일 ~ 토)
	 */
	public static String toDayOfWeekNameInKr(int dow){
		String dowName = null;
		if(dow <= 0 || dow > 7) return dowName;
		switch (dow){
			case 1: dowName = "일"; break;
			case 2: dowName = "월"; break;
			case 3: dowName = "화"; break;
			case 4: dowName = "수"; break;
			case 5: dowName = "목"; break;
			case 6: dowName = "금"; break;
			case 7: dowName = "토"; break;
		}
		return dowName;
	}

	public static Date getTodayDate() {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());

		// 00시 00분 00초
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		return today.getTime();
	}

	public static boolean isTodayDate(Date date) {
		Calendar today = Calendar.getInstance();
		Calendar stdDate = Calendar.getInstance();
		today.setTime(new Date());
		stdDate.setTime(date);

		if(today.get(Calendar.YEAR) == stdDate.get(Calendar.YEAR) &&
				today.get(Calendar.MONTH) == stdDate.get(Calendar.MONTH) &&
				today.get(Calendar.DATE) == stdDate.get(Calendar.DATE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isYesterDayDate(Date date) {
		Calendar yesterDay = Calendar.getInstance();
		Calendar stdDate = Calendar.getInstance();
		yesterDay.setTime(new Date());
		stdDate.setTime(date);

		yesterDay.add(Calendar.DATE, -1);

		if(yesterDay.get(Calendar.YEAR) == stdDate.get(Calendar.YEAR) &&
				yesterDay.get(Calendar.MONTH) == stdDate.get(Calendar.MONTH) &&
				yesterDay.get(Calendar.DATE) == stdDate.get(Calendar.DATE)) {
			return true;
		} else {
			return false;
		}
	}

	// isSameDate
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal2.setTime(date2);

		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
				cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);
	}

	public static String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) {
			return null;
		}
		else {
			Object principal = authentication.getPrincipal();
			if(principal instanceof PrincipalDetails) {
				return ((PrincipalDetails) principal).getUsername();
			}
			else {
				return principal.toString();
			}
		}
//		return "test2614";
	}

	public static Calendar setZeroTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static boolean isStartWithKorean(String str) {
		if(str == null || str.length() == 0) {
			return false;
		}
		else {
			char ch = str.charAt(0);
			return ch >= 0xAC00 && ch <= 0xD7A3;
		}
	}

	private FieldValueUtil() {}

	public static MultipartFile[] mergeMultipartFile(MultipartFile[] list1, MultipartFile[] list2) {
		MultipartFile[] files = null;
		if (list1 != null && list2 != null) {
			files = new MultipartFile[list1.length + list2.length];
			System.arraycopy(list1, 0, files, 0, list1.length);
			System.arraycopy(list2, 0, files, list1.length, list2.length);
		} else if (list1 != null) {
			files = list1;
		} else if (list2 != null) {
			files = list2;
		}
		return files;
	}

	public static Date getFirstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}

	public static Date getFirstDateOfPrevMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date getLastDateOfPrevMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}

	public static Date getFirstDateOfNextMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date getLastDateOfNextMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}
}
