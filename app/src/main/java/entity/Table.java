package entity;

/**
 * Created by jr on 8/30/14.
 */
public class Table {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String table_id;

    /**
     * This table id is String And will show table name like
     * Vanilla,VIP,Guest etc
     */
    public String getTable_id() {
        return table_id;
    }
    /**
     * This will set table id
     * */
    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    private String table_name;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    private Table_status table_status;

    public Table_status getTable_status() {
        return table_status;
    }

    public void setTable_status(Table_status table_status) {
        this.table_status = table_status;
    }


    private boolean is_reserved;

    public boolean isIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }

    private String from_time;

    private String to_time;

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }
}
