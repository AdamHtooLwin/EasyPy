package src;
import java.util.HashMap; // import the HashMap class

public class Memory {
    private static volatile Memory instance = null;
    //    {"x":15,"y":Null}
    HashMap<String, Integer> ValueTable = new HashMap<String, Integer>();
    HashMap<String, Integer> SymbolTable = new HashMap<String, Integer>();

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

    public void assignValueToVariable(String varName, Integer value){
        ValueTable.put(varName, value);
    }

    public Integer getValueFromVariable(String varName){
        return ValueTable.get(varName);
    }

    public void memory_dump(){
        System.out.println("Value Table: " + ValueTable);
        System.out.println("Symbol Table: " + SymbolTable);
    }
}