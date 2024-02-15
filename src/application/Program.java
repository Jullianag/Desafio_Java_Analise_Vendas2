package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        System.out.println();
        System.out.println("Total de vendas por vendedor:");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
                        Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
                line = br.readLine();
            }


            Map<String, Double> vendedor = list.stream()
                            .collect(Collectors.groupingBy(s -> s.getSeller(),
                                    Collectors.summingDouble(s -> s.getTotal())));


            for (String s : vendedor.keySet()) {
                System.out.println(s + " - " + String.format("R$ %.2f", vendedor.get(s)));
            }



        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());

        }
        sc.close();
    }
}
