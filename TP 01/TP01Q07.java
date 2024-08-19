/*Leia duas strings sendo que a primeira é o nome de uma página web e a segunda, seu endereço. 
Por exemplo, Pontifícia Universidade Católica de Minas Gerais'' e www.pucminas.br''. 
Em seguida, mostre na tela o número de vogais (sem e com acento), consoantes e dos padrões $\<br\>$'' e $<table>$'' que aparecem no código dessa página. 
A entrada padrão é composta por várias linhas. Cada uma contém várias strings sendo que a primeira é um endereço web e as demais o nome dessa página web. 
A última linha da entrada padrão contém a palavra ``FIM''.

A saída padrão contém várias linhas sendo que cada uma apresenta o número de ocorrência (valor $x_{i}$ entre parênteses) de cada caractere ou string solicitado. 
Cada linha de saída será da seguinte forma: 
a($x_{1}$) e($x_{2}$) i($x_{3}$) o($x_{4}$) u($x_{5}$) á($x_{6}$) é($x_{7}$) í($x_{8}$) ó($x_{9}$) ú($x_{10}$) à($x_{11}$) è($x_{12}$) ì($x_{13}$) 
ò($x_{14}$) ù($x_{15}$) ã($x_{16}$) õ($x_{17}$) â($x_{19}$) ê($x_{19}$) î($x_{20}$) ô($x_{21}$) û($x_{22}$) consoante($x_{23}$) $<br>$($x_{24}$) $<table>$($x_{25}$) 
nomepágina($x_{26}$). */

import java.io.*;
import java.net.*;

//classe que vai contar br e table
class Counter {
    public int[] a;
    public int[] e;
    public int[] i;
    public int[] o;
    public int[] u;
    public int consoante;
    public int br;
    public int table;
    public String show;

    Counter(String show) {
        // indices dos arrays:
        // 0 - letra normal
        // 1 - letra com acento agudo
        // 2 - letra com crase
        // 3 - letra com til
        // 4 - letra com acento circunflexo
        this.a = new int[5];
        this.e = new int[5];
        this.i = new int[5];
        this.o = new int[5];
        this.u = new int[5];

        this.consoante = 0;
        this.br = 0;
        this.table = 0;
        this.show = show;
    }

    // método de print das respostas
    public String toString() {
        return "a(" + this.a[0] + ") " +
                "e(" + this.e[0] + ") " +
                "i(" + this.i[0] + ") " +
                "o(" + this.o[0] + ") " +
                "u(" + this.u[0] + ") " +
                "á(" + this.a[1] + ") " +
                "é(" + this.e[1] + ") " +
                "í(" + this.i[1] + ") " +
                "ó(" + this.o[1] + ") " +
                "ú(" + this.u[1] + ") " +
                "à(" + this.a[2] + ") " +
                "è(" + this.e[2] + ") " +
                "ì(" + this.i[2] + ") " +
                "ò(" + this.o[2] + ") " +
                "ù(" + this.u[2] + ") " +
                "ã(" + this.a[3] + ") " +
                "õ(" + this.o[3] + ") " +
                "â(" + this.a[4] + ") " +
                "ê(" + this.e[4] + ") " +
                "î(" + this.i[4] + ") " +
                "ô(" + this.o[4] + ") " +
                "û(" + this.u[4] + ") " +
                "consoante(" + this.consoante + ") " +
                "<br>(" + this.br + ") " +
                "<table>(" + this.table + ") " +
                this.show;
    }
}

public class TP01Q07 {
    // verifica se a palavra é igual a FIM
    public static boolean FIM(String str) {
        return str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M';
    }

    // busca o html da página depois de receber o link dela
    @SuppressWarnings("deprecation")
    public static String getHtml(String addr) {
        URL url;
        InputStream is = null;
        BufferedReader br;

        String resp = new String();
        String line = new String();

        try {
            url = new URL(addr);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
        }

        return resp;
    }

    // verifica se a letra é maiuscula
    public static boolean verificaMaiuscula(char c) {
        return 'A' <= c && c <= 'Z';
    }

    // verifica se a letra é minuscula
    public static boolean verificaMinuscula(char c) {
        return 'a' <= c && c <= 'z';
    }

    // verifica se o char é uma letra
    public static boolean verificaLetra(char c) {
        return verificaMaiuscula(c) || verificaMinuscula(c);
    }

    // verifica se é uma vogal
    public static boolean verificaVogal(char c) {
        if (verificaMaiuscula(c))
            return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
        else if (verificaMinuscula(c))
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        else
            return false;
    }

    // verifica se é uma consoante
    public static boolean verificaConsoante(char c) {
        return verificaMinuscula(c) && !verificaVogal(c);
    }

    // verifica se a String é igual ao <br>
    public static boolean verificaBr(String str, int i) {
        return str.charAt(i) == '<' &&
                str.charAt(i) == 'b' &&
                str.charAt(i) == 'r' &&
                str.charAt(i) == '>';
    }

    // verifica se a String é igual ao <table>
    public static boolean verificaTable(String str, int i) {
        return str.charAt(i) == '<' &&
                str.charAt(i + 1) == 't' &&
                str.charAt(i + 2) == 'a' &&
                str.charAt(i + 3) == 'b' &&
                str.charAt(i + 4) == 'l' &&
                str.charAt(i + 5) == 'e' &&
                str.charAt(i + 6) == '>';
    }

    // percorre o html todo
    public static void searchHtml(Counter counter, String html) {
        for (int i = 0; i < html.length(); i++) {
            // conta <table>
            if (verificaTable(html, i)) {
                counter.table++;
                i += 6;
            } else if (verificaBr(html, i)) {
                // conta <br>
                counter.br++;
                i += 3;
            } else if (verificaConsoante(html.charAt(i))) {
                // conta consoante
                counter.consoante++;
            } else {
                switch (html.charAt(i)) {
                    case 'a':
                        counter.a[0]++;
                        break;
                    case 'e':
                        counter.e[0]++;
                        break;
                    case 'i':
                        counter.i[0]++;
                        break;
                    case 'o':
                        counter.o[0]++;
                        break;
                    case 'u':
                        counter.u[0]++;
                        break;
                    case 225:
                        counter.a[1]++;
                        break;
                    case 233:
                        counter.e[1]++;
                        break;
                    case 237:
                        counter.i[1]++;
                        break;
                    case 243:
                        counter.o[1]++;
                        break;
                    case 250:
                        counter.u[1]++;
                        break;
                    case 224:
                        counter.a[2]++;
                        break;
                    case 232:
                        counter.e[2]++;
                        break;
                    case 236:
                        counter.i[2]++;
                        break;
                    case 242:
                        counter.o[2]++;
                        break;
                    case 249:
                        counter.u[2]++;
                        break;
                    case 227:
                        counter.a[3]++;
                        break;
                    case 245:
                        counter.o[3]++;
                        break;
                    case 226:
                        counter.a[4]++;
                        break;
                    case 234:
                        counter.e[4]++;
                        break;
                    case 238:
                        counter.i[4]++;
                        break;
                    case 244:
                        counter.o[4]++;
                        break;
                    case 251:
                        counter.u[4]++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String site = new String();
        String enderecoSite = new String();
        String html = new String();

        boolean stop;

        do {
            site = MyIO.readLine();

            // chama a função que compara as palavras, pra ver se não é igual a FIM
            stop = FIM(site);

            if (!stop) {
                Counter cont = new Counter(site);

                enderecoSite = MyIO.readLine();
                html = getHtml(enderecoSite);

                searchHtml(cont, html);

                MyIO.println(cont.toString());
            }
        } while (!stop);
    }
}