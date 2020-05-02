package src;


public class Args {

    private Expression left, right;
    Args(Expression x, Expression y){
        left = x;

        right =y;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
