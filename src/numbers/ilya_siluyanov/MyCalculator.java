package numbers.ilya_siluyanov;

import numbers.do_not_change.Calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class MyCalculator extends Calculator {
    /**
     * This is the constructor of a Calculator
     *
     * @param numbers the list of numbers
     */
    public MyCalculator(List<Number> numbers) {
        super(numbers);
    }


    /**
     * calculate the sum of all numbers from numbers list
     * @return product of all numbers from numbers list
     */
    @Override
    public double summarize() {
        double sum = 0.0;
        for (Number number : this.getNumbers()) {
            sum += number.doubleValue();
        }
        return sum;
    } //completed

    /**
     * calculate the product of all numbers from numbers list
     * @return product of all numbers from numbers list
     */
    @Override
    public double multiply() {
        double product = 1.0;
        for (Number number : this.getNumbers()) {
            product *= number.doubleValue();
        }
        return product;
    } //completed

    /**
     * Deletes all numbers which are less than zero from numbers list
     */
    @Override
    public void deleteNegativeNumbers() {
        List<Number> numbers = this.getNumbers();
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i).doubleValue() < 0) { //TODO: what about the precision?
                numbers.remove(i);
            }
        }
    } //completed

    /**
     * Divides each element of numbers list into the divisor with saving the element type
     * If element A is integer, then the result is x, x*divisor<=A
     * @param divisor the divisor
     */
    @Override
    public void divideBy(double divisor) {
        if (divisor==0.0){
            System.out.println("ArithmeticException: division by zero");
            return;
        }
        List<Number> numbers = this.getNumbers();
        String strRepresentOfDivisor = Double.toString(divisor);
        for (int i = 0; i < numbers.size(); i++) {
            Number currentNumber = numbers.get(i);
            if (currentNumber instanceof BigInteger) {
                BigDecimal currBigInt = new BigDecimal(((BigInteger)currentNumber));
                BigDecimal divisorBigInt = new BigDecimal(strRepresentOfDivisor);
                numbers.set(i, currBigInt.divide(divisorBigInt,RoundingMode.HALF_DOWN).toBigInteger());
            } else if (currentNumber instanceof BigDecimal) {
                BigDecimal currBigDec = (BigDecimal) currentNumber;
                BigDecimal divisorBigDec = new BigDecimal(strRepresentOfDivisor);
                numbers.set(i, currBigDec.divide(divisorBigDec, RoundingMode.HALF_UP));
            } else if (currentNumber instanceof Double) {
                numbers.set(i, currentNumber.doubleValue() / divisor);
            } else if (currentNumber instanceof Float) {
                numbers.set(i, (float) (currentNumber.floatValue() / divisor));
            } else if (currentNumber instanceof Byte) {
                numbers.set(i, (byte) (currentNumber.byteValue() / divisor));
            } else if (currentNumber instanceof Integer) {
                numbers.set(i, (int) (currentNumber.intValue() / divisor));
            } else if (currentNumber instanceof Long) {
                numbers.set(i, (long) (currentNumber.longValue() / divisor));
            } else if (currentNumber instanceof Short) {
                numbers.set(i, (short) (currentNumber.shortValue() / divisor));
            }
        }
    } //completed

    /**
     * Substitute each element in numbers list into its square root with saving the type of the element
     * If the number A is integer, than the result is  the biggest integer value x, x*x<=A
     */
    @Override
    public void getSquareRoot() {
        try {
            ArithmeticException negativeValueException = new ArithmeticException("negative value");
            for (int i = 0; i < this.getNumbers().size(); i++) {
                Number currentNumber = this.getNumbers().get(i);
                if (currentNumber instanceof BigInteger) { // we must store BigInteger instance
                    BigInteger current = (BigInteger) currentNumber;
                    if (current.compareTo(BigInteger.ZERO) < 0) { // could be removed because current.sqrt() throws ArithmeticException if value < 0 since java 9 (exactly)
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, BigInteger.valueOf((long)Math.sqrt(current.doubleValue())));
                } else if (currentNumber instanceof BigDecimal) { // we must store BigDecimal instance
                    BigDecimal current = (BigDecimal) currentNumber;
                    if (current.compareTo(BigDecimal.ZERO) < 0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, BigDecimal.valueOf(Math.sqrt(current.doubleValue())));
                } else if (currentNumber instanceof Double) {
                    if (currentNumber.doubleValue() < 0.0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, Math.sqrt(currentNumber.doubleValue()));
                } else if (currentNumber instanceof Float) {
                    if (currentNumber.floatValue() < 0.0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, (float) Math.sqrt(currentNumber.doubleValue()));
                } else if (currentNumber instanceof Byte) {
                    if (currentNumber.byteValue() < 0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, (byte) Math.sqrt(currentNumber.doubleValue()));
                } else if (currentNumber instanceof Integer) {
                    if (currentNumber.intValue() < 0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, (int) Math.sqrt(currentNumber.doubleValue()));
                } else if (currentNumber instanceof Long) {
                    if (currentNumber.longValue() < 0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, (long) Math.sqrt(currentNumber.doubleValue()));
                } else if (currentNumber instanceof Short) {
                    if (currentNumber.shortValue() < 0) {
                        throw negativeValueException;
                    }
                    this.getNumbers().set(i, (short) Math.sqrt(currentNumber.doubleValue()));
                }
            }
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException because of " + e.getMessage());
        }
    } //completed


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0;i < this.getNumbers().size();i++){
            Number x = this.getNumbers().get(i);
            sb.append(x.getClass().getSimpleName()).append(" : ").append(x.toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
