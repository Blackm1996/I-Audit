import java.io.Serializable;
import java.util.ArrayList;

public class PatternSequence implements Serializable
{
    String type;
    String name;
    ArrayList<Condition> conditions;
    public PatternSequence(){}
}
