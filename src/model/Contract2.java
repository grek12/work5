package model;

import java.sql.Date;

public class Contract2 {
    private int idContract;
    private Date date;
    private double amount;
    private String Seller ;
    private String Client ;

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }
}
