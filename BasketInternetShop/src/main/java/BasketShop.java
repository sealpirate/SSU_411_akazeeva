import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_sk;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;


/**
 * Created by Асель on 19.02.2016.
 */
public class BasketShop implements Basket{
    private HashMap <String, Integer> Products;

    public BasketShop(){
        Products = new HashMap<String, Integer>();

    }
    public void addProduct(String product, int quantity){
        Products.put(product, quantity);

    }
    public void removeProduct(String product){

        try {
            Products.remove(product);
        } catch (Exception e){
            System.out.println("Продукт не найден");
        }

    }
    public void updateProductQuantity(String product, final int quantity){
        try {
            Products.put(product, quantity);
        } catch (Exception e){
            System.out.println("Продукт не найден");
        }



    }
    public void clear(){
        Products.clear();

    }
    public List<String> getProducts(){
        List <String> listOfProducts = new LinkedList<String>();
        for ( String key : Products.keySet() ){
            listOfProducts.add(key);
        }
            return listOfProducts;


    }
    public int getProductQuantity(String product){
        int quantityOfProduct=0;
        try {
            quantityOfProduct =  Products.get(product);
        } catch (Exception e){
            System.out.println("Продукт не найден");
        }

        return quantityOfProduct;
    }

    public void PrintListProducts(){
        List <String> listOfProducts = new LinkedList<String>();
        listOfProducts = getProducts();
        if (!listOfProducts.isEmpty())
        for (String s: listOfProducts){
            System.out.println(s);
        }
        else System.out.println("В корзине нет продуктов");


    }
}
