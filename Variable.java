
public class Variable extends Expression {

    int offset;

    public Variable(int anOffset) {
        this.offset = anOffset;
    }

    public boolean evaluate(boolean[] arr) {
        return arr[offset];
    }
}
