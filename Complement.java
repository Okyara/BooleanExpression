
public class Complement extends Expression {

    Expression operand;

    public Complement(Expression ex) {
        this.operand = ex;
    }

    public boolean evaluate(boolean[] array) {
        boolean currentValue = operand.evaluate(array);
        return !currentValue;
    }
}
