package src;

import java.util.HashMap; // import the HashMap class
import java.util.Map;

public class Memory {

    private static volatile Memory instance = null;
    // make separate hashmaps for each data type?
    HashMap<String, Object> ValueTable = new HashMap<String, Object>();
    HashMap<String, Type> SymbolTable = new HashMap<String, Type>();

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

    public void memory_dump(){
        System.out.println("Value Table: " + ValueTable);
        System.out.println("Symbol Table: {");
        for (Map.Entry<String, Type> entry : SymbolTable.entrySet()) {
            System.out.println("\t" + entry.getKey() + ":" + entry.getValue().getCode());
        }
        System.out.print("}");
    }
}