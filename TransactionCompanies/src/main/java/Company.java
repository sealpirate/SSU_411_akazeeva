import java.util.HashMap;

/**
 * Created by Асель on 26.02.2016.
 */
public class Company {
    private String nameCompany;
    private Integer accountnumber;
    private Integer budget;

    public Company(String nameCompany, Integer accountnumber, Integer budget){
        this.nameCompany = nameCompany;
        this.accountnumber = accountnumber;
        this.budget = budget;



    }

    public String getNameCompany(){
        return this.nameCompany;
    }
    public Integer getAccountnumber(){
        return this.accountnumber;
    }
    public Integer getBudget(){
        return this.budget;
    }

    public void changeBudget(boolean isSum, Integer newBudget){
        if (isSum){
            this.budget += newBudget;
        }
        else this.budget -= newBudget;

    }



    @Override
    public String toString(){
        return nameCompany + ";" + accountnumber +";" + budget + ";";
    }
}
