package src;

public class Expression {
    private Integer INTEGER_LITERAL;
    private Float FLOAT_LITERAL;
    private String CHAR_LITERAL;

    private Boolean BOOLEAN_LITERAL;
    private String ID;

    private Type type;

    private int Operator;

    public Expression(){

    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setOperator(int operator) {
        Operator = operator;
    }

    public void setFLOAT_LITERAL(Float FLOAT_LITERAL) {
        this.FLOAT_LITERAL = FLOAT_LITERAL;
    }

    public void setCHAR_LITERAL(String CHAR_LITERAL) {
        this.CHAR_LITERAL = CHAR_LITERAL;
    }

    public void setINTEGER_LITERAL(Integer INTEGER_LITERAL) {
        this.INTEGER_LITERAL = INTEGER_LITERAL;
    }

    public void setBOOLEAN_LITERAL(Boolean BOOLEAN_LITERAL) {
        this.BOOLEAN_LITERAL = BOOLEAN_LITERAL;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getINTEGER_LITERAL() {
        return INTEGER_LITERAL;
    }

    public Float getFLOAT_LITERAL() {
        return FLOAT_LITERAL;
    }

    public String getCHAR_LITERAL() {
        return CHAR_LITERAL;
    }

    public Boolean getBOOLEAN_LITERAL() {
        return BOOLEAN_LITERAL;
    }

    public String getID() {
        return ID;
    }

    public int getOperator() {
        return Operator;
    }
}
