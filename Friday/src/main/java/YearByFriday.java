import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Асель on 21.01.2016.
 */
public class YearByFriday {
    //реализовать одной функцией с интервалом в качестве аргумента?
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
            if ((n < 0) || (n > 11)) {
                System.out.println("Введите целое число из интервала [0, 11]");
                check = false;
            }
        }
        return check;


    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        for (;;) {
            System.out.println("Введите номер месяца");
            Scanner scanner = new Scanner(System.in);
            String str="";

            boolean check = false;

            while (!check){
                str = scanner.nextLine();
                check = isValid(str);

            }
            int Month = Integer.parseInt(str);
            System.out.println("номер месяца: " + Month);
            Locale locale = Locale.getDefault();
          //  String currentYear = calendar.getDisplayName(calendar.YEAR, calendar.SHORT, Locale.ENGLISH);
           int yearOfEnd = calendar.get(Calendar.YEAR);
            System.out.println("Текущий год " + yearOfEnd);
           // int yearOfEnd = Integer.parseInt(currentYear);
            int countOfFridays = 0;
            String Months = "";
            String currentMonth;
            Friday friday = new Friday();
            String dayOfWeek;
            int numberOfYears=0;



            for (int i = 1880; i < yearOfEnd; i++) {
                calendar.set(i, Month, 13);
                dayOfWeek = calendar.getDisplayName(calendar.DAY_OF_WEEK, calendar.SHORT, Locale.ENGLISH);
                if (dayOfWeek.equals("Fri")) {
                    System.out.println(i);
                    numberOfYears++;
                }


            }
            System.out.println("Количество лет: " + numberOfYears);




        }




    }
}
