package src;

import java.io.FileReader;
import java.util.HashMap;

public class Main {
    static public void main(String[] argv) {
        try {
            Memory m = Memory.getInstance();
            Lexer l = new Lexer(new FileReader(argv[0]));
            parser p = new parser(l);
            p.parse();

            System.out.println("==========Compiler Memory Assets=========");
            m.memory_dump();
        } catch (Exception e) {
            /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }
    }
}


