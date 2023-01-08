package com.shahian;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ParseException {
        List<Product> products = Arrays.asList(
                new Product("pen", 10, 40000, new SimpleDateFormat("dd-MM-yyyy").parse("04-06-2019"), LocalDate.of(2019, Month.DECEMBER, 04)),
                new Product("pencil", 10, 50000, new SimpleDateFormat("dd-MM-yyyy").parse("21-01-2019"), LocalDate.of(2019, Month.NOVEMBER, 04)),
                new Product("eraser", 10, 60000, new SimpleDateFormat("dd-MM-yyyy").parse("15-12-2019"), LocalDate.of(2019, Month.MARCH, 04)),
                new Product("marker", 10, 10000, new SimpleDateFormat("dd-MM-yyyy").parse("26-07-2019"), LocalDate.of(2019, Month.MAY, 04)),
                new Product("bag", 10, 120000, new SimpleDateFormat("dd-MM-yyyy").parse("03-09-2019"), LocalDate.of(2019, Month.JANUARY, 04)),
                new Product("noteBook", 10, 350000, new SimpleDateFormat("dd-MM-yyyy").parse("14-11-2019"), LocalDate.of(2019, Month.APRIL, 04)),
                new Product("pencil case", 10, 1500, new SimpleDateFormat("dd-MM-yyyy").parse("29-02-2019"), LocalDate.of(2019, Month.AUGUST, 04))

        );
        //beforJava8(products);
        //sortingWithLambdaExpression(products);
        //sortingWithMethodReference(products);
        //sortingWithMultipleAttributeUsingMethodReference(products);
        //sortNaturallyUsingStream(products);
        //sortingByNameUsingStream(products);
        //sortingByNameAndPrice(products);
        //sortingByDateAscending(products);
        //sortingByDateDescending(products);
        //sortingDateBYMethodReferencesAscending(products);
        //sortingDateBYMethodReferencesDscending(products);
        //sortingDateBYStreamApiAscending(products);
        //sortingDateBYStreamApiDscending(products);
        sortingByLocalDateAscending(products);
    }

    private static void sortingByLocalDateAscending(List<Product> products) {
    }

    private static void sortingDateBYStreamApiDscending(List<Product> products) {
        products.stream()
                .sorted(Comparator.comparing(Product::getDate).reversed())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void sortingDateBYStreamApiAscending(List<Product> products) {
        products.stream()
                .sorted(Comparator.comparing(Product::getDate))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void sortingDateBYMethodReferencesAscending(List<Product> products) {
        products.sort(Comparator.comparing(product -> product.getDate()));
        products.forEach(System.out::println);
    }

    private static void sortingDateBYMethodReferencesDscending(List<Product> products) {
        products.sort(Comparator.comparing(Product::getDate).reversed());
        products.forEach(System.out::println);
    }

    private static void sortingByDateAscending(List<Product> products) {
        Comparator<Product> comparator = (p1, p2) -> {
            return Long.valueOf(p1.getDate().getTime()).compareTo(p2.getDate().getTime());
        };
        Collections.sort(products, comparator);
        products.forEach(System.out::println);
    }

    private static void sortingByDateDescending(List<Product> products) {
        Comparator<Product> reverseComparator = (p1, p2) -> {
            return Long.valueOf(p2.getDate().getTime()).compareTo(p1.getDate().getTime());
        };
        Collections.sort(products, reverseComparator);
        products.forEach(System.out::println);
    }

    private static void sortingByNameAndPrice(List<Product> products) {
        Comparator<Product> sortByName = (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName());
        Comparator<Product> sortByPrice = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());

        products.stream()
                .sorted(sortByName.thenComparing(sortByPrice))
                .forEach(System.out::println);
    }

    private static void sortingByNameUsingStream(List<Product> products) {
        products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
        products.forEach(System.out::println);
    }

    private static void sortNaturallyUsingStream(List<Product> products) {
        /*
        We have two overloaded methods of the sorted() API:
        sorted() – sorts the elements based on natural ordering; the element class must implement the Comparable interface.
        sorted(Comparator<? super T> comparator) – sorts the elements based on a Comparator instance.
        we need to pass a comparator instance, as a parameter.
         */
        List<String> alphabets = Arrays.asList("E", "A", "G", "P", "L");

        List<String> sortedAlphabets = alphabets.stream().sorted().collect(Collectors.toList());
        List<String> reversSortedAlphabets = alphabets.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sortedAlphabets.forEach(item -> System.out.println(item));
        System.out.println("******");
        reversSortedAlphabets.forEach(item -> System.out.println(item));
    }

    private static void sortingWithMultipleAttributeUsingMethodReference(List<Product> products) {
        products.sort(Comparator.comparing(Product::getName).thenComparing(Product::getPrice));
        products.forEach(System.out::println);
    }

    //sort the List in Java 8 using Method Reference
    //using an instance method reference and the Comparator.comparing method,
    private static void sortingWithMethodReference(List<Product> products) {
        Collections.sort(products, Comparator.comparing(Product::getName));
        products.forEach(System.out::println);

    }

    private static void sortingWithLambdaExpression(List<Product> products) {
        products.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        products.forEach(System.out::println);
    }

    private static void beforJava8(List<Product> products) {
        //When we have custom objects to sort then we have to provide the comparator implementation
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
