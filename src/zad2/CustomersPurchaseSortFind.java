/**
 *
 *  @author Batorski Bartłomiej PD2654
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomersPurchaseSortFind {
    List<Purchase> orders = new ArrayList<Purchase>();

    public void readFile(String fname) {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(fname));
            String line;
            while ((line = inputStream.readLine()) != null ){
                Purchase order = new Purchase(line);
                orders.add(order);
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Blad wczytania danych");
        }
    }

    public void showSortedBy(String sortBy) {
        if("Nazwiska".equals(sortBy)) {
            System.out.println(sortBy);
            Comparator<Purchase> comparator = Comparator.comparing(purchase -> purchase.getLastName());
            comparator = comparator.thenComparing(Comparator.comparing(purchase -> purchase.getId()));
            Stream<Purchase> sorted = orders.stream().sorted(comparator);
            print(sorted);
            System.out.println();
        }
        else if("Koszty".equals(sortBy)) {
            System.out.println(sortBy);
            Comparator<Purchase> comparator = Comparator.comparing(purchase -> purchase.getCost());
            comparator = comparator.reversed().thenComparing(Comparator.comparing(purchase -> purchase.getId()));
            Stream<Purchase> sorted = orders.stream().sorted(comparator);
            printWithCost(sorted);
            System.out.println();
        } else {
            System.out.println("Błędny wybór sortowania");
        }
    }

    private void printWithCost(Stream<Purchase> sorted) {
        sorted.forEach(s -> System.out.println(s.getLineWithCost()));
    }

    private void print(Stream<Purchase> sorted) {
        sorted.forEach(s -> System.out.println(s.getLine()));

    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        List<Purchase> filteredOrders = orders.stream().filter(purchase -> purchase.getId().equals(id)).collect(Collectors.toList());
        print(filteredOrders.stream());
        System.out.println();
    }
}
