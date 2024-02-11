import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.Reader;
import java.util.ArrayList;

/* Miguel Calha, Diogo Soares */

public class InputFicheiro {

    Scanner sc = new Scanner(System.in);
    private ArrayList list;
    private int n, m, j, d;
    private int[] compPecas;
    private int[] qttPecas;
    private ArrayList pecas;
    private Random random;


    public InputFicheiro() {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList();
        random = new Random();
        n = 0;
        m = 0;
        j = 0;
        d = 0;
        compPecas = new int[]{};
        qttPecas = new int[]{};
        pecas = new ArrayList<Integer>();
        random = new Random();
    }

    public void readFile(String ficheiro, int threads, int segundos) {

        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\dcalha\\Desktop\\ProjetoSO2\\files\\" + ficheiro)); //Mudar de PC para PC
            while ((line = reader.readLine()) != null) {
                list.add(line);

            }
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("\nProblem =  " + ficheiro + " \tNumber of Threads = " + threads + " \tTime = " + segundos);
        System.out.println("\nParams{m= " + ficheiro + "}" + " \tmaxLenght= " + threads + " \tlenghts[ = " + segundos + "]" + "\t limits[" + segundos + "]");
    }


}
