import java.util.*;

public class ExpressionBuilder {

    public ExpressionBuilder() {
    }

    public String stringSanitizer(String inString) {
        String[] strArray = inString.split("=");

        if (strArray.length > 2) {
            return null;
        } else if (strArray.length == 2) {
            return strArray[1];
        }

        return inString;
    }

    /**
     * @param variable
     * @param arrayVariables - holds (A, B, X)
     * @return the index of the variable (A, B, X) in the array.
     */
    public int indexForVariable(String variable, ArrayList<String> arrayVariables) {
        if (!arrayVariables.contains(variable)) {
            arrayVariables.add(variable);
        }

        return arrayVariables.indexOf(variable);
    }

    public Expression scanner(String str, ArrayList<String> arrayVariables) {

        boolean lastVar = false;

        String expressionString = stringSanitizer(str);

        Stack<Expression> expressionStack = new Stack<Expression>();
        Stack<String> operatorStack = new Stack<String>();


        for (int i = 0; i < expressionString.length(); i++) {
            switch (expressionString.charAt(i)) {
                case '*':
                    Expression ex = expressionStack.pop();

                    Complement comp = new Complement(ex);

                    expressionStack.push(comp);
                    lastVar = false;
                    break;

                case '+':

                    if (!operatorStack.empty()) {

                        if (operatorStack.peek().equals("+")) {
                            operatorStack.pop();

                            Expression rightExpr = expressionStack.pop();
                            Expression leftExpr = expressionStack.pop();


                            OrExpression orExpr = new OrExpression(leftExpr, rightExpr);
                            expressionStack.push(orExpr);


                        }
                    }

                    operatorStack.push("+");
                    lastVar = false;
                    break;


                case '(':
                    operatorStack.push("(");
                    lastVar = false;
                    break;

                case ')':

                    while (true) {
                        lastVar = false;

                        String strOperator = operatorStack.pop();

                        if (strOperator.equals("+")) {
                            Expression rightExpr = expressionStack.pop();
                            Expression leftExpr = expressionStack.pop();


                            OrExpression orExpr = new OrExpression(leftExpr, rightExpr);
                            expressionStack.push(orExpr);

                        } else if (strOperator.equals("(")) {
                            break;
                        } else {
                            break;
                        }

                    }//while

                    break;

                case ' ':
                    break;

                case '\n':
                    break;

                default:

                    String s1 = Character.toString(expressionString.charAt(i));

                    Variable newVariable = new Variable(indexForVariable(s1, arrayVariables));

                    if (lastVar == true) {
                        Expression leftExpr = expressionStack.pop();


                        And andExpr = new And(leftExpr, newVariable);
                        expressionStack.push(andExpr);
                    } else {
                        expressionStack.push(newVariable);

                    }

                    lastVar = true;
            }

        }//for


        if (!operatorStack.empty()) {
            Expression rightExpr = expressionStack.pop();
            Expression leftExpr = expressionStack.pop();


            OrExpression orExpr = new OrExpression(leftExpr, rightExpr);
            expressionStack.push(orExpr);
        }

        return expressionStack.pop();
    }

}//class

