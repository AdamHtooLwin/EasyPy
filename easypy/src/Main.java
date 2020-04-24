package src;

import java.io.FileReader;
import java.util.HashMap;

public class Main {
    static public void main(String[] argv) {
        try {
//            HashMap<String, String> SymbolTable = new HashMap<String, String>();
//            SymbolTable.put("x", "hello");
//            System.out.println(SymbolTable.get("a"));
//            int x = 8;
//            Object obj = x;
//            Object obj2 = (int) obj +  9;
//            Object obj3 = "hello";
//            String string = (String) obj3;
//            System.out.println(string);

            Memory m = Memory.getInstance();
            parser p = new parser(new Lexer(new FileReader(argv[0])));
            p.parse();
            m.memory_dump();
            //Object result = p.parse().value;
        } catch (Exception e) {
            /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }
    }
}


