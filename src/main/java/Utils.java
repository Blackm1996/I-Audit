import antlr.gramLexer;
import antlr.gramParser;
import bsh.EvalError;
import bsh.Interpreter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils
{
    public static ArrayList<PatternSpecification> getPatterns()
    {
        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<NewPattern>>(){}.getType();
        JsonReader reader=null;
        try {
            reader= new JsonReader(new FileReader(Utils.getSettings("Patterns_Path")));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        ArrayList<NewPattern> allPaterns = gson.fromJson(reader, listType);
        ArrayList<PatternSpecification> patterns=new ArrayList<>();


        for (NewPattern newPattern:allPaterns)
        {
            PatternSpecification a=new PatternSpecification();
            PatternSequence begin=newPattern.sequences.get(0);
            a.Name=begin.name;
            a.Result_name=newPattern.sequences.get(newPattern.sequences.size()-1).name;
            a.pattern=Pattern.<Action_Entry> begin(begin.name,AfterMatchSkipStrategy.skipPastLastEvent());
            for (Condition condition:begin.conditions)
            {
                switch (condition.type)
                {
                    case ("where"):
                    {
                        try {

                            Method method=Pattern.class.getDeclaredMethod(condition.type, IterativeCondition.class);
                            a.pattern=(Pattern)method.invoke(a.pattern, new IterativeCondition<Action_Entry>() {

                                @Override
                                public boolean filter(Action_Entry e, Context<Action_Entry> context)
                                {
                                    HashMap<String,Object> variables=new HashMap<>();
                                    variables.put("timeStamp",e.getTimeStamp());
                                    variables.put("actor",e.getActor());
                                    variables.put("action",e.getAction());
                                    variables.put("db_name",e.getDb_name());
                                    variables.put("table_name",e.getTable_name());
                                    variables.put("id_record",e.getId_record());
                                    variables.put("extra",e.getExtra());

                                    gramLexer lexer = new gramLexer(CharStreams.fromString(condition.expression));
                                    gramParser parser = new gramParser(new CommonTokenStream(lexer));
                                    Object result = new MyParser(variables,context).visit(parser.parse());
                                    return (Boolean)result;

                                }
                            });
                        } catch (NoSuchMethodException| InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case ("oneOrMore"):
                    {
                        try {

                            Method method = Pattern.class.getMethod(condition.type);
                            a.pattern=(Pattern)method.invoke(a.pattern);

                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case ("times"):
                    case ("timesOrMore"):
                    {
                        try {

                            Method method = Pattern.class.getDeclaredMethod(condition.type,int.class);
                            a.pattern=(Pattern)method.invoke(a.pattern,Integer.valueOf(condition.time));

                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            for (PatternSequence sequence:newPattern.sequences)
            {
                if (sequence.type.equals("begin"))
                    continue;
                try {
                    Method method = Pattern.class.getDeclaredMethod(sequence.type,String.class);
                    a.pattern=(Pattern)method.invoke(a.pattern,sequence.name);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                for (Condition condition:sequence.conditions)
                {
                    switch (condition.type)
                    {
                        case ("where"):
                        {
                            try {
                                Method method=Pattern.class.getDeclaredMethod(condition.type, IterativeCondition.class);
                                a.pattern=(Pattern)method.invoke(a.pattern, new IterativeCondition<Action_Entry>() {

                                    @Override
                                    public boolean filter(Action_Entry e, Context<Action_Entry> context)
                                    {
                                        HashMap<String,Object> variables=new HashMap<>();
                                        variables.put("timeStamp",e.getTimeStamp());
                                        variables.put("actor",e.getActor());
                                        variables.put("action",e.getAction());
                                        variables.put("db_name",e.getDb_name());
                                        variables.put("table_name",e.getTable_name());
                                        variables.put("id_record",e.getId_record());
                                        variables.put("extra",e.getExtra());

                                        gramLexer lexer = new gramLexer(CharStreams.fromString(condition.expression));
                                        gramParser parser = new gramParser(new CommonTokenStream(lexer));
                                        Object result = new MyParser(variables,context).visit(parser.parse());
                                        return (Boolean)result;
                                    }
                                });
                            } catch (NoSuchMethodException| InvocationTargetException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case ("oneOrMore"):
                        {
                            try {
                                Method method = Pattern.class.getDeclaredMethod(condition.type);
                                a.pattern=(Pattern)method.invoke(a.pattern);
                            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case ("times"):
                        case ("timesOrMore"):
                        {
                            try {
                                Method method = Pattern.class.getDeclaredMethod(condition.type,int.class);
                                a.pattern=(Pattern<Action_Entry,?>)method.invoke(a.pattern,Integer.valueOf(condition.time));
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case ("within"):
                        {
                            try {
                                Method method = Pattern.class.getDeclaredMethod(condition.type, Time.class);
                                Method method1=Time.class.getDeclaredMethod(condition.unit,long.class);
                                Time time=(Time)method1.invoke(null,Long.valueOf(condition.time));
                                a.pattern=(Pattern)method.invoke(a.pattern,time);
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
            patterns.add(a);
        }
        return patterns;
    }

    public static String getSettings(String setting) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("src\\main\\resources\\settings.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName(setting);
        Node n=nList.item(0);
        return n.getTextContent();
    }
}
