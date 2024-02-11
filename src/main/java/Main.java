
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Miguel Calha, Diogo Soares
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome do ficheiro que pretende executar (coloque nome do ficheiro + .txt): \n");
        String ficheiro = sc.nextLine();
        System.out.println("Insira quantas threads pretende executar o ficheiro: \n:");
        int threads = sc.nextInt();
        System.out.println("Insira o número de segundos que pretende executar o algoritmo: \n");
        int segundos = sc.nextInt();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\dcalha\\Desktop\\Projeto SO 2\\" + ficheiro)); //Mudar de PC para PC 
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        int compPecas[] = {3, 4, 5, 6};
        int qttPecas[] = {2, 2, 1, 3};
        int m = 0;
        int i = 0;
        int maxComprimento = 12;
        Map<Integer, Integer> map;
        CuttingStockProblem cuttingStock = new CuttingStockProblem(maxComprimento, compPecas, qttPecas);
        while (cuttingStock.hasMoreCombinations()) {
            System.out.println("\nProblem =  " + ficheiro + " \tNumber of Threads = " + threads + " \tTime = " + segundos);
            // System.out.println("\n Combination no " + (i++));
            map = cuttingStock.nextCombination();
            for (Entry<Integer, Integer> entry : map.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key + "  *  " + value);
            }
        }

        for (int k = threads; k < threads; k++) {
            MultiThreading mThread = new MultiThreading(k);
            mThread.start();
        }
    }

}
