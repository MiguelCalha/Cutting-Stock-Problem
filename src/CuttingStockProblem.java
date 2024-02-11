import java.util.ArrayList;
import java.util.Scanner;

/* Miguel Calha, Diogo Soares */
public class CuttingStockProblem {


    public static void main(String[] args) {
        String ficheiro = " ";
        int threads = 0;
        int segundos = 0;
        int maxComrpimento = 0;
        int placasCortadas = 0;
        //InputFicheiro file = new InputFicheiro();
        Individuo individuo = new Individuo();
        Scanner sc = new Scanner(System.in);


        System.out.println("Insira o nome do ficheiro que pretende executar (coloque nome do ficheiro + .txt): \n");
        ficheiro = sc.nextLine();
        System.out.println("Insira quantas threads pretende executar o ficheiro: \n:");
        threads = sc.nextInt();
        System.out.println("Insira o número de segundos que pretende executar o algoritmo: \n");
        segundos = sc.nextInt();
        individuo.readFile(ficheiro, threads, segundos);
        individuo.cortesPlacas();
        individuo.custo(maxComrpimento, placasCortadas);
        individuo.desperdicio();
        individuo.mostrarResultados();
        individuo.raizCusto();
        individuo.somatorioPlcas(placasCortadas);


    }
}
