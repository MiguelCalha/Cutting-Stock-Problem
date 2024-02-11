import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.sqrt;

/* Miguel Calha, Diogo Soares */

public class Individuo {
    private ArrayList<Integer> individuo;
    private ArrayList<Integer> desperdicios;
    private ArrayList<Integer> pecas;
    private ArrayList<Integer> posCorte;
    private double custoIndiv;
    private int desperdicio;
    private int desperdicioDois;
    private int desp;
    private int soma;
    private int maxComprimento;
    private ArrayList fileValues;
    private int n, m, j, d;
    private int[] compPecas;
    private int[] qttPecas;
    private Random random;
    private int threads;
    private int segundos;
    private String ficheiro;

    /**
     * Inicializar as varáveis
     */
    public Individuo() {
        individuo = new ArrayList();
        pecas = new ArrayList();
        desperdicios = new ArrayList();
        posCorte = new ArrayList<>();
        custoIndiv = 0;
        desperdicio = 0;
        desperdicioDois = 0;
        desp = 0;
        soma = 0;
        maxComprimento = 0;
        fileValues = new ArrayList();
        random = new Random();
        n = 0;
        m = 0;
        j = 0;
        d = 0;
        compPecas = new int[]{};
        qttPecas = new int[]{};
        random = new Random();
        threads = 0;
        segundos = 0;
        ficheiro = "";
    }

    /**
     * Método que lê ficheiro .txt com o problema e guarda os valores nas variáveis
     */
    public void readFile(String ficheiro, int threads, int segundos) {

        this.ficheiro = ficheiro;
        this.threads = threads;
        this.segundos = segundos;

        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\dcalha\\Desktop\\ProjetoSO2\\files\\" + ficheiro)); //Mudar de PC para PC
            while ((line = reader.readLine()) != null) {
                fileValues.add(line);

            }
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public ArrayList shuffleInput(ArrayList<Integer> compPecas, ArrayList<Integer> qttdPecas) {
        int currentSize = 0;

        for (Integer i : qttdPecas) {
            int j = 0;
            while (j < i) {
                pecas.add(compPecas.get(currentSize));
                j++;
            }
            currentSize++;
        }
        System.out.println(pecas);
        Collections.shuffle(pecas);
        System.out.println(pecas);
        return pecas;
    }

    /**
     * Método para multiplicar tamanhos
     */
    public static List<Integer> multiplicarTamanhos(List<Integer> sizes, List<Integer> quantities) {
        List<Integer> multipliedList = new ArrayList<>();
        int currSize = 0;

        for (Integer quantity : quantities) {
            int i = 0;
            while (i < quantity) {
                multipliedList.add(sizes.get(currSize));
                i++;
            }
            currSize++;
        }
        return multipliedList;
    }


    /**
     * Método para efetuar o corte das placas
     */
    public void cortesPlacas() {
        ArrayList<Integer> cortes = new ArrayList<>();
        int posicao = 0;
        ArrayList<Double> probabilidade = new ArrayList<>();
        double desperdicioTotal = 0;

        for(int i = 0; i < pecas.size(); i++){
            if((soma + pecas.get(i)) <= maxComprimento){
                soma += pecas.get(i);
                posicao++;
                continue;
            }
            cortes.add(posicao);
            posicao++;
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Cortes");
        System.out.println(cortes);

        for(int i = 0; i <desperdicios.size(); i++){
            double numero = desperdicios.get(i);
            if (numero > 0) {
                desperdicioTotal += Math.sqrt(1/numero);
            }
            System.out.println("-------------------------------------------------");
            System.out.println("Desperdício Total");
            System.out.println(new DecimalFormat("#.####").format(desperdicioTotal));

            for(int j=0; i<desperdicios.size(); j++){
                double num = desperdicios.get(j);
                if(num > 0 && desperdicioTotal > 0){
                    probabilidade.add(((Math.sqrt(1/num))/desperdicioTotal));
                }
                System.out.println("-------------------------------------------------");
                System.out.println("Probabilidade");
                Collections.sort(probabilidade);
                System.out.println(probabilidade);

                double a = Math.random();

                if(a <= probabilidade.get(0)){
                    double aux = probabilidade.get(0);
                    int itemPos = probabilidade.indexOf(aux);

                }
            }
        }

    }

    /**
     * Método calcular o custo
     */
    public void custo(int maxComprimento, int placasCortadas) {

        double placasCortadasD = placasCortadas;
        custoIndiv = (1 / (1 + placasCortadasD)) * ((desperdicio + desperdicioDois));
        System.out.println(new DecimalFormat("#.####").format(custoIndiv));
    }


    /**
     * Método calcular o  desperdício
     */

    public void desperdicio(){

        for (int i=0; i <pecas.size(); i++) {
            if ((soma + pecas.get(i)) <= maxComprimento) {
                soma += pecas.get(i);
                continue;
            }
            desp -= soma;
            desperdicios.add(desp);
            posCorte.add(i - 1);
            soma = 0;
            desp = 14;
            soma += pecas.get(i);;
        }
        System.out.println(desperdicios);
        System.out.println(posCorte);

        }


    /**
     * Método que calcula a raiz do custo
     */
    public void raizCusto() {
        for (int i = 0; i < desperdicios.size(); i++) {
            int valor = (int) desperdicios.get(i); //CHECK THIS --> MIGHT CAUSE TROUBLE
            double maxCompDouble = maxComprimento;
            double conta = valor / maxCompDouble;
            desperdicio += sqrt(conta);
        }
        System.out.println(desperdicio);
    }

public void somatorioPlcas (int plcasCortadas){
        for(int i=0; i <desperdicios.size(); i++){
            if(desperdicios.get(i) > 1){
                double placasCortadasDouble = plcasCortadas;
                //  maisUmDesperdicio += ( 1/ placasCortadasDouble); --> Decalarar variáveis
            }
        }
}
    public void mostrarResultados() {

        System.out.println("\nProblem =  " + ficheiro + " \tNumber of Threads = " + threads + " \tTime = " + segundos);
        System.out.println("\nParams{m= " + ficheiro + "}" + " \tmaxLenght= " + threads + " \tlenghts[ = " + segundos + "]" + "\t limits[" + segundos + "]");
        System.out.println(m);
        System.out.println(maxComprimento);
        System.out.printf(Arrays.toString(compPecas));
        System.out.printf(Arrays.toString(qttPecas));
    }
}

