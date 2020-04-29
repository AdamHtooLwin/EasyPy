/*
 * A utility class to compare types. Created for convenience.
 * Type object of integer type or boolean type can be returned.
 * This class is used in CUP.
 */
package src;

import java.util.HashMap;

public class Type {

    public static final int INTEGER = 1;
    public static final int BOOLEAN = 2;
    public static final int FLOAT = 3;
    public static final int STRING = 4;

    int tag;
    String code;

    public Type(int t, String c) {
        tag = t;
        code = c;
    }

    public int getTag() {
        return tag;
    }

    public String getCode() {
        return code;
    }

}
