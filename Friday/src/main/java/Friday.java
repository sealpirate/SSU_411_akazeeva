import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Асель on 15.01.2016.
 */

public class Friday {

    public static boolean isValid(String str)
    {  boolean check = true;
       int n;
        try
        {
             n = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            check = false;
        }
        if (!check)
            System.out.println("Ошибка. Введите целое число");
        else {
            n = Integer.parseInt(str);
            if ((n < 1880) || (n > 2200)) {
                System.out.println("Введите целое число из интервала [1880, 2200]");
                check = false;

            }
        }
        return check;


    }

  //public String getMonths(boolean IsPrint, int year){

  //}

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        for (;;) {
            System.out.println("Введите год");
            Scanner scanner = new Scanner(System.in);
            String str="";

            boolean check = false;

            while (!check){
                str = scanner.nextLine();
                check = isValid(str);

            }
            int year = Integer.parseInt(str);
            System.out.println("год: " + year);
            Locale locale = Locale.getDefault();
            String dayOfWeek; //= calendar.getDisplayName(calendar.DAY_OF_WEEK, calendar.SHORT, Locale.ENGLISH);


            int countOfFridays = 0;
            String Months = "";
            String currentMonth;

            for (int i = 0; i < 11; i++) {
                calendar.set(year, i, 13);
                dayOfWeek = calendar.getDisplayName(calendar.DAY_OF_WEEK, calendar.SHORT, Locale.ENGLISH);
                if (dayOfWeek.equals("Fri")) {
                    countOfFridays++;
                    currentMonth = calendar.getDisplayName(calendar.MONTH, calendar.SHORT, Locale.ENGLISH);
                    Months += " " + currentMonth;


                }


            }


            System.out.println("number of fridays: " + countOfFridays);
            System.out.println("Months: " + Months);

        }



    }
}
