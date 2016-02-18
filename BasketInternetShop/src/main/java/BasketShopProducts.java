import sun.security.x509.IssuerAlternativeNameExtension;

import java.util.Base64;
import java.util.Scanner;

/**
 * Created by Асель on 19.02.2016.
 */
public  class BasketShopProducts  {
    public static boolean IsNumber(String string) {
        if (string == null) return false;
        return string.matches("^-?\\d+$");
    }
    public static void main(String[] args) {

        BasketShop bs = new BasketShop();
        Scanner sc = new Scanner(System.in);
        String str;
        String nameProduct, quant;
        Integer quantity=0;
        boolean checkClose = true, checkQuant=true;
        bs.addProduct("Книга", 20);
        bs.addProduct("Статуэтка", 11);
        bs.addProduct("Светильник", 1);

        while (checkClose){
            System.out.println("Введите команду");
            str = sc.nextLine();
            checkQuant=true;
            if (str.equals("add")){
                System.out.println("Добавление продукта");
                nameProduct = sc.nextLine();
                while (checkQuant) {
                    quant = sc.nextLine();

                    if (IsNumber(quant)) {
                        quantity = Integer.parseInt(quant);
                        if (quantity>0)
                            checkQuant = false;
                        else System.out.println("Введите положительное число");

                    } else {
                        System.out.println("Введите положительное число");
                    }

                }
                checkQuant = true;
                bs.addProduct(nameProduct, quantity);
                System.out.println("Продукт добавлен");


            }
            if (str.equals("close")){
                System.exit(0);
            }
            if (str.equals("print")){
                bs.PrintListProducts();
            }
            if (str.equals("rm")){
                System.out.println("Удаление продукта");
               nameProduct = sc.nextLine();
               bs.removeProduct(nameProduct);
            }
            if (str.equals("get")){
                System.out.println("Получение количества");
                nameProduct = sc.nextLine();
                bs.getProductQuantity(nameProduct);

            }
            if (str.equals("update")){
                System.out.println("Обновление количества");
                System.out.println("Введите название продукта");
                nameProduct = sc.nextLine();
                System.out.println("Введите количество");
                while (checkQuant) {
                    quant = sc.nextLine();

                    if (IsNumber(quant)) {
                        quantity = Integer.parseInt(quant);
                        if (quantity>0)
                            checkQuant = false;
                        else System.out.println("Введите положительное число");

                    } else {
                        System.out.println("Введите положительное число");
                    }

                }

                bs.updateProductQuantity(nameProduct, quantity);
                System.out.println("новое количество" + bs.getProductQuantity(nameProduct));

            }





        }

        sc.close();



    }
}
