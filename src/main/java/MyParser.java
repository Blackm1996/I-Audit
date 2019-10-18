
import antlr.*;

import java.util.HashMap;
import java.util.Map;
import org.apache.flink.cep.pattern.conditions.*;

public class MyParser extends gramBaseVisitor<Object>
{

    private Map<String, Object> instance_variables;
    private Map<String, Object> Old_variables;
    private IterativeCondition.Context<Action_Entry> context;

    public MyParser(Map<String, Object> instance_variables,IterativeCondition.Context<Action_Entry> context) {
        this.instance_variables = instance_variables;
        this.context=context;
        Old_variables=new HashMap<>();
    }
    @Override
    public Object visitParse(gramParser.ParseContext ctx) {
        if(ctx.for_all()!=null)
            return super.visit(ctx.for_all());
        if(ctx.accept()!=null)
        {
            return super.visit(ctx.accept());
        }

        return super.visit(ctx.reject());
    }

    @Override
    public Object visitFor_all(gramParser.For_allContext ctx) {
        Iterable<Action_Entry> iterable = null;
        try {
            iterable=context.getEventsForPattern(ctx.sequence.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ctx.sa!=null)
        {
            if((Boolean)super.visit(ctx.sa))
                return true;
        }
        else if(ctx.sr!=null)
        {
            if(!(Boolean)super.visit(ctx.sr))
                return false;
        }

        if (ctx.acall!=null)
        {
            for (Action_Entry e:iterable)
            {
                Old_variables.put("timeStamp",e.getTimeStamp());
                Old_variables.put("actor",e.getActor());
                Old_variables.put("action",e.getAction());
                Old_variables.put("db_name",e.getDb_name());
                Old_variables.put("table_name",e.getTable_name());
                Old_variables.put("id_record",e.getId_record());
                Old_variables.put("extra",e.getExtra());
                if(!(Boolean)super.visit(ctx.acall))
                    return false;
            }
            return true;
        }
        if (ctx.reall!=null)
        {
            for (Action_Entry e:iterable)
            {
                Old_variables.put("timeStamp",e.getTimeStamp());
                Old_variables.put("actor",e.getActor());
                Old_variables.put("action",e.getAction());
                Old_variables.put("db_name",e.getDb_name());
                Old_variables.put("table_name",e.getTable_name());
                Old_variables.put("id_record",e.getId_record());
                Old_variables.put("extra",e.getExtra());
                if((Boolean)super.visit(ctx.reall))
                    return true;
            }

            return false;
        }

        throw new RuntimeException("Bad syntax " );
    }

    @Override
    public Object visitAccept(gramParser.AcceptContext ctx) {
       return super.visit(ctx.expression());
    }

    @Override
    public Object visitReject(gramParser.RejectContext ctx) {
        return !(Boolean)super.visit(ctx.expression());
    }

    @Override
    public Object visitAccept_all_but(gramParser.Accept_all_butContext ctx) {
        return !(Boolean)super.visit(ctx.expression());    }

    @Override
    public Object visitReject_all_but(gramParser.Reject_all_butContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Object visitBinaryExpression(gramParser.BinaryExpressionContext ctx) {
        if (ctx.op.AND() != null) {
            return asBoolean(ctx.left) && asBoolean(ctx.right);
        }
        else if (ctx.op.OR() != null) {
            return asBoolean(ctx.left) || asBoolean(ctx.right);
        }
        throw new RuntimeException("not implemented: binary operator " + ctx.op.getText());
    }

    @Override
    public Object visitDecimalExpression(gramParser.DecimalExpressionContext ctx) {
        return Double.valueOf(ctx.DECIMAL().getText());
    }

    @Override
    public Object visitStringExpression(gramParser.StringExpressionContext ctx)
    {
        return String.valueOf(ctx.STRING());
    }

    @Override
    public Object visitBoolExpression(gramParser.BoolExpressionContext ctx) {
        return Boolean.valueOf(ctx.getText());
    }


    @Override
    public Object visitIdentifierExpression(gramParser.IdentifierExpressionContext ctx)
    {
        if(ctx.old!=null)
            return Old_variables.get(ctx.IDENTIFIER().getText());
        if (ctx.neww!=null)
            return instance_variables.get(ctx.IDENTIFIER().getText());

        throw new RuntimeException("Bad Identifier");
    }

    @Override
    public Object visitNotExpression(gramParser.NotExpressionContext ctx) {
        return !((Boolean)this.visit(ctx.expression()));
    }

    @Override
    public Object visitParenExpression(gramParser.ParenExpressionContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Object visitComparatorExpression(gramParser.ComparatorExpressionContext ctx) {
        if (ctx.op.EQ() != null) {
            return this.visit(ctx.left).equals(this.visit(ctx.right));
        }
        else if (ctx.op.DIF()!=null) {
            return !this.visit(ctx.left).equals(this.visit(ctx.right));
        }
        else if (ctx.op.LE() != null) {
            return asDouble(ctx.left) <= asDouble(ctx.right);
        }
        else if (ctx.op.GE() != null) {
            return asDouble(ctx.left) >= asDouble(ctx.right);
        }
        else if (ctx.op.LT() != null) {
            return asDouble(ctx.left) < asDouble(ctx.right);
        }
        else if (ctx.op.GT() != null) {
            return asDouble(ctx.left) > asDouble(ctx.right);
        }
        throw new RuntimeException("not implemented: comparator operator " + ctx.op.getText());
    }

    private boolean asBoolean(gramParser.ExpressionContext ctx) {
        return (boolean)visit(ctx);
    }

    private double asDouble(gramParser.ExpressionContext ctx) {
        return (double)visit(ctx);
    }
}
