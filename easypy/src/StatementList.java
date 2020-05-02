package src;

import java.util.ArrayList;


public class Lstat {

    ArrayList<Statement> statementLists;

    public Lstat(Statement s){
        statementLists  = new ArrayList<Statement>();
        statementLists.add(s);
    }

    public Lstat(Lstat l, Statement s){
        statementLists = l.statementLists;
        statementLists.add(s);
    }

    public void execute(){
        System.out.println("\n===========PROGRAM OUTPUT===========");

        for (Statement statement : statementLists) {
            statement.execute();
        }
    }

    public void showStatements()
    {
        for (int i = 0; i < statementLists.size(); i++)
        {
            System.out.println("****************");
            System.out.println(statementLists.get(i));
            System.out.println("****************");
            
        }
    }
}
