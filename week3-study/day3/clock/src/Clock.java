import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Clock {


    public static void printTime() {
        LocalTime localTime = LocalTime.now();
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.printf("현재 시간: %d시 %d분 %d초", hour, minute, second);
    }

    public static void printCalender() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int month = localDateTime.getMonthValue();
        int date = localDateTime.getDayOfMonth();
        System.out.printf("오늘 날짜: %d월 %d일", month, date);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input;
        do{
            input = sc.nextLine();
        }while(!input.equals("clock") && !input.equals("cal"));

        if (input.equals("clock")) {
            printTime();
        } else {
            printCalender();
        }
    }
}
