/*Entrada
A primeira linha de entrada contém dois inteiros K e N separados por um espaço em branco, indicando, 
respectivamente, o número de caracteres presentes no alfabeto alienígena e o número de caracteres da mensagem enviada.
A segunda linha de entrada contém K caracteres distintos representando os caracteres pertencentes ao alfabeto 
alienígena.
A terceira linha de entrada contém N caracteres (não necessariamente distintos) representando a mensagem enviada.

Saída
Seu programa deverá imprimir uma única linha contendo um único caractere: se a mensagem 
pode ter sido escrita no alfabeto alienígena, imprima a letra ‘S’ maiúscula; caso contrário, 
imprima a letra ‘N’ maiúscula. */

import java.util.*;

public class AlfabetoAlienigena {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        @SuppressWarnings("unused")
        int K = sc.nextInt(); // quantidade de caracteres do alfabeto alienígena (nao consegui usar o K para
                              // contar quantos caracteres tem na palavra alien, ir arrumar depois da prova!!)
        int N = sc.nextInt(); // quantidade de caracteres da mensagem enviada

        sc.nextLine(); // Consumir a nova linha (/n)

        String AlfabetoAlien = sc.nextLine();
        String MensagemEnviada = sc.nextLine();

        // Variável para verificar se a mensagem pertence ou nao ao alfabeto alienígena
        boolean isMensagemAlien = true;

        int i;
        for (i = 0; i < N; i++) {
            char caractereMensagem = MensagemEnviada.charAt(i); // atribui cada caracter da mensgem a esse char

            // Se o caractere da mensagem não estiver no alfabeto alienígena
            if (AlfabetoAlien.indexOf(caractereMensagem) == -1) {// utilização do indexOf (comparação de caracteres e
                                                                 // strings)
                isMensagemAlien = false;
                break; // Se encontramos um caractere inválido, podemos parar a verificação entre as
                       // palavras e sai do loop
            }
        }

        // Imprimir o resultado (S para mensagem alien e N para nao mensagem alien)
        if (isMensagemAlien) {
            System.out.println('S');
        } else {
            System.out.println('N');
        }

        sc.close();
    }
}
