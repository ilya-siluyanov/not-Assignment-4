package numbers.ilya_siluyanov;

import numbers.do_not_change.Calculator;
import numbers.do_not_change.NumberCreator;

public class NumberOperations {
    public static void main(String[] args) {
        while (true) {
            NumberCreator creator = new MyNumberCreator();
            Calculator calculator = new MyCalculator(creator.insertNumbers(creator.validateAndSetNumberQuantity()));
            System.out.println(calculator);

            System.out.println(calculator.summarize());
            System.out.println(calculator);
            System.out.println();

            System.out.println(calculator.multiply());
            System.out.println(calculator);
            System.out.println();

            calculator.getSquareRoot();
            System.out.println(calculator);
            System.out.println();

            calculator.deleteNegativeNumbers();
            System.out.println(calculator);
            System.out.println();



            double a = Math.random() * 10;
            calculator.divideBy(a);
            System.out.print(a + "\t");
            System.out.println(calculator);
            System.out.println();

            try{
                Thread.sleep(1000);
            }catch (Exception e){}
        }
    }
}
