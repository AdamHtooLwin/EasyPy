/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;

public class ParseTree {
    public static final int assignment = 1;
    public static final int declaration = 2;
    public static final int expression = 3;
    public static final int if_else = 4;
    public static final int while_loop = 5;

    private ArrayList<ParseTree> nodes = new ArrayList<>();
    private String token;
    private double lexeme;
    private int level;
    private String type;
    public ParseTree(){
        
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public ArrayList<ParseTree> getNodes(){
        return nodes;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setLexeme(double lexeme){
        this.lexeme = lexeme;
    }
    public void setNodes(ArrayList<ParseTree> nodes){
        this.nodes = nodes;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public String getToken(){
        return token;
    }
    public double getLexeme(){
        return lexeme;
    }
    public int getLevel(){
        return level;
    }
}
