package src;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap; // import the HashMap class

public class Memory {

    private static volatile Memory instance = null;
    // make separate hashmaps for each data type?
    HashMap<String, Object> ValueTable = new HashMap<String, Object>();
    HashMap<String, String> SymbolTable = new HashMap<String, String>();

    // TODO
    // Combine valuetable and symbolTable
    // HashMap<String, Hashmap<String, Object>> SymbolTable = new HashMap<String, Integer>();

    private Memory(){}

    public static Memory getInstance(){
        if(instance == null){
            synchronized(Memory.class){
                if(instance == null){
                    instance = new Memory();
                }
            }
        }
        return instance;
    }

    public void createVariable(String varName){
        ValueTable.put(varName, null);
    }

    public void assignInteger(String varName, Integer value){
        ValueTable.put(varName, value);
        //switch case or if to check for type from params
    }

    public void assignFloat(String varName, Float value){
        ValueTable.put(varName, value);
        //switch case or if to check for type from params
    }

    public void assignBoolean(String varName, Boolean value){
        ValueTable.put(varName, value);
        //switch case or if to check for type from params
    }

    public void assignString(String varName, String value){
        ValueTable.put(varName, value);
        //switch case or if to check for type from params
    }

    public void memory_dump(){
        System.out.println("Value Table: " + ValueTable);
        System.out.println("Symbol Table: " + SymbolTable);
    }
}