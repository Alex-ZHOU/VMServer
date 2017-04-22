package test;

import java.util.Calendar;

public class TestCalendar {
	public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();
		
		System.out.println(calendarToString(calendar));
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(Calendar.MINUTE));
		System.out.println(calendar2Time(calendar));
	}
	
    /**
     * 将日期转换为字符串
     *
     * @param calendar 输入的日期
     * @return 返回字符串时间 2017-01-01
     */
    private static String calendarToString(Calendar calendar) {
        String str;
        
        str = calendar.get(Calendar.YEAR) + "-" +
                (calendar.get(Calendar.MONTH) + 1 > 9 ? (calendar.get(Calendar.MONTH) + 1) : "0" + (calendar.get(Calendar.MONTH) + 1)) + "-" +
                (calendar.get(Calendar.DAY_OF_MONTH) > 9 ? calendar.get(Calendar.DAY_OF_MONTH) : "0" + calendar.get(Calendar.DAY_OF_MONTH));
        
        return str;
    }
    
    public static String calendar2Time(Calendar calendar){
    	 String str;
    	 
    	 str = (calendar.get(Calendar.HOUR_OF_DAY)<10?"0"+calendar.get(Calendar.HOUR_OF_DAY):calendar.get(Calendar.HOUR_OF_DAY)) +
    			 ":"+
    			 (calendar.get(Calendar.MINUTE)<10?"0"+calendar.get(Calendar.MINUTE):calendar.get(Calendar.MINUTE));
    	 
    	 return str;
    }
    
}
