//package services;
//
//import java.io.*;
//import java.util.*;
//
//public class FileService {
//
//    String path = "/home/gabrielwenchenck/Dev/javinha/curso_nelio/17.trab_arquivos/exercicio_fixacao/src/file/file.csv";
//    String outputPath = "/home/gabrielwenchenck/Dev/javinha/curso_nelio/17.trab_arquivos/exercicio_fixacao/out/summary.csv";
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
//    BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
//
//        String line = bufferedReader.readLine();
//        while (line != null) {
//            String[] parts = line.split(",");
//            String name = parts[0];
//            double price = Double.parseDouble(parts[1]);
//            int quantity = Integer.parseInt(parts[2]);
//            double total = price * quantity;
//
//            writer.write(name + "," + total);
//            writer.newLine();
//
//            line = bufferedReader.readLine();
//        }
//    } catch (IOException e) {
//        System.out.println("Error: " + e.getMessage());
//    }
//}
