package com.example.username.weddingplanning;

/**
 * Created by Sharu Vive on 2/07/2016.
 */
public class Expence {
    private String category;
    private String description;
    private String amount;
    private String date;
    private String status;
    private String year;
    private String month;

    public Expence(String e_id) {
        this.e_id = e_id;
    }

    private String e_id;

    public Expence() {

    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    //get methods to access these variables
    public String getCategory() {
        return category;
    }

    //set methods to modify them
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
