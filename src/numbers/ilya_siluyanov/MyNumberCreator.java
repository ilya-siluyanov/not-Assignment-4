package numbers.ilya_siluyanov;

import numbers.do_not_change.NumberCreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyNumberCreator extends NumberCreator {
    Scanner scanner = new Scanner(System.in);
    Random randomGenerator = new Random();

    /**
     * This method allows to choose the size of list
     * with numbers
     *
     * @return the quantity of numbers in the list
     */
    @Override
    public int validateAndSetNumberQuantity() {
        System.out.printf("Input the number of numbers. Bounds are [%d;%d]:",
                MIN_NUMBER_QUANTITY, MAX_NUMBER_QUANTITY);
        return (int) readInteger(MIN_NUMBER_QUANTITY, MAX_NUMBER_QUANTITY);
    }  //completed

    /**
     * This method allows generating the list of numbers
     * of different types chose randomly. In case chose type
     * and user input type are not the same,
     * an exception throws and being handled
     *
     * @param numberQuantity the quantity of numbers in the list
     * @return the list of generated numbers
     */
    @Override
    public List<Number> generateNumbers(int numberQuantity) {
        List<Number> result = new ArrayList<>();
        for (int i = 0; i < numberQuantity; i++) {
            Number iterationResult = null;
            int randomDoesHavePoint = randomGenerator.nextInt(2);
            if (randomDoesHavePoint == 1) {//it must be floating-point number
                switch (randomGenerator.nextInt(3)+1) {
                    case 1: //BigDecimal
                        iterationResult = BigDecimal.valueOf(generateDouble());
                        break;
                    case 2: //double
                        iterationResult = generateDouble();
                        break;
                    case 3: //float
                        iterationResult = (float) generateDouble();
                        break;
                }
            } else {
                switch (randomGenerator.nextInt(5)+1) {
                    case 1: //BigInteger
                        iterationResult = BigInteger.valueOf(generateInteger());
                        break;
                    case 2: //byte
                        iterationResult = (byte) generateInteger();
                        break;
                    case 3: //short
                        iterationResult = (short) generateInteger();
                        break;
                    case 4: //int
                        iterationResult = (int) generateInteger();
                        break;
                    case 5: //long
                        iterationResult = generateInteger();
                        break;
                }
            }
            result.add(iterationResult);
        }
        return result;
    } //completed

    /**
     * This method allows to insert numbers into the list
     * of randomly chosen types. The user has to print the
     * values via console. The incorrectly printed data has
     * to be handled
     *
     * @return the list of numbers inserted by user
     */
    @Override
    public List<Number> insertNumbers(int numberQuantity) {
        List<Number> numbers = new ArrayList<>();
        System.out.printf("Input %d numbers, one number at the row, from the interval [-100;100]\n", numberQuantity);
        Random randomGenerator = new Random();
        for (int i = 0; i < numberQuantity; i++) {
            Number iterationResult = null;
            int randomDoesHavePoint = randomGenerator.nextInt(2);
            if (randomDoesHavePoint == 1) { //it must be floating-point number
                System.out.print("Input floating-point number:");
                double input = readDouble();
                switch (randomGenerator.nextInt(3)+1) {
                    case 1: //BigDecimal
                        iterationResult = new BigDecimal(Double.toString(input));
                        break;
                    case 2: //double
                        iterationResult = input;
                        break;
                    case 3: //float
                        iterationResult = (float) input;
                        break;
                }
            } else {
                System.out.print("Input integer:");
                long input = readInteger(NUMBER_LOWER_BOUNDARY, NUMBER_UPPER_BOUNDARY);
                switch (randomGenerator.nextInt(5)+1) {
                    case 1: //BigInteger
                        iterationResult = BigInteger.valueOf(input);
                        break;
                    case 2: //byte
                        iterationResult = (byte) input;
                        break;
                    case 3: //short
                        iterationResult = (short) input;
                        break;
                    case 4: //int
                        iterationResult = (int) input;
                        break;
                    case 5: //long
                        iterationResult = input;
                        break;

                }
            }
            numbers.add(iterationResult);
        }
        return numbers;
    } //completed


    /**
     * Read a string from console until both it can not be interpreted as integer and
     * it is out of bounds [lowerBound;upperBound]
     *
     * @param lowerBound - minimal value the method can return
     * @param upperBound - maximum value the method can return
     * @return integer value read from console which is instance of primitive
     **/
    private long readInteger(int lowerBound, int upperBound) {
        try {
            String x = this.scanner.nextLine();
            //if(x.contains("."))throw new NumberFormatException(); TODO: check whether it is sufficient
            long result = Long.parseLong(x);
            if (isInsideOfBounds(result, lowerBound, upperBound))
                return result;
            else {
                System.out.println("value is out of bounds. Try again");
                return readInteger(lowerBound, upperBound);
            }
        } catch (NumberFormatException e) {
            System.out.println("Not an integer. Please, try again");
            return readInteger(lowerBound, upperBound);
        }
    }

    /**
     * Read a string from console until both it can not be interpreted as floating-point number and
     * it is out of bounds specified in NumberCreator class
     *
     * @return floating-point number read from console which is instance of primitive
     **/
    private double readDouble() {
        try {
            String x = this.scanner.nextLine();
            double result = Double.parseDouble(x);
            if (isInsideOfBounds(result, NUMBER_LOWER_BOUNDARY, NUMBER_UPPER_BOUNDARY))
                return result;
            else {
                System.out.println("value is out of bounds. Try again");
                return readDouble();
            }
        } catch (NumberFormatException e) {
            System.out.println("Not an floating-point number. Please, try again");
            return readDouble();
        }
    }

    /**
     * @param x          - input value
     * @param lowerBound - minimum value of considering interval
     * @param upperBound - maximum value of considering interval
     * @return whether x belongs to [lowerBound;upperBound]
     */
    private boolean isInsideOfBounds(Number x, int lowerBound, int upperBound) {
        return lowerBound <= x.doubleValue() && x.doubleValue() <= upperBound;
    }


    /**
     * Generates integer value within bounds specified by NumberCreator class
     *
     * @return generated integer
     */
    private long generateInteger() {
        return randomGenerator.nextInt(NUMBER_UPPER_BOUNDARY + 1 - NUMBER_LOWER_BOUNDARY) + NUMBER_LOWER_BOUNDARY;
    }

    /**
     * Generates floating-point number  within bounds specified by NumberCreator class
     *
     * @return generated floating-point number
     */
    private double generateDouble() {
        return randomGenerator.nextDouble() * (NUMBER_UPPER_BOUNDARY + 1 - NUMBER_LOWER_BOUNDARY) + NUMBER_LOWER_BOUNDARY;
    }

}
