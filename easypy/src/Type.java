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
    public static final int ERRORTYPE = 3;
    public static final int FLOAT = 4;
    public static final int STRING = 5;

    static HashMap<Integer, Type> types = new HashMap<Integer, Type>();
    int tag;
    String code;

    public Type() {
        initTypes();
    }

    public Type(int t, String c) {
        tag = t;
        code = c;
    }

    public String getCode() {
        return code;
    }

    public static void initTypes() {
        types.put(INTEGER , new Type(INTEGER, "int"));
        types.put(BOOLEAN, new Type(BOOLEAN, "bool"));
        types.put(FLOAT, new Type(FLOAT, "float"));
        types.put(STRING, new Type(STRING, "char"));
        types.put(ERRORTYPE, new Type(ERRORTYPE, "error"));
    }

    public static Type string() {
        return (Type) types.get(STRING);
    }

    public boolean isString() {
        if (tag == STRING) {
            return true;
        } else {
            return false;
        }
    }

    public static Type integer() {
        return (Type) types.get(INTEGER);
    }

    public boolean isInteger() {
        if (tag == INTEGER) {
            return true;
        } else {
            return false;
        }
    }

    public static Type floating_point() {
        return (Type) types.get(FLOAT);
    }

    public boolean isFloating_point() {
        if (tag == FLOAT) {
            return true;
        } else {
            return false;
        }
    }

    public static Type bool() {
        return (Type) types.get(BOOLEAN);
    }

    public boolean isBool() {
        if (tag == BOOLEAN) {
            return true;
        } else {
            return false;
        }
    }

    public static Type errortype() {
        return (Type) types.get(ERRORTYPE);
    }
}
