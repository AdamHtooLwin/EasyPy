package src;

public class Astat
{
    Memory m = Memory.getInstance();
    src.parser parser = new parser();
    String statementType;
    String ID;
    Type type;
    Expression e;
    Astat body;
    Astat elsebody;
    Lstat statementList;
    int calculatorResult;
    float calculatorFloatResult;
    String ID2;

    //for logical
    Expression left,right;
    

    public Astat(Type t, String ID, Expression e)
    {
        this.ID = ID;
        this.e = e;
        this.type = t;
    }
    
    public Astat(Type t, String ID)
    {
        this.ID = ID;
        this.e = null;
        this.type = t;
    }

    public Astat (Integer i)
    {
        this.calculatorResult = i;
    }
     public Astat (Float i)
    {
        this.calculatorFloatResult = i;
    }
     
    
    public Astat(String ID, Expression e)
    {
        this.ID = ID;
        this.e = e;
    }
    
    public Astat(String ID1,String ID2 )
    {
        this.ID = ID1;
        this.ID2 = ID2;
    }

    public Astat(Expression e, Astat body)
    {
        this.e = e;
        this.body = body;
    }
    
    public Astat(Expression e, Astat body1, Astat body2) {
        this.e = e;

        this.body = body1;
        this.elsebody = body2;

    }

    public Astat(Expression e)
    {
        this.e = e;
    }

    private Astat(Lstat l)
    {
        statementList = l;
    }

    
    public static Astat logic (Expression logical)
    {
        Astat logic = new Astat(logical);
        logic.statementType="logic";

        return logic;
    }
    
    public static Astat assignment(String ID, Expression e)
    {
        Astat assignment = new Astat(ID, e);

        assignment.statementType = "assignment";

        return assignment;

    }

    public static Astat assignment(Type t, String ID, Expression e)
    {
        Astat assignment = new Astat(t, ID, e);
        assignment.statementType = "instantiation";

        return assignment;

    }

    public static Astat assignment(Type t, String ID)
    {
        Astat assignment = new Astat(t, ID);
        assignment.statementType = "declaration";

        return assignment;

    }

    public static Astat whileloop(Expression e, Astat whileBody)
    {
        Astat loop = new Astat(e, whileBody);
        loop.statementType = "whileloop";
        return loop;

    }
    
    public static Astat until_st(Expression e, Astat untilBody)
    {
        Astat loop = new Astat(e, untilBody);
        loop.statementType = "until_st";
        return loop;

    }

    public static Astat ifthen(Expression e, Astat ifbody)
    {
        Astat ifthen = new Astat(e, ifbody);
        ifthen.statementType = "ifthen";
        return ifthen;
    }
    
    public static Astat ifthenelse(Expression e, Astat ifbody, Astat elsebody) {

        Astat ifthenelse = new Astat(e, ifbody,elsebody);
        ifthenelse.statementType = "ifthenelse";
        return ifthenelse;
        
    }

    public static Astat print(Expression e)
    {

        Astat p = new Astat(e);
        p.statementType = "print";
        return p;

    }

    public static Astat list(Lstat l)
    {

        Astat p = new Astat(l);
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
                for (;;) {
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
        } else if (statementType.equals("until_st")) {
            if (e.getEntity().getType().isBool()) {
                for (;;) {
                    if (!(Boolean) e.getEntity().getValue()) {
                        body.execute();
                    } else {
                        break;
                    }
                }
            } else {
                //type error
                parser.type_error("", "Until expression must be boolean.");
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
            for (Astat s : statementList.statementLists) {
                s.execute();
            }
        } else if (statementType.equals("calculator")) {
            if (type.isInteger()) {
                System.out.println(calculatorResult);
            }
            if (type.isFloating_point()) {
                System.out.println(calculatorFloatResult);
            }

        } else if (statementType.equals("function")) {
            for (Astat s : statementList.statementLists) {
                s.execute();
            }
        }

    }
}
