

import java.time.LocalDateTime;
import java.util.Objects;

public class Action_Entry extends Entry
{
    private String original_value;
    private String action;
    private String table_name;
    private int id_record;
    private String extra;

    public Action_Entry(){}

    public Action_Entry(String entry)
    {
        this.original_value=entry;
        String[] parts=entry.split("\\s+");
        this.actor=parts[2];
        this.db_name=parts[3];
        this.table_name=parts[4];
        this.action=parts[5];
        this.id_record=Integer.valueOf(parts[6]);
        if (parts.length==8)
            this.extra=parts[7];
        String s=parts[0]+"T"+parts[1];
        this.timeStamp=LocalDateTime.parse(s);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj==null)
            return false;
        if(! (obj instanceof Action_Entry))
            return false;
        Action_Entry e=(Action_Entry) obj;
        if(!(this.action.equals(e.action)))
            return false;
        if(!(this.actor.equals(e.actor)))
            return false;
        if (!this.db_name.equals(e.db_name))
            return false;
        if (!this.table_name.equals(e.table_name))
            return false;
        if (id_record!=e.getId_record())
            return false;
        if (!this.extra.equals(e.extra))
            return false;


        return this.timeStamp.equals(e.timeStamp);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(action,actor,extra,timeStamp,db_name,table_name,id_record);
    }

    public String toString()
    {
        return original_value;
    }
}
