import java.util.Arrays;
import java.util.Random;

public class QuicksortPivo {

    // Método para trocar elementos no array
    private void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 1. QuickSort com o primeiro elemento como pivô
    void QuickSortPivoPrimeiro(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionarPivoPrimeiro(array, esquerda, direita);
            QuickSortPivoPrimeiro(array, esquerda, indicePivo - 1);
            QuickSortPivoPrimeiro(array, indicePivo + 1, direita);
        }
    }

    private int particionarPivoPrimeiro(int[] array, int esquerda, int direita) {
        int pivo = array[esquerda];
        int i = esquerda + 1;
        int j = direita;

        while (i <= j) {
            while (i <= j && array[i] <= pivo)
                i++;
            while (i <= j && array[j] > pivo)
                j--;
            if (i < j)
                trocar(array, i, j);
        }
        trocar(array, esquerda, j);
        return j;
    }

    // 2. QuickSort com o último elemento como pivô
    void QuickSortPivoUltimo(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionarPivoUltimo(array, esquerda, direita);
            QuickSortPivoUltimo(array, esquerda, indicePivo - 1);
            QuickSortPivoUltimo(array, indicePivo + 1, direita);
        }
    }

    private int particionarPivoUltimo(int[] array, int esquerda, int direita) {
        trocar(array, esquerda, direita); // Move o pivô para o início
        int pivo = array[esquerda];
        int i = esquerda + 1;
        int j = direita;

        while (i <= j) {
            while (i <= j && array[i] <= pivo)
                i++;
            while (i <= j && array[j] > pivo)
                j--;
            if (i < j)
                trocar(array, i, j);
        }
        trocar(array, esquerda, j);
        return j;
    }

    // 3. QuickSort com pivô aleatório
    void QuickSortPivoAleatorio(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionarPivoAleatorio(array, esquerda, direita);
            QuickSortPivoAleatorio(array, esquerda, indicePivo - 1);
            QuickSortPivoAleatorio(array, indicePivo + 1, direita);
        }
    }

    private int particionarPivoAleatorio(int[] array, int esquerda, int direita) {
        int indicePivoAleatorio = esquerda + (int) (Math.random() * (direita - esquerda + 1));
        trocar(array, esquerda, indicePivoAleatorio); // Move o pivô para o início
        int pivo = array[esquerda];
        int i = esquerda + 1;
        int j = direita;

        while (i <= j) {
            while (i <= j && array[i] <= pivo)
                i++;
            while (i <= j && array[j] > pivo)
                j--;
            if (i < j)
                trocar(array, i, j);
        }
        trocar(array, esquerda, j);
        return j;
    }

    // 4. QuickSort com mediana de três elementos
    void QuickSortPivoMedianaTres(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionarPivoMedianaTres(array, esquerda, direita);
            QuickSortPivoMedianaTres(array, esquerda, indicePivo - 1);
            QuickSortPivoMedianaTres(array, indicePivo + 1, direita);
        }
    }

    private int particionarPivoMedianaTres(int[] array, int esquerda, int direita) {
        int meio = esquerda + (direita - esquerda) / 2;
        int indicePivo = medianaDeTres(array, esquerda, meio, direita);
        trocar(array, esquerda, indicePivo); // Move o pivô para o início
        int pivo = array[esquerda];
        int i = esquerda + 1;
        int j = direita;

        while (i <= j) {
            while (i <= j && array[i] <= pivo)
                i++;
            while (i <= j && array[j] > pivo)
                j--;
            if (i < j)
                trocar(array, i, j);
        }
        trocar(array, esquerda, j);
        return j;
    }

    private int medianaDeTres(int[] array, int a, int b, int c) {
        int x = array[a];
        int y = array[b];
        int z = array[c];
        if ((x > y) != (x > z))
            return a;
        else if ((y > x) != (y > z))
            return b;
        else
            return c;
    }

    // Métodos para gerar diferentes tipos de arrays
    int[] gerarArrayOrdenado(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++)
            array[i] = i;
        return array;
    }

    int[] gerarArrayQuaseOrdenado(int tamanho, int trocas) {
        int[] array = gerarArrayOrdenado(tamanho);
        Random rand = new Random();
        for (int i = 0; i < trocas; i++) {
            int indice1 = rand.nextInt(tamanho);
            int indice2 = rand.nextInt(tamanho);
            trocar(array, indice1, indice2);
        }
        return array;
    }

    int[] gerarArrayAleatorio(int tamanho) {
        int[] array = new int[tamanho];
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++)
            array[i] = rand.nextInt(tamanho * 10); // Ajuste o multiplicador conforme necessário
        return array;
    }

    // Método para medir o tempo de execução
    long medirTempoOrdenacao(int[] array, String metodo) {
        long tempoInicial = System.nanoTime();
        switch (metodo) {
            case "PivoPrimeiro":
                QuickSortPivoPrimeiro(array, 0, array.length - 1);
                break;
            case "PivoUltimo":
                QuickSortPivoUltimo(array, 0, array.length - 1);
                break;
            case "PivoAleatorio":
                QuickSortPivoAleatorio(array, 0, array.length - 1);
                break;
            case "PivoMedianaTres":
                QuickSortPivoMedianaTres(array, 0, array.length - 1);
                break;
            default:
                throw new IllegalArgumentException("Método inválido");
        }
        long tempoFinal = System.nanoTime();
        return tempoFinal - tempoInicial;
    }

    // Método principal para executar os testes
    public static void main(String[] args) {
        QuicksortPivo sorter = new QuicksortPivo();

        int[] tamanhos = { 100, 1000, 10000 };
        String[] metodos = { "PivoPrimeiro", "PivoUltimo", "PivoAleatorio", "PivoMedianaTres" };
        String[] tiposArray = { "Ordenado", "QuaseOrdenado", "Aleatorio" };

        for (int tamanho : tamanhos) {
            System.out.println("Tamanho do array: " + tamanho);
            for (String tipoArray : tiposArray) {
                System.out.println("  Tipo do array: " + tipoArray);
                int[] arrayOriginal = null;
                switch (tipoArray) {
                    case "Ordenado":
                        arrayOriginal = sorter.gerarArrayOrdenado(tamanho);
                        break;
                    case "QuaseOrdenado":
                        arrayOriginal = sorter.gerarArrayQuaseOrdenado(tamanho, tamanho / 10);
                        break;
                    case "Aleatorio":
                        arrayOriginal = sorter.gerarArrayAleatorio(tamanho);
                        break;
                }
                for (String metodo : metodos) {
                    int[] arrayParaOrdenar = Arrays.copyOf(arrayOriginal, arrayOriginal.length);
                    long tempo = sorter.medirTempoOrdenacao(arrayParaOrdenar, metodo);
                    System.out.printf("    %s: %d ns\n", metodo, tempo);
                }
            }
            System.out.println();
        }
    }
}
