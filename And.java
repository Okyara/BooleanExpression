
public class And extends Expression {

    Expression leftOperand;
    Expression rightOperand;

    public And(Expression leftEx, Expression rightEx) {
        this.leftOperand = leftEx;
        this.rightOperand = rightEx;

    }

    public boolean evaluate(boolean[] array) {
        boolean leftValue = leftOperand.evaluate(array);
        boolean rightValue = rightOperand.evaluate(array);

        return leftValue && rightValue;
    }

}
