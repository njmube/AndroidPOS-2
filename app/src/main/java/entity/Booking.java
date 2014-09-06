package entity;

/**
 * Created by jr on 9/6/14.
 */
public class Booking {
    private int id;
    private String bdate;
    private String btimefrom;
    private String btimeto;
    private Table table;
    private Customer customer;
    private int no_of_table;
    private int no_of_people;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getBtimefrom() {
        return btimefrom;
    }

    public void setBtimefrom(String btimefrom) {
        this.btimefrom = btimefrom;
    }

    public String getBtimeto() {
        return btimeto;
    }

    public void setBtimeto(String btimeto) {
        this.btimeto = btimeto;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getNo_of_table() {
        return no_of_table;
    }

    public void setNo_of_table(int no_of_table) {
        this.no_of_table = no_of_table;
    }

    public int getNo_of_people() {
        return no_of_people;
    }

    public void setNo_of_people(int no_of_people) {
        this.no_of_people = no_of_people;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
