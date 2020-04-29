package src;

import java.io.FileReader;
import java.util.HashMap;

public class Main {
    static public void main(String[] argv) {
        try {
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


