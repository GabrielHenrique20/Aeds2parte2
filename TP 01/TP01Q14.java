/*Refaça a questão Álgebra Booleana de forma recursiva.*/

public class TP01Q14 {
    public static void main(String[] args) {
        boolean fim;
        int rep;
        String palavra1 = new String();
        int tam;
        char[] entradaChar = new char[1000];

        do {
            rep = MyIO.readInt();

            fim = fim(rep);
            if (fim) {
                String[] listaValores = lerValores(rep);
                palavra1 = MyIO.readLine();
                stringChar(palavra1, entradaChar);
                tam = palavra1.length();
                tam = removerEspacos(entradaChar, tam);
                trocar(entradaChar, listaValores, tam);
                char resp = algebraBooleana(entradaChar, tam);
                MyIO.println(resp);
            }
        } while (fim);
    }

    // Converte de String para array de caracteres

    public static void stringChar(String palavra1, char[] entradaChar) {
        for (int i = 0; i < palavra1.length(); i++) {
            entradaChar[i] = palavra1.charAt(i);
        }
    }

    public static int removerEspacos(char[] entradaChar, int tam) {
        for (int i = 0; i < tam; i++) {
            if (entradaChar[i] == ' ' || entradaChar[i] == ',') {
                for (int j = i; j < tam - 1; j++) {
                    entradaChar[j] = entradaChar[j + 1];
                }
                i--;
                entradaChar[tam] = '\0';
                tam--;
            }
        }
        return tam;
    }

    // troca as letras pelos valores binários
    public static void trocar(char[] entradaChar, String[] listaValores, int tam) {
        int index = 0;
        for (int i = 0; i < listaValores.length; i++) {
            for (int j = 0; j < tam; j++) {
                if ((int) entradaChar[j] == index + 65) {
                    entradaChar[j] = (char) listaValores[index].charAt(0);
                }
            }
            index++;
        }
        return;
    }

    // le os valores binários de cada posição
    public static String[] lerValores(int quantValores) {
        String[] listaValores = new String[quantValores];
        for (int i = 0; i < quantValores; i++) {
            listaValores[i] = MyIO.readString();
        }
        return listaValores;
    }

    // boolean boolNot
    public static char boolNot(char[] valores, int quantValores) {
        char retorno = '1';
        for (int i = 0; i < quantValores; i++) {
            if (valores[i] == '1') {
                retorno = '0';
                i = quantValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    // boolean AND
    public static char boolAnd(char[] valores, int quantValores) {
        char retorno = '1';
        for (int i = 0; i < quantValores; i++) {
            if (valores[i] == '0') {
                retorno = '0';
                i = quantValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    // boolean OR
    public static char boolOr(char[] valores, int quantValores) {
        char retorno = '0';
        for (int i = 0; i < quantValores; i++) {
            if (valores[i] == '1') {
                retorno = '1';
                i = quantValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    // le a expressão booleana verificando os () e adicionais, como AND, NOT e OR
    public static char algebraBooleana(char[] entradaChar, int tam) {
        char substituir;
        char[] valores = new char[10];
        int quantValores = 0;

        for (int i = 0; i < tam; i++) {
            if (entradaChar[i] == ')') {
                for (int j = i; j > 0; j--) {
                    if (entradaChar[j] == '(') {
                        // not
                        if (entradaChar[j - 1] == 't') {
                            valores[quantValores] = entradaChar[j + 1];
                            quantValores++;

                            substituir = boolNot(valores, quantValores);
                            entradaChar[j - 3] = substituir;

                            for (int k = 0; k < quantValores + 4; k++) {
                                for (int w = j - 2; w < tam - 1; w++) {
                                    entradaChar[w] = entradaChar[w + 1];
                                }
                                entradaChar[tam - k] = '\0';
                            }
                            tam -= quantValores + 4;
                            quantValores = 0;
                            i = 0;
                            j = 0;
                        }

                        // and
                        else if (entradaChar[j - 1] == 'd') {
                            for (int k = j + 1; k < i; k++) {
                                valores[quantValores] = entradaChar[k];
                                quantValores++;
                            }
                            substituir = boolAnd(valores, quantValores);
                            entradaChar[j - 3] = substituir;

                            for (int k = 0; k < quantValores + 4; k++) {
                                for (int w = j - 2; w < tam - 1; w++) {
                                    entradaChar[w] = entradaChar[w + 1];
                                }
                                entradaChar[tam - k] = '\0';
                            }

                            tam -= quantValores + 4;
                            quantValores = 0;
                            i = 0;
                            j = 0;
                        }

                        // or
                        else if (entradaChar[j - 1] == 'r') {
                            for (int k = j + 1; k < i; k++) {
                                valores[quantValores] = entradaChar[k];
                                quantValores++;
                            }
                            substituir = boolOr(valores, quantValores);
                            entradaChar[j - 2] = substituir;

                            for (int k = 0; k < quantValores + 3; k++) {
                                for (int w = j - 1; w < tam - 1; w++) {
                                    entradaChar[w] = entradaChar[w + 1];
                                }
                                entradaChar[tam - k] = '\0';
                            }
                            tam -= quantValores + 3;
                            quantValores = 0;
                            i = 0;
                            j = 0;
                        }
                    }
                }
            }
        }
        return entradaChar[0];
    }

    // verificação da palavra FIM
    public static boolean fim(int quantNumeros) {
        boolean fim = true;
        if (quantNumeros == 0) {
            fim = false;
        }
        return fim;
    }

}