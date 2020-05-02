package src;

public class Statement
{
    Memory m = Memory.getInstance();
    src.parser parser = new parser();
    String statementType;
    String ID;
    Type type;
    Expression e;
    Statement body;
    Statement elsebody;
    StatementList statementList;
    int calculatorResult;
    float calculatorFloatResult;
    String ID2;

    //for logical
    Expression left,right;
    

    public Statement(Type t, String ID, Expression e)
    {
        this.ID = ID;
        this.e = e;
        this.type = t;
    }
    
    public Statement(Type t, String ID)
    {
        this.ID = ID;
        this.e = null;
        this.type = t;
    }

    public Statement(Integer i)
    {
        this.calculatorResult = i;
    }
     public Statement(Float i)
    {
        this.calculatorFloatResult = i;
    }
     
    
    public Statement(String ID, Expression e)
    {
        this.ID = ID;
        this.e = e;
    }
    
    public Statement(String ID1, String ID2 )
    {
        this.ID = ID1;
        this.ID2 = ID2;
    }

    public Statement(Expression e, Statement body)
    {
        this.e = e;
        this.body = body;
    }
    
    public Statement(Expression e, Statement body1, Statement body2) {
        this.e = e;

        this.body = body1;
        this.elsebody = body2;

    }

    public Statement(Expression e)
    {
        this.e = e;
    }

    private Statement(StatementList l)
    {
        statementList = l;
    }

    
    public static Statement logic (Expression logical)
    {
        Statement logic = new Statement(logical);
        logic.statementType="logic";

        return logic;
    }
    
    public static Statement assignment(String ID, Expression e)
    {
        Statement assignment = new Statement(ID, e);

        assignment.statementType = "assignment";

        return assignment;

    }

    public static Statement assignment(Type t, String ID, Expression e)
    {
        Statement assignment = new Statement(t, ID, e);
        assignment.statementType = "instantiation";

        return assignment;

    }

    public static Statement assignment(Type t, String ID)
    {
        Statement assignment = new Statement(t, ID);
        assignment.statementType = "declaration";

        return assignment;

    }

    public static Statement whileloop(Expression e, Statement whileBody)
    {
        Statement loop = new Statement(e, whileBody);
        loop.statementType = "whileloop";
        return loop;

    }

    public static Statement ifthen(Expression e, Statement ifbody)
    {
        Statement ifthen = new Statement(e, ifbody);
        ifthen.statementType = "ifthen";
        return ifthen;
    }
    
    public static Statement ifthenelse(Expression e, Statement ifbody, Statement elsebody) {

        Statement ifthenelse = new Statement(e, ifbody, elsebody);
        ifthenelse.statementType = "ifthenelse";
        return ifthenelse;
        
    }

    public static Statement print(Expression e)
    {

        Statement p = new Statement(e);
        p.statementType = "print";
        return p;

    }

    public static Statement list(StatementList l)
    {

        Statement p = new Statement(l);
        p.statementType = "list";
        return p;

    }

    public String getstat() {
        System.out.println(statementType);

        if (statementType.equals("assignment")) {
            return ID + "=" + e.getexp();
        } else if (statementType.equals("intantiation")) {
            return type.getCode() + " " + ID + "=" + e.getexp();
        } else if (statementType.equals("ifthen")) {
            return "if " + e.getexp() + " " + body.getstat();
        } else if (statementType.equals("ifthenelse")) {
            return "else " + e.getexp() + " " + body.getstat();
        } else if (statementType.equals("print")) {
            return "print " + e.getexp();
        } else if (statementType.equals("whileloop")) {
            return "while " + e.getexp() + " do " + body.getstat();
        } else if (statementType.equals("list")) {
            return "list";
        } else if (statementType.equals("function-return")) {
            return "function-return";
        } else if (statementType.equals("until_st")) {
            return "until_st";
        } else if (statementType.equals("type-error")) {
            return "type-error";
        } else {
            return "unknown";
        }



    }

    public void execute() {
        /*
         * Retreive identifier from table, check its type, then assign
         */
        if (statementType.equals("assignment")) {
            if (m.SymbolTable.get(ID).isInteger()) {
                m.ValueTable.put(ID, e.getEntity().getValue());
            }
            if (m.SymbolTable.get(ID).isString()) {
                m.ValueTable.put(ID, e.getEntity().getValue());
            }
            if (m.SymbolTable.get(ID).isFloating_point()) {
                m.ValueTable.put(ID, e.getEntity().getValue());
            }
            if (m.SymbolTable.get(ID).isBool()) {
                m.ValueTable.put(ID, e.getEntity().getValue());
            }
        } else if (statementType.equals("ifthen")) {
            if (e.getEntity().getType().isBool()) //expr must be boolean
            {
                boolean b = (Boolean) e.getEntity().getValue();
                if ((Boolean) e.getEntity().getValue()) {
                    body.execute();
                }
            } else {
                parser.type_error("", "if expression must be boolean.");
            }
        } else if (statementType.equals("ifthenelse")) {
            if (e.getEntity().getType().isBool()) //expr must be boolean
            {
                if ((Boolean) e.getEntity().getValue()) {
                    body.execute();
                } else {
                    elsebody.execute();
                }
            } else {
                parser.type_error("", "if expression must be boolean.");
            }
        } else if (statementType.equals("whileloop")) {
            if (e.getEntity().getType().isBool()) {
                while (true) {
                    if ((Boolean) e.getEntity().getValue()) {
                        body.execute();
                    } else {
                        break;
                    }
                }
            } else {
                //type error
                parser.type_error("", "while expression must be boolean.");
            }
        } else if (statementType.equals("print")) {
            //need to check type for casting from Entity
            if (e.getType().isInteger()) {
                System.out.println((Integer) e.getEntity().getValue());
            }
            if (e.getType().isFloating_point()) {
                System.out.println((Float) e.getEntity().getValue());
            }
            if (e.getType().isString()) {
                System.out.println((String) e.getEntity().getValue());
            }
            if (e.getType().isBool()) {
                System.out.println((Boolean) e.getEntity().getValue());
            }

        } else if (statementType.equals("list")) {
            for (Statement s : statementList.statementLists) {
                s.execute();
            }
        } else if (statementType.equals("calculator")) {
            if (type.isInteger()) {
                System.out.println(calculatorResult);
            }
            if (type.isFloating_point()) {
                System.out.println(calculatorFloatResult);
            }

        }

    }
}
