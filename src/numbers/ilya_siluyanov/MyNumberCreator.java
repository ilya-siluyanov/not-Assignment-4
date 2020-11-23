package numbers.ilya_siluyanov;

import numbers.do_not_change.NumberCreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyNumberCreator extends NumberCreator {
    /**
     * This method allows to choose the size of list
     * with numbers
     *
     * @return the quantity of numbers in the list
     */
    @Override
    public int validateAndSetNumberQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of numbers: ");
//        int numberOfNumbers = scanner.nextInt();
        int numberOfNumbers = (int)(Math.random()*10);
        numberOfNumbers = Math.max(MIN_NUMBER_QUANTITY, numberOfNumbers);
        numberOfNumbers = Math.min(MAX_NUMBER_QUANTITY, numberOfNumbers);
        return numberOfNumbers;
    }  //completed

    /**
     * This method allows generating the list of numbers
     * of different types
     *
     * @param numberQuantity the quantity of numbers in the list
     * @return the list of generated numbers
     */
    @Override
    public List<Number> generateNumbers(int numberQuantity) {
        Random randomGenerator = new Random();
        List<Number> result = new ArrayList<>();
        for (int i = 0; i < numberQuantity; i++) {
            Number newNumber = null;
            long randomLong = randomGenerator.longs(1, NUMBER_LOWER_BOUNDARY, NUMBER_UPPER_BOUNDARY + 1).toArray()[0];
            double randomDouble = NUMBER_UPPER_BOUNDARY + 1;
            while (randomDouble > NUMBER_UPPER_BOUNDARY || randomDouble < NUMBER_LOWER_BOUNDARY) {
                randomDouble = randomGenerator.doubles(1, NUMBER_LOWER_BOUNDARY, NUMBER_UPPER_BOUNDARY + 1).toArray()[0];
            }
            newNumber = valueHandling(randomLong, randomDouble);
            result.add(newNumber);
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
        String DELIMETER = ".";//TODO: check whether we use this char for double and float
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Input %d numbers, one number at the row\n", numberQuantity);

        for (int i = 0; i < numberQuantity; i++) {
            try {
                String nextNumber = scanner.next();
                long integer;
                double floatingPoint;
                if (nextNumber.contains(DELIMETER)) {
                    floatingPoint = Double.parseDouble(nextNumber);
                    integer = (long) floatingPoint;
                } else {
                    integer = Long.parseLong(nextNumber);
                    floatingPoint = (double) integer;
                }
                numbers.add(valueHandling(integer, floatingPoint));
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
                System.out.println();
                i--;
            }
        }

        return numbers;
    } //completed

    private Number valueHandling(long integerValue, double floatingPointValue) {
        Random randomGenerator = new Random();
        Number result = null;
        switch (randomGenerator.nextInt(8)) {
            case 0:
                result = BigInteger.valueOf(integerValue);
                break;
            case 1:
                result = BigDecimal.valueOf(floatingPointValue);
                break;
            case 2:
                result = floatingPointValue;
                break;
            case 3:
                result = (float) floatingPointValue;
                break;
            case 4:
                result = (byte) integerValue;
                break;
            case 5:
                result = (int) integerValue;
                break;
            case 6:
                result = integerValue;
                break;
            case 7:
                result = (short) integerValue;
                break;
        }
        return result;
    } //completed
}
