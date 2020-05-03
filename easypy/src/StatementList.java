package src;

import java.util.ArrayList;


public class StatementList {

    ArrayList<Statement> statementList;

    // initialize new list with 1 statement
    public StatementList(Statement s){
        statementList  = new ArrayList<Statement>();
        statementList.add(s);
    }

    // add 1 statement into existing list
    public StatementList(StatementList l, Statement s){
        statementList = l.statementList;
        statementList.add(s);
    }

    // concatenate two lists
    public StatementList(StatementList l, StatementList s){
        statementList = l.statementList;
        statementList.addAll(s.statementList);
    }

    // get prefix for inner while, if statements
    // syntactic sugar
    public StringBuilder getPrefix(boolean inner){
        StringBuilder program = new StringBuilder();

        for (Statement statement: statementList){
            program.append(statement.getPrefix());
        }
        return program;
    }

    // get prefix statements for all other statements
    public StringBuilder getPrefix(){
        StringBuilder program = new StringBuilder();

        for (Statement statement: statementList){
            program.append(statement.getPrefix()).append("\n");
        }
        return program;
    }

    // evaluate statements in list
    public void execute(){

        for (Statement statement : statementList) {
            statement.execute();
        }
    }
}
