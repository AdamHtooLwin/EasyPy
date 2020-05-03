package src;
public class Expression
{
    Memory m = Memory.getInstance();
    private src.parser parser = new parser();
    private boolean[] tag = new boolean[6];
    private Integer NUMBER_INTEGER;
    private Float NUMBER_FLOAT;
    private String TEXT_LITERAL;
    private String ID;
    private Args Operands;
    private int Operator;
    private Boolean BOL;
    private String prefix;

    private Type type = new Type();
    //A variable to check if identifier hold boolean or not. If false, its integer.
    private boolean isBool;

    Expression(Integer x)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 0)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        NUMBER_INTEGER = x;
        type = Type.integer();
        isBool = false;
    }
    
    Expression(Float x)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 1)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        NUMBER_FLOAT = x;
        type = Type.floating_point();
        isBool = false;
    }

     
    Expression(String x)
    {
          int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 2)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        TEXT_LITERAL = x;
        type = Type.string();
        isBool = false;
    }
    
    Expression(String x, Boolean identifier)
    {
        
        int i;

        for (i = 0; i <= 5; i++)
        {
            if (i == 3)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        //System.out.println("IN X constructor");
        if (m.SymbolTable.get(x).isInteger())
        {
            type = Type.integer();
            isBool = false;
            //System.out.println("ID test(integer): "+x+" "+type.isInteger());
        } else if (m.SymbolTable.get(x).isFloating_point())
        {
            type = Type.floating_point();
            isBool = false;
        } else if (m.SymbolTable.get(x).isString())
        {
            type = Type.string();
            isBool = false;
        } 
        else if (m.SymbolTable.get(x).isBool())
        {
            //System.out.println(x+" is boolean "+ Env.envTable.get(x));
            type = Type.bool();
            isBool = true;
        } else
        {
          type = Type.errortype();
        }

        ID = x;
    }

    Expression(Args x, int op)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 4)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
       
        // set type here
        if (op == sym.PLUS || op == sym.MINUS || op == sym.TIMES || op == sym.DIVIDE || op == sym.MOD)
        {
            
             if (x.getRight().getType().isFloating_point()){
                type = Type.floating_point();
            } else if (x.getRight().getType().isInteger() ){
                type = Type.integer();
            } else if (x.getRight().getType().isString()){
                type = Type.string();
            }             
              
            isBool = false;
        } else if (op == sym.GT || op == sym.LT || op == sym.EQUAL || op == sym.NOTEQUAL || op == sym.GTE || op == sym.LTE)
        {
            type = Type.bool();
            isBool = true;
        }else if (op == sym.OR || op == sym.AND)
        {
            type = Type.bool();
            isBool = true;
        }
        else
        {
            type = Type.errortype();
        }

        Operands = x;
        Operator = op;
    }

    Expression(Boolean mutex)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 5)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }

        type = Type.bool();
        this.BOL = mutex;
        //System.out.println("Mutex "+BOL);
        isBool = true;
    }
    
   
    public String getexp()
    {

        String s = "";
        if (tag[0])
        {
            s = String.valueOf(NUMBER_INTEGER);
        } else if (tag[1])                
        {
            s = String.valueOf(NUMBER_FLOAT);
        } else if (tag[2])                
        {
            s = "\"" + TEXT_LITERAL + "\"";
        } else if (tag[3])
        {
            s = ID;
        } else if (tag[4])
        {
            if (Operator == sym.PLUS)
            {
                s = "PLUS(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            } else if (Operator == sym.MINUS)
            {
                s = "MINUS(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.TIMES)
            {
                s = "TIMES(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.DIVIDE)
            {
                s = "DIVIDE(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.MOD)
            {
                s = "MOD(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.EQUAL)
            {
                s = "EQUAL(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.NOTEQUAL)
            {
                s = "NOTEQUAL(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.GTE)
            {
                s = "GTE(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.LTE)
            {
                s = "LTE(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.LT)
            {
                s = "LT(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
            if (Operator == sym.GT)
            {
                s = "GT(" + Operands.getLeft().getexp() + "," + Operands.getRight().getexp() + ")";
            }
        } else if (tag[5])
        {
            s = String.valueOf(BOL);
        }
        return s;
    }

    public String getPrefix()
    {

        String s = "";
        if (tag[0])
        {
            s = String.valueOf(NUMBER_INTEGER);
        } else if (tag[1])
        {
            s = String.valueOf(NUMBER_FLOAT);
        } else if (tag[2])
        {
            s = "\"" + TEXT_LITERAL + "\"";
        } else if (tag[3])
        {
            s = ID;
        } else if (tag[4])
        {
            if (Operator == sym.PLUS)
            {
                s = "+ (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            } else if (Operator == sym.MINUS)
            {
                s = "- (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.TIMES)
            {
                s = "* (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.DIVIDE)
            {
                s = "/ (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.MOD)
            {
                s = "% (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.EQUAL)
            {
                s = "is (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.NOTEQUAL)
            {
                s = "is not (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.GTE)
            {
                s = ">= (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.LTE)
            {
                s = "<= (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.LT)
            {
                s = "< (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
            if (Operator == sym.GT)
            {
                s = "> (" + Operands.getLeft().getPrefix() + " " + Operands.getRight().getPrefix() + ")";
            }
        } else if (tag[5])
        {
            s = String.valueOf(BOL);
        }
        return s;
    }

    
    //Evaluation of expressions
    public Entity getEntity()
    {
        Entity typeVal = null;
        if (tag[0]) //number integer
        {
              typeVal = new Entity(NUMBER_INTEGER, Type.integer());
        }else if (tag[1]) //number float
        {
              typeVal = new Entity(NUMBER_FLOAT, Type.floating_point());
        }
        else if (tag[2]) //String
        {
              typeVal = new Entity(TEXT_LITERAL, Type.string());
        }
        else if (tag[3]) //identifier
        {
            if (isBool)
            {
                typeVal = new Entity(m.ValueTable.get(ID), m.SymbolTable.get(ID));
            }
            else
            {
                typeVal = new Entity(m.ValueTable.get(ID), m.SymbolTable.get(ID));
            }
        }
        else if (tag[4])    // operation on two expressions
        {

            /*
             * Typecasts will throw a exception from java itself, when there
             * is a type error in the language
             */

            if (Operator == sym.PLUS)
            {

                if ( Operands.getLeft().getType().isInteger() )
                {
                    int val = (Integer) Operands.getLeft().getEntity().getValue();
                    val = val + (Integer) Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.integer());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    float val = (Float) Operands.getLeft().getEntity().getValue();
                    val = val + (Float) Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.floating_point());
                }
                else if (Operands.getLeft().getType().isString() && Operands.getRight().getType().isString())
                {
                    String val = (String) Operands.getLeft().getEntity().getValue();
                    val = val + (String) Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.string());
                }
                else {
                    System.out.println("Invalid operation for types " + Operands.getLeft().getEntity().getType().code + " and " + Operands.getRight().getEntity().getType().code);
                    System.exit(1);
                }
            }
            else if (Operator == sym.MINUS)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                    int val = (Integer) Operands.getLeft().getEntity().getValue();
                    val = val - (Integer)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.integer());
                } else if ( Operands.getLeft().getType().isFloating_point()){
                    float val = (Float) Operands.getLeft().getEntity().getValue();
                    val = val - (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.floating_point());
                }

            }
            else if (Operator == sym.TIMES)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                    int val = (Integer) Operands.getLeft().getEntity().getValue();
                    val = val * (Integer)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.integer());
                } else if ( Operands.getLeft().getType().isFloating_point()){
                    float val = (Float) Operands.getLeft().getEntity().getValue();
                    val = val * (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.floating_point());
                }
            }
            else if (Operator == sym.DIVIDE)
            {


                if ( Operands.getLeft().getType().isInteger() )
                {
                    int val = (Integer) Operands.getLeft().getEntity().getValue();
                    val = val / (Integer)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.integer());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    float val = (Float) Operands.getLeft().getEntity().getValue();
                    val = val / (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.floating_point());
                }
            }
            else if (Operator == sym.MOD)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                    int val = (Integer) Operands.getLeft().getEntity().getValue();
                    val = val % (Integer)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.integer());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    float val = (Float) Operands.getLeft().getEntity().getValue();
                    val = val % (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.floating_point());
                }
            }
            else if (Operator == sym.GT)
            {

                 if ( Operands.getLeft().getType().isInteger() )
                {
                     boolean val = (Integer) Operands.getLeft().getEntity().getValue() > (Integer)Operands.getRight().getEntity().getValue();
                     typeVal = new Entity(val, Type.bool());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    boolean val = (Float) Operands.getLeft().getEntity().getValue() > (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.bool());
                }
            }
            else if (Operator == sym.LT)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                     boolean val = (Integer) Operands.getLeft().getEntity().getValue() < (Integer) Operands.getRight().getEntity().getValue();
                     typeVal = new Entity(val, Type.bool());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    boolean val = (Float) Operands.getLeft().getEntity().getValue() < (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.bool());
                }
            }
            else if (Operator == sym.GTE)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                     boolean val = (Integer) Operands.getLeft().getEntity().getValue() >= (Integer)Operands.getRight().getEntity().getValue();
                     typeVal = new Entity(val, Type.bool());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    boolean val = (Float) Operands.getLeft().getEntity().getValue() >= (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.bool());
                }
            }
            else if (Operator == sym.LTE)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                     boolean val = (Integer) Operands.getLeft().getEntity().getValue() <= (Integer)Operands.getRight().getEntity().getValue();
                     typeVal = new Entity(val, Type.bool());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    boolean val = (Float) Operands.getLeft().getEntity().getValue() <= (Float)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.bool());
                }
            }
            else if (Operator == sym.EQUAL)
            {
                if ( Operands.getLeft().getType().isInteger() )
                {
                     boolean val = ((Integer) Operands.getLeft().getEntity().getValue()).equals((Integer) Operands.getRight().getEntity().getValue());
                     typeVal = new Entity(val, Type.bool());

                } else if ( Operands.getLeft().getType().isFloating_point())
                {
                    boolean val = ((Float) Operands.getLeft().getEntity().getValue()).equals((Float) Operands.getRight().getEntity().getValue());
                    typeVal = new Entity(val, Type.bool());

                } else if ( Operands.getLeft().getType().isBool())
                {
                    boolean val = (Boolean) Operands.getLeft().getEntity().getValue().equals((Boolean)Operands.getRight().getEntity().getValue());
                    typeVal = new Entity(val, Type.bool());
                }
            }
            else if (Operator == sym.NOTEQUAL)
            {
                if (Operands.getLeft().getType().isInteger() )
                {
                     boolean val = !((Integer) Operands.getLeft().getEntity().getValue()).equals((Integer) Operands.getRight().getEntity().getValue());
                     typeVal = new Entity(val, Type.bool());

                } else if (Operands.getLeft().getType().isFloating_point())
                {
                    boolean val = !((Float) Operands.getLeft().getEntity().getValue()).equals((Float) Operands.getRight().getEntity().getValue());
                    typeVal = new Entity(val, Type.bool());

                }else if (Operands.getLeft().getType().isBool())
                {
                    boolean val = (Boolean) Operands.getLeft().getEntity().getValue() != (Boolean)Operands.getRight().getEntity().getValue();
                    typeVal = new Entity(val, Type.bool());
                }
            }
            else if (Operator == sym.OR)
            {
                boolean val = (Boolean) Operands.getLeft().getEntity().getValue() || (Boolean) Operands.getRight().getEntity().getValue();
                typeVal = new Entity(val, Type.bool());
            }
            else if (Operator == sym.AND)
            {
                boolean val = (Boolean) Operands.getLeft().getEntity().getValue() && (Boolean) Operands.getRight().getEntity().getValue();
                typeVal = new Entity(val, Type.bool());
            }
        }
        if (tag[5]) //for boolean expression (from constructor)
        {           
            typeVal = new Entity(BOL, Type.bool());
        }
        return typeVal;
    }
    
    public Type getType()
    {
        return type;
    }

}
