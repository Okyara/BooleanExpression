
import java.util.*;

public class BooleanExpression {

    Scanner scanner = new Scanner(System.in);
    static ExpressionBuilder exprBuilder = new ExpressionBuilder();

    /**
     * @param numRow - the row we are looking at.
     * @param arraySize - the number of variables
     * @return
     */
    public static boolean[] rowMinterms(int theRowNum, int arraySize) {
        boolean[] boolResult = new boolean[arraySize];

        for (int i = 0; i < arraySize; i++) {
            boolResult[arraySize - i - 1] = (theRowNum & (1 << i)) > 0 ? true : false;
        }

        return boolResult;
    }

    public static void main(String[] args) {
        ArrayList<String> arrStr = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the first boolean expression: ");
        String exp1 = scanner.nextLine();

        System.out.println("Enter the second boolean expression: ");
        String exp2 = scanner.nextLine();

        Expression expression1 = exprBuilder.scanner(exp1, arrStr);

        Expression expression2 = exprBuilder.scanner(exp2, arrStr);

        int numMinterms = 1 << arrStr.size();    //2^size

        boolean[] minterms;

        /**
         * Extract the first row of the table
         */
        String strFunction1 = exprBuilder.stringSanitizer(exp1);
        String strFunction2 = exprBuilder.stringSanitizer(exp2);



        /**
         * Print out the first line table
         */
        for (int i = 0; i < arrStr.size(); i++) {
            System.out.print(arrStr.get(i) + "  ");

        }//for

        System.out.print(strFunction1 + "    " + strFunction2 + "\n");
        System.out.println("-------------------------------------------------");


        for (int i = 0; i < numMinterms; i++) {
            minterms = rowMinterms(i, arrStr.size());

            for (int j = 0; j < minterms.length; j++) {
                if (minterms[j] == true) {

                    System.out.print("T  ");
                } else {
                    System.out.print("F  ");
                }

            }//for

            boolean value1 = expression1.evaluate(minterms);
            boolean value2 = expression2.evaluate(minterms);

            System.out.print(value1 + "   " + value2 + "\n");

        }//for

    }//main

}//class

/**********************************OUTPUT***************************************
 *
 *  Enter the first boolean expression:
    (xy)+(yn)
    Enter the second boolean expression:
    (x+y+n)(xy)
    x  y  n  (xy)+(yn)    (x+y+n)(xy)
    -------------------------------------------------
    F  F  F  false   false
    F  F  T  false   false
    F  T  F  false   false
    F  T  T  true   false
    T  F  F  false   false
    T  F  T  false   false
    T  T  F  true   true
    T  T  T  true   true
 *
 *
 *  run:
    Enter the first boolean expression:
    !x(m+n)+y
    Enter the second boolean expression:
    x+y+m+n(xymn)
    !  x  m  n  y  !x(m+n)+y    x+y+m+n(xymn)
    -------------------------------------------------
    F  F  F  F  F  false   false
    F  F  F  F  T  true   false
    F  F  F  T  F  true   true
    F  F  F  T  T  true   true
    F  F  T  F  F  true   false
    F  F  T  F  T  true   false
    F  F  T  T  F  true   true
    F  F  T  T  T  true   true
    F  T  F  F  F  false   false
    F  T  F  F  T  true   false
    F  T  F  T  F  true   true
    F  T  F  T  T  true   true
    F  T  T  F  F  true   false
    F  T  T  F  T  true   false
    F  T  T  T  F  true   true
    F  T  T  T  T  true   true
    T  F  F  F  F  false   false
    T  F  F  F  T  true   false
    T  F  F  T  F  true   true
    T  F  F  T  T  true   true
    T  F  T  F  F  true   false
    T  F  T  F  T  true   false
    T  F  T  T  F  true   true
    T  F  T  T  T  true   true
    T  T  F  F  F  false   false
    T  T  F  F  T  true   false
    T  T  F  T  F  true   true
    T  T  F  T  T  true   true
    T  T  T  F  F  true   false
    T  T  T  F  T  true   false
    T  T  T  T  F  true   true
    T  T  T  T  T  true   true
    BUILD SUCCESSFUL (total time: 40 seconds)

 */

