
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Entry
{
    private String original_value;
    protected LocalDateTime timeStamp;
    protected String actor;
    protected String db_name;

    public Entry(){}


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



    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj==null)
            return false;
        if(! (obj instanceof Entry))
            return false;
        Entry e=(Entry) obj;
        if(!(this.actor.equals(e.actor)))
            return false;
        if (!this.db_name.equals(e.db_name))
            return false;

        return this.timeStamp.equals(e.timeStamp);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(actor,timeStamp,db_name);
    }

    public String toString()
    {
        return original_value;
    }
}