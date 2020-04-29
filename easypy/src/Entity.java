package src;

public class Entity {
    private final Object value;
    private final Type type;

    public Entity(Object input_value, Type input_type){
        value = input_value;
        type = input_type;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
}
