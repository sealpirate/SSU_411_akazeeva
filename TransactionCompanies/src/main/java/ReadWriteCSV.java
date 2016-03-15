/**
 * Created by Асель on 26.02.2016.
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class ReadWriteCSV {
    private static String file_header = "Название компании;Лицевой счет;Первоначальный бюджет;";
    private  static final int Company_Name = 0;
    private  static final int Company_account_number = 1;
    private  static final int Company_budget = 2;
    public static void writeCSVFile(String fileName, List<Company> companies){

        FileWriter fw = null;
        try{
            fw = new FileWriter(fileName);
            fw.append(file_header.toString());
            fw.append("\n");

            for (Company somecompany: companies){
                fw.append(somecompany.toString());
                fw.append("\n");



            }

            System.out.println("CSV file was created successfully");

        }catch (Exception e){
            System.out.println("Error in CSVFile");
            e.printStackTrace();
        }finally {
            try{
                fw.flush();
                fw.close();
            }catch (IOException e){
                System.out.println("error");
                e.printStackTrace();
            }

        }
    }

    public static List<Company> readCSVFIleCompany(String fileName){
        BufferedReader bf = null;
        List <Company> companies = new ArrayList<Company>();
        try{
            String line = "";
            bf = new BufferedReader(new FileReader(fileName));
            bf.readLine();
            while ((line = bf.readLine())!= null){
                String [] tokens = line.split(";");
                Company company = new Company(tokens[Company_Name],
                        Integer.parseInt(tokens[Company_account_number]),
                                Integer.parseInt(tokens[Company_budget]));

                companies.add(company);

            }

            for (Company company : companies) {

                System.out.println(company.toString());

            }




        }catch (Exception e){
            System.out.println("Error in CsvFileReader !!!");

            e.printStackTrace();
        }

        try {

            bf.close();

        } catch (IOException e) {

            System.out.println("Error while closing fileReader !!!");

            e.printStackTrace();

        }



        return companies;
    }

    public static List<Transaction> readCSVFIleTransaction(String fileName){
        BufferedReader bf = null;
        List <Transaction> transactions = new ArrayList<Transaction>();
        String sendName, recipName;
        Integer sendAcc, recipAcc;
        Integer sumSend;

        Transaction transaction;
        try{
            String line = "";
            bf = new BufferedReader(new FileReader(fileName));
            bf.readLine();
            while ((line = bf.readLine())!= null){
                String [] tokens = line.split(";");
                sendName = tokens[0];
                sendAcc = Integer.parseInt(tokens[1]);
                recipName = tokens[2];
                recipAcc = Integer.parseInt(tokens[3]);
                sumSend = Integer.parseInt(tokens[4]);
                transaction  = new Transaction(sendName,
                       sendAcc,
                       recipName, recipAcc, sumSend);


                transactions.add(transaction);

            }



        }catch (Exception e){
            System.out.println("Error in CsvFileReader !!!");

            e.printStackTrace();
        }

        try {

            bf.close();

        } catch (IOException e) {

            System.out.println("Error while closing fileReader !!!");

            e.printStackTrace();

        }



        return transactions;
    }



    public static void main(String[] args) {
        List <Company> companies = new ArrayList<Company>();
        List <Company> companiesClone = new ArrayList<Company>();
        companiesClone = new ArrayList<Company>(companies);
        List <Transaction> transactions = new ArrayList<Transaction>();

        List<String> filesList = new ArrayList<String>();
        String pathToTransactions = ".\\Transactions\\";

        String pathToCompanies = ".\\Companies\\";

        File[] files = new File(pathToTransactions).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                filesList.add(file.getName());
            }
        }

        for (String fileName: filesList){
            transactions = readCSVFIleTransaction(pathToTransactions+ fileName);

        }
        filesList.clear();
        files = new File(pathToCompanies).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                filesList.add(file.getName());
            }
        }

        for (String fileName: filesList){
            companies = readCSVFIleCompany(pathToCompanies + fileName);
        }

        for (Transaction transaction: transactions){
       transaction.makeTransaction(companies);
        }

        for (Company company: companies){
            System.out.println(company.toString());

        }

        writeCSVFile("result.csv", companies);

    }
}
