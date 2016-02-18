import sun.invoke.empty.Empty;

import java.util.*;
import java.util.regex.*;

/**
 * Created by Асель on 22.01.2016.
 */


public class ConsoleCalculator{

    public static boolean checkException=true;



    private static boolean isOperation(String operation) {
        Pattern p = Pattern.compile("([\\+\\-\\*\\^\\/])");
        Matcher m = p.matcher(operation);
        return m.matches();

    }

    private static boolean IsCorrect(String str){


      //  Pattern p = Pattern.compile("^(\\()?\\-?(\\d+(\\.\\d*)?|\\.\\d+)[*+-]|/(?!0)+\\-?(\\d+(\\.\\d*)?|\\.\\d+)+(\\))?$");
       // Pattern p = Pattern.compile("^(?:(\\()?\\d+([*+-^]|/(?!0)))+\\d+(\\))?$");
        Pattern p = Pattern.compile("^(?:(\\()?(\\d+(\\.\\d*)?|\\.\\d+)([*+-^]|/(?!0)))+?(\\d+(\\.\\d*)?|\\.\\d+)(\\))?$");
        Matcher m = p.matcher(str);
        return m.matches();
    }




    private static byte priorOperation(String op) {
       if (op.equals("^")) return 4;
        else if (op.equals("*")||op.equals("/")) return 3;
        else if(op.equals("+")||op.equals("-")) return 2;
        else return 1;
    }

    public static boolean IsNumber(String str) {
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
       // if (str == null) return false;
      //  return str.matches("^-?\\d+$");
    }

    public static boolean IsCorrectSymbol(String str){
        if ((IsNumber(str))||(isOperation(str))|| str.equals(".")||
                (str.equals("("))||str.equals(")")) return true;
        else return false;
    }



private static String convertReverse(String str) throws Exception{
    Stack <String> stack = new Stack<String>();
    String result="";
    String symbol;
    String topElement="";
    boolean check;
    int j = 1;
    for (int i = 0; i< str.length();){
        j = 1;
        check = true;
        symbol = str.substring(i, i+j);
        //if IsNumber(str)
        if (!IsNumber(symbol))
            check = false;
        while (check){
            j++;
            if (i+j <= str.length())
             symbol = str.substring(i, i+j);
            else check = false;
            if (!IsNumber(symbol)){
                check = false;
                j--;
                symbol = str.substring(i, i+j);

            }


        }

        //если это одна из операций
        if (isOperation(symbol)){
            //если стек пуст, добавляем операцию
            if (stack.isEmpty()){
                stack.push(symbol);
            }
            else {
                topElement = stack.peek();
                    while ((priorOperation(topElement) >= priorOperation(symbol))
                            &&(!stack.isEmpty())){
                        topElement=stack.pop();
                        result+=topElement + " ";
//
            if (!stack.isEmpty())           topElement=stack.peek();

                }
                stack.push(symbol);


            }

        }
        else if (symbol.equals("("))
                stack.push(symbol);
        else if (symbol.equals(")")){
            topElement = stack.peek();
            while (!topElement.equals("(")&&(!stack.isEmpty())){
                topElement = stack.pop();
                if (!topElement.equals("("))
                result+=topElement + " ";
                if (!stack.isEmpty())           topElement=stack.peek();

            }
            if (topElement.equals("(")) topElement = stack.pop();
        }
        else result+=symbol + " ";

        if (j > 1) i+=j;
        else i++;
    }
    while (!stack.isEmpty()){
        topElement = stack.pop();
      if (!topElement.equals("(")||(topElement.equals(")")))
        result += topElement + " ";
      else return "Неверно расставлены скобки";
    }
    return result;
}



    private static Double CalculateResult(String str) throws Exception{
        Stack <Double> stack = new Stack<Double>();
        int endOfSymbol=-1, beginOfSymbol = 0, currentIndex;
        String Symbol;
        Double number=1.0;
        Double a, b;
        Double resultOperation=1.0;

        while (endOfSymbol < str.length() - 1) {
            beginOfSymbol = endOfSymbol + 1;
            endOfSymbol = str.indexOf(" ", beginOfSymbol);
            if (endOfSymbol == -1)
                endOfSymbol = str.length();
            Symbol = str.substring(beginOfSymbol, endOfSymbol);


            if (IsNumber(Symbol)){
                try {
                    number = Double.parseDouble(Symbol);
                } catch (Exception e){

                }
                stack.push(number);

            }
             if (isOperation(Symbol)){
                try {
                    a = stack.pop();
                    b = stack.pop();
                    if (Symbol.equals("+"))
                        resultOperation = b + a;
                    if (Symbol.equals("-"))
                        resultOperation = b - a;
                    if (Symbol.equals("*"))
                        resultOperation = b * a;
                    if (Symbol.equals("/"))
                        resultOperation = b / a;
                    if (Symbol.equals("^"))
                        resultOperation = Math.pow(b, a);
                    stack.push(resultOperation);

                } catch (EmptyStackException e){
                    System.out.println("Неверный ввод");
                    checkException = false;


                    return 1.0;
                }




            }



        }


        if (!stack.isEmpty())
        return stack.peek();
        else return 1.0;
    }


    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean check = true;
        Double resultCalc;

        while (check) {
            boolean checkCorrect = true;
            System.out.println("Введите арифметическое выражение");
            String s = scan.nextLine();
            if (s.equals("Close")||(s.equals("close"))){
                check = false;
                System.exit(0);
            }
            else {
                if (IsCorrect(s))
                    System.out.println("Правильно");
                else System.out.println("Неправильно");
                for (int i = 0; i<s.length()-1; i++)
                    if (!IsCorrectSymbol(s.substring(i, i+1))){
                        System.out.println("Некорректный ввод. Попробуйте ещё раз");
                        checkCorrect = false;
                        break;
                    }

            if (checkCorrect)
            try {

                String str = convertReverse(s);
                System.out.println(str);
                if (!str.equals("Неверно расставлены скобки")){
                    try {
                        resultCalc = CalculateResult(str);
                        if (checkException)
                            System.out.println(resultCalc);
                        checkException = true;
                        //else System.out.println(checkException);
                    }catch (Exception e){
                        //System.out.println("Неверный ввод");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
        scan.close();
    }
}
