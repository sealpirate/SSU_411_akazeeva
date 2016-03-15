import java.util.List;

/**
 * Created by Асель on 26.02.2016.
 */
public class Transaction {
    private String senderName;
    private Integer senderAcc;

    private String recipientName;
    private Integer recipientAcc;

    private Integer sendBudget;

    public Transaction(String senderName, Integer senderAcc, String recipientName, Integer recipientAcc,
                       Integer sendBudget){
        this.senderName = senderName;
        this.senderAcc = senderAcc;
        this.recipientName = recipientName;
        this.recipientAcc = recipientAcc;
        this.sendBudget = sendBudget;

    }



    public void makeTransaction(List<Company> companies){
        Integer twoCompaniesFound = 0;
        Company Sender = companies.get(0);
        Company Recipient = companies.get(0);
        Integer SenderBudget = 0;
        for (Company company: companies){
            if ((company.getNameCompany().equals(senderName))
            && (company.getAccountnumber().equals(senderAcc))){
                Sender = company;
                SenderBudget = Sender.getBudget();
                twoCompaniesFound++;
            }

            if ((company.getNameCompany().equals(recipientName)) &&
            (company.getAccountnumber().equals(recipientAcc))) {
                Recipient = company;
                twoCompaniesFound++;
            }
        }
        if (twoCompaniesFound == 2) {
            if (SenderBudget >= sendBudget) {
                Recipient.changeBudget(true, sendBudget);
                Sender.changeBudget(false, sendBudget);
                System.out.println("Транзакция прошла удачно");
                System.out.println("Компания " + Sender.getNameCompany() + " перевела " +
                        sendBudget + " со счета "
                        + Sender.getAccountnumber() + " на счет " + Recipient.getAccountnumber() + " компании " +
                        Recipient.getNameCompany());

            } else {
                System.out.println("Ошибка, недостаточно денег.");
            }
        }
        else System.out.println("Неверный список компаний.");

    }



}
