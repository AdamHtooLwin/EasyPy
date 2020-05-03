package src;

public class Statement
{
    Memory m = Memory.getInstance();

    // for errors
    src.parser parser = new parser();

    String statementType;
    String ID;
    Type type;
    Expression e;
    StatementList body;
    StatementList elsebody;
    StatementList whilebody;
    StatementList statementList;
    

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
    
    public Statement(String ID, Expression e)
    {
        this.ID = ID;
        this.e = e;
    }

    // while and if (revised)
    public Statement(Expression e, StatementList body)
    {
        this.e = e;
        this.body = body;
    }

    // if else
    public Statement(Expression e, StatementList body1, StatementList body2) {
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

    public static Statement whileloop(Expression e, StatementList whileBody)
    {
        Statement loop = new Statement(e, whileBody);
        loop.statementType = "whileloop";
        return loop;

    }

    public static Statement ifthen(Expression e, StatementList ifbody)
    {
        Statement ifthen = new Statement(e, ifbody);
        ifthen.statementType = "ifthen";
        return ifthen;
    }
    
    public static Statement ifthenelse(Expression e, StatementList ifbody, StatementList elsebody) {

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

//    public String getstat() {
////        System.out.println(statementType);
//
//        switch (statementType) {
//            case "assignment":
//                return ID + "=" + e.getexp();
//            case "intantiation":
//                return type.getCode() + " " + ID + "=" + e.getexp();
//            case "ifthen":
//                return "if " + e.getexp() + " " + body.getstat();
//            case "ifthenelse":
//                return "if " + e.getexp() + " " + body.getstat() + " else " + e.getexp() + " " + elsebody.getstat();
//            case "print":
//                return "publish (\"" + e.getexp() + "\")";
//            case "whileloop":
//                return "while (" + e.getexp() + ") { " + body.getstat() + " } ";
//            case "list":
//                return "list";
//            case "function-return":
//                return "function-return";
//            case "until_st":
//                return "until_st";
//            case "type-error":
//                return "type-error";
//            default:
//                return "unknown";
//        }
//
//    }
//
    public String getPrefix(javax.swing.JTextArea jTextAreaPrefix) {
//        System.out.println(statementType);

        switch (statementType) {
            case "assignment":
                return "assign " + ID + " (" + e.getPrefix(jTextAreaPrefix) + ")";
            case "declaration":
                return ID + " : " + e.getPrefix(jTextAreaPrefix);
            case "ifthen":
                return "if (" + e.getPrefix(jTextAreaPrefix) + ") {" + body.getPrefix(true, jTextAreaPrefix).toString() + "}";
            case "ifthenelse":
                return "if (" + e.getPrefix(jTextAreaPrefix) + ") {" + body.getPrefix(true,jTextAreaPrefix).toString() + "} else {" + elsebody.getPrefix(true, jTextAreaPrefix) + "}";
            case "print":
                return "publish(" + e.getPrefix(jTextAreaPrefix) + ")";
            case "whileloop":
                return "while (" + e.getPrefix(jTextAreaPrefix) + ") { " + body.getPrefix(true, jTextAreaPrefix).toString() + " } ";
            case "type-error":
                return "type-error";
            default:
                return "unknown";
        }

    }

    public void execute(javax.swing.JTextArea jTextAreaOutput, javax.swing.JTextArea jTextAreaPrefix) {
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
            if (e.getEntity().getType().isBool() && e.getEntity() != null) //expr must be boolean
            {
                boolean b = (Boolean) e.getEntity().getValue();
                if ((Boolean) e.getEntity().getValue()) {
                    body.execute(jTextAreaOutput, jTextAreaPrefix);
                }
            } else {
                parser.type_error("", "if expression must be boolean.");
            }
        } else if (statementType.equals("ifthenelse")) {
            if (e.getEntity().getType().isBool()) //expr must be boolean
            {
                if ((Boolean) e.getEntity().getValue()) {
                    body.execute(jTextAreaOutput, jTextAreaPrefix);
                } else {
                    elsebody.execute(jTextAreaOutput, jTextAreaPrefix);
                }
            } else {
                parser.type_error("", "if expression must be boolean.");
            }
        } else if (statementType.equals("whileloop")) {
            if (e.getEntity().getType().isBool()) {
                while (true) {
                    if ((Boolean) e.getEntity().getValue() && e.getEntity() != null) {
                        body.execute(jTextAreaOutput, jTextAreaPrefix);
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
                jTextAreaOutput.setText(jTextAreaOutput.getText() + e.getEntity().getValue() + "\n");
//                System.out.println((Integer) e.getEntity().getValue());
            }
            if (e.getType().isFloating_point()) {
                jTextAreaOutput.setText(jTextAreaOutput.getText() + (e.getEntity().getValue()) + "\n");

//                System.out.println((Float) e.getEntity().getValue());
            }
            if (e.getType().isString()) {
                jTextAreaOutput.setText(jTextAreaOutput.getText() + (e.getEntity().getValue()) + "\n");
//                System.out.println((String) e.getEntity().getValue());
            }
            if (e.getType().isBool()) {
                jTextAreaOutput.setText(jTextAreaOutput.getText() + (e.getEntity().getValue()) + "\n");

//                System.out.println((Boolean) e.getEntity().getValue());
            }

        }

    }
}
