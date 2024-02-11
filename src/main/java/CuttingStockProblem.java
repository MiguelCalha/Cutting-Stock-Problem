
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author dcalha
 */
public class CuttingStockProblem {

    private int block[], qty[], comb[], tempcomb[], limit[];
    @SuppressWarnings("unused")
    private int max, total, counter = 0, waste = 0;
    private List<Map<Integer, Integer>> mapList = new ArrayList<Map<Integer, Integer>>();
    private List<Integer> store = new ArrayList<Integer>();
    private int count = 0;

    public boolean hasMoreCombinations() {
        if (count < counter) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized Map<Integer, Integer> nextCombination() {
        Map<Integer, Integer> map = mapList.get(count);
        count++;
        return map;
    }

    public CuttingStockProblem(int max, int block[], int quantity[]) throws InvalidLenghtException, InvalidParameterException {
        for (int i = 0; i < block.length; i++) {
            if (block[i] > max) {
                throw new InvalidLenghtException();
            }
        }
        if (block.length != quantity.length) {
            throw new InvalidLenghtException();
        }
        this.total = block.length;
        this.max = max;
        this.block = block;
        this.qty = quantity;
        this.doIt();
    }

    private void doIt() {
        this.initialize();
    }

    private void initialize() {
        store = new ArrayList<Integer>();
        waste = 0;
        counter = 0;
        this.sort();
        this.calculate(store);
    }

    private void sort() {
        int tmp;
        boolean swap;
        do {
            swap = false;
            for (int j = 0; j < total - 1; j++) {
                if (block[j + 1] > block[j]) {
                    tmp = block[j];
                    block[j] = block[j + 1];
                    block[j + 1] = tmp;

                    tmp = qty[j];
                    qty[j] = qty[j + 1];
                    qty[j + 1] = tmp;
                    swap = true;
                }
            }
        } while (swap);
    }

    private void calculate(List<Integer> store) {
        initLimit();
        boolean start = true, chaloo = true;
        int best = 0, sum = 0;
        comb = new int[total];
        while (start) {
            this.combinations();
            sum = 0;
            for (int i = 0; i < total; i++) {
                sum += block[i] * comb[i];
                if (sum > max) {
                    sum = 0;
                    break;
                }
            }

            if (sum > 0) //if a comb suited
            {
                if (sum == max) // if best comb found
                {
                    this.showComb(0, store);  //print comb
                    resetComb();
                    updateLimit();
                    best = 0;
                    sum = 0;
                } else if (sum > best) {
                    best = sum;
                    tempcomb = new int[total];
                    for (int i = 0; i < total; i++) // guarda a melhor combinação no array tempComb[]
                    {
                        tempcomb[i] = comb[i];
                    }
                    sum = 0;
                    for (int k = 0; k < tempcomb[k]; k++) {
                     
                        System.out.println("\n Best combination: " + tempcomb[k]);
                    }

                }
            }
            for (int i = 0; i < total; i++) //Verificar se as combinaçoes foram executadas
            {
                if (comb[i] != limit[i]) {
                    chaloo = true;
                    break;
                }
                chaloo = false;
            }
            if (!chaloo) // when all comb completed
            {
                this.showComb(best, store);
                updateLimit();
                resetComb();
                best = 0;
            }
            for (int i = 0; i < total; i++) // To end while loop when no more pieces left
            {
                if (qty[i] == 0 && i != total - 1) {
                    continue;
                } else if (i == total - 1 && qty[i] == 0) {
                    start = false;
                }
                break;
            }

        }
    }

    private void showComb(int a, List<Integer> store) {
        counter++;

        boolean flag = false;
        if (a == 0) {
            Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < total; i++) {
                if (comb[i] != 0) {
                    tempMap.put(new Integer(block[i]), new Integer(comb[i]));
                    //System.out.println(block[i]+"  *  "+comb[i]);
                    qty[i] = qty[i] - comb[i]; //  eliminar resultadoq eu ja foram apresentados
                    if ((qty[i] - comb[i]) < 0) {
                        flag = true;  
                    }
                }
            }

            if (flag) {
                mapList.add(tempMap);
                return;
            }
            showComb(0, store);
        } else {
            Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < total; i++) {
                if (tempcomb[i] != 0) {
                    tempMap.put(new Integer(block[i]), new Integer(tempcomb[i]));
                }
            }
            mapList.add(tempMap);
            System.out.println("\nWaste = " + (max - a));
            waste += max - a;
            store.add(max - a);
            for (int i = 0; i < total; i++) {
                qty[i] = qty[i] - tempcomb[i];
            }

            for (int i = 0; i < total; i++) {
                if ((qty[i] - comb[i]) < 0) {
                    return;
                }
            }
            showComb(a, store);
        }
    }

    private void combinations() {
        for (int i = total - 1;;) {
            if (comb[i] != limit[i]) {
                comb[i]++;
                break;
            } else {
                if (i == 0 && comb[0] != limit[0]) {
                    i = total - 1;
                } else {
                    comb[i] = 0;
                    i--;
                }
            }
        }
    }

    private void initLimit() {
        int div;
        limit = new int[total];
        for (int i = 0; i < total; i++) {
            div = max / block[i];
            if (qty[i] > div) {
                limit[i] = div;
            } else {
                limit[i] = qty[i];
            }
        }
    }

    private void updateLimit() {
        for (int i = 0; i < total; i++) {
            if (qty[i] < limit[i]) {
                limit[i] = qty[i];
            }
        }
    }

    private void resetComb() {
        for (int i = 0; i < total; i++) // reset comb[]
        {
            comb[i] = 0;
        }
    }

}
