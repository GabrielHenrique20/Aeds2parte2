/*
 * Complete o código da classe matriz dinâmica visto na sala de aula. A primeira
 * tarefa consiste em, no construtor da classe Matriz, dados os números de linha
 * e coluna, fazer as devidas alocações de células. As demais tarefas são as
 * implementações dos métodos Matriz soma(Matriz), Matriz multiplicacao(Matriz),
 * void mostrarDiagonalPrincipal() e void mostrarDiagonalSecundaria().
 * 
 * A entrada padrão é composta por vários casos de teste sendo que o número de
 * casos é um inteiro contido na primeira linha da entrada. Em seguida, temos
 * cada um dos casos de teste. Cada caso é composto por duas matrizes. Para cada
 * caso de teste, temos que suas duas primeiras linhas contêm um número inteiro
 * cada representando os números de linhas e de colunas da primeira matriz,
 * respectivamente. Em seguida, temos os elementos da primeira matriz que estão
 * representados nas próximas l linhas onde l é o número de linhas dessa matriz.
 * Cada uma dessas linhas têm c colunas onde c é o número de colunas dessa
 * matriz. Nas duas linhas seguintes, temos os números de linhas e colunas da
 * segunda matriz do caso de teste. As l2 linhas seguintes têm c2 colunas
 * contendo os elementos da segunda matriz. l2 e c2 correspondem aos números de
 * linhas e colunas da segunda matriz do caso de teste, respectivamente.
 * 
 * 
 * A saída padrão contém várias linhas para cada caso de teste. As duas
 * primeiras linhas de saída de um caso de teste correspondem às diagonais
 * principal e secundaria da primeira matriz, respectivamente. As demais ls
 * linhas de um caso de teste correspondem as linhas matriz obtida pela soma das
 * duas matrizes do caso de teste sendo que essas linhas contêm $cs$ colunas
 * referentes às colunas da matriz de soma. Da mesma forma, as linhas seguintes
 * do caso teste contêm lm linhas com cm colunas representando os elementos da
 * matriz de multiplicação onde lm e cm são os números de linhas e colunas da
 * matriz de multiplicação.
 */

import java.util.*;

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

public class TP03Q11 {
    public static Scanner sc = new Scanner(System.in);

    private Celula inicio;
    private int linha, coluna;

    public TP03Q11() {
        this(3, 3);
    }

    public TP03Q11(int linha, int coluna) {
        // Definir dados de linha e coluna
        this.linha = linha;
        this.coluna = coluna;
        // Criar as colunas
        inicio = new Celula();
        Celula i = inicio, j = null;
        for (int x = 1; x < coluna; x++) {
            // Criar nova celula
            j = new Celula();
            // Conectar celulas
            i.dir = j;
            j.esq = i;
            // Avancar ponteiros
            i = i.dir;
            j = j.dir;
        }
        // Criar as linhas
        i = inicio;
        j = i.dir;
        for (int x = 1; x < linha; x++) {
            // Criar nova celula
            i.inf = new Celula();
            i.inf.sup = i;
            // Criar ponteiros temporarios
            Celula iTemp = i;
            Celula jTemp = j;
            // Criar restante da linha
            for (int y = 1; y < coluna; y++) {
                // Criar nova celula
                jTemp.inf = new Celula();
                jTemp.inf.sup = jTemp;
                // Conectar celula
                iTemp.inf.dir = jTemp.inf;
                jTemp.inf.esq = iTemp.inf;
                // Avancar ponteiros
                iTemp = iTemp.dir;
                jTemp = jTemp.dir;
            }
            // Avancar ponteiros
            i = i.inf;
            j = j.inf;
        }
    }

    public void ler() throws Exception {
        // Testar valores de linha e coluna
        if (linha <= 0 || coluna <= 0)
            throw new Exception("Não é possível ler dados para a matriz.");
        // Ler dados e atribuir a' matriz
        Celula i = this.inicio;
        for (int x = 0; x < linha; x++) {
            Celula j = i;
            for (int y = 0; y < coluna; y++) {
                j.elemento = sc.nextInt();
                j = j.dir;
            }
            i = i.inf;
        }
    }

    public TP03Q11 soma(TP03Q11 m) throws Exception {
        // Testar se é possível somar as matrizes
        if (this.linha != m.linha || this.coluna != m.coluna)
            throw new Exception("Não é possível somar duas matrizes.");
        // Definir matriz de resposta
        TP03Q11 resp = new TP03Q11(this.linha, this.coluna);
        // Definir ponteiros
        Celula a = this.inicio;
        Celula b = m.inicio;
        Celula c = resp.inicio;
        // Percorrer as linhas
        for (int x = 0; x < linha; x++) {
            // Definir ponteiros temporarios
            Celula a_ = a;
            Celula b_ = b;
            Celula c_ = c;
            // Percorrer as colunas
            for (int y = 0; y < coluna; y++) {
                // Definir soma dos elementos
                c_.elemento = a_.elemento + b_.elemento;
                // Deslocar ponteiros
                a_ = a_.dir;
                b_ = b_.dir;
                c_ = c_.dir;
            }
            // Deslocar ponteiros
            a = a.inf;
            b = b.inf;
            c = c.inf;
        }
        // Retornar
        return resp;
    }

    public TP03Q11 multiplicacao(TP03Q11 m) throws Exception {
        // Verificar se a multiplicação é possível
        if (this.coluna != m.linha)
            throw new Exception("Não é possível multiplicar as duas matrizes");
        // Criar matriz de resposta
        TP03Q11 resp = new TP03Q11(this.linha, m.coluna);
        // Definir ponteiros para percorrer as linhas e colunas
        Celula linhaResp = resp.inicio; // Linha da matriz resposta
        Celula linhaThis = this.inicio; // Linha da matriz atual
        // Percorrer as linhas
        for (int x = 0; x < resp.linha; x++) {
            // Definir ponteiros
            Celula colunaResp = linhaResp; // Coluna da matriz resposta
            Celula colunaMatriz = m.inicio; // Coluna da matriz m
            // Percorrer as colunas
            for (int y = 0; y < resp.coluna; y++) {
                // Definir ponteiros
                Celula elementoLinhaThis = linhaThis; // Elemento atual da linha de 'this'
                Celula elementoColunaMatriz = colunaMatriz; // Elemento atual da coluna de 'm'
                // Calcular o valor para resp[x][y]
                int soma = 0;
                for (int k = 0; k < this.coluna; k++) {
                    soma += elementoLinhaThis.elemento * elementoColunaMatriz.elemento;
                    elementoLinhaThis = elementoLinhaThis.dir; // Avançar na linha de 'this'
                    elementoColunaMatriz = elementoColunaMatriz.inf; // Avançar na coluna de 'm'
                }
                // Atribuir soma ao elemento da matriz resposta
                colunaResp.elemento = soma;
                // Deslocar ponteiros
                colunaResp = colunaResp.dir; // Avançar para a próxima coluna
                colunaMatriz = colunaMatriz.dir; // Avançar para a próxima coluna de 'm'
            }
            // Deslocar ponteiros
            linhaResp = linhaResp.inf;
            linhaThis = linhaThis.inf;
        }
        // Retornar
        return resp;
    }

    public boolean isQuadrada() {
        return (this.linha == this.coluna);
    }

    public void mostrarDiagonalPrincipal() {
        // Testar se a matriz é quadrada
        if (!isQuadrada())
            return;
        // Percorrer a diagonal principal
        Celula i = this.inicio;
        for (int x = 0; x < linha; x++) {
            System.out.print(i.elemento + " ");
            i = (i.dir != null) ? i.dir.inf : null;
        }
        System.out.println("");
    }

    public void mostrarDiagonalSecundaria() {
        // Testar se a matriz é quadrada
        if (!isQuadrada())
            return;
        // Percorrer a diagonal secundaria
        Celula i = inicio;
        for (int x = 1; x < coluna; x++, i = i.dir)
            ;
        for (int x = 0; x < linha; x++) {
            System.out.print(i.elemento + " ");
            i = (i.esq != null) ? i.esq.inf : null;
        }
        System.out.println("");
    }

    public void mostrar() {
        if (this.linha <= 0 && this.coluna <= 0)
            return;
        Celula i = inicio;
        for (int x = 0; x < linha; x++, i = i.inf) {
            Celula j = i;
            for (int y = 0; y < coluna; y++, j = j.dir) {
                System.out.print(j.elemento + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String args[]) {
        try {
            // Definir dados locais
            int n;
            // Ler quantidade de casos
            n = sc.nextInt();
            // n = 1;
            // Laço de repetiçao
            for (int i = 0; i < n; i++) {
                // Definir dados locais
                int linha1, coluna1, linha2, coluna2;
                // Ler linhas e colunas da primeira matriz
                linha1 = sc.nextInt();
                coluna1 = sc.nextInt();
                // Definir primeira matriz
                TP03Q11 matriz1 = new TP03Q11(linha1, coluna1);
                matriz1.ler();
                // Ler dados da segunda matriz
                linha2 = sc.nextInt();
                coluna2 = sc.nextInt();
                // Definir segunda matriz
                TP03Q11 matriz2 = new TP03Q11(linha2, coluna2);
                matriz2.ler();
                // Mostrar diagonais da primeira matriz
                matriz1.mostrarDiagonalPrincipal();
                matriz1.mostrarDiagonalSecundaria();
                // Calcular matriz de soma
                TP03Q11 matrizSoma = matriz1.soma(matriz2);
                matrizSoma.mostrar();
                // Calcular matriz de multiplicacao
                TP03Q11 matrizMultiplicacao = matriz1.multiplicacao(matriz2);
                matrizMultiplicacao.mostrar();
                // Desreferenciar ponteiros
                matriz1 = matriz2 = matrizSoma = matrizMultiplicacao = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}