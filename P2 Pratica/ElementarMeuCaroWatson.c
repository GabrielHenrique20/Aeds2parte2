/*Watson, Crick e Wilkins receberam em 1962 o prêmio Nobel de Medicina especialmente pelo seu trabalho que
resultou na descoberta da estrutura das moléculas do DNA e na sua
importância na transmissão de informações entre as gerações de seres vivos.
Watson e Crick publicaram na revista “Nature” em 1953 o artigo em que mostravam que a molécula de
DNA apresentava uma estrutura de dupla hélice.
O artigo assume enorme importância nos dias de hoje, especialmente depois dos vários avanços na área.

Muitas pesquisas têm sido feitas na área de Bioinformática ligadas à descoberta da sequência de bases que compõem
as moléculas de DNA dos vários seres vivos. Em especial, a estrutura destas moléculas tem sido usada para compor
teorias de como os seres vivos evoluíram e quais têm ancestrais comuns. Acredita-se que os seres vivos presentes
hoje no planeta podem descender de ancestrais comuns, sendo que as modificações nos seus respectivos
DNAs são devidas a fenômenos de mutação ocorridos durante a evolução.
Muitos biólogos acreditam no princípio da parcimônia, que diz que o número destas mutações deve ser o mínimo possível,
uma vez que a Natureza busca, de certa forma, o caminho “mais barato” para a modificação desejada.

Sua tarefa neste problema é auxiliar os pesquisadores na tarefa de determinar se duas sequências de
DNA podem ter um ancestral comum. Considere dadas duas sequências
(podemos imaginar como sequências de números inteiros).
O seu objetivo é determinar o menor número de trocas de elementos de uma das sequências
(os elementos não precisam estar em posições adjacentes na sequência)
que leva uma das sequências na outra. Observe que podemos considerar uma das sequências fixa
(por exemplo, em ordem crescente), dessa forma buscamos o número mínimo de tais trocas que ordena a sequência dada.*/

/*Entrada
A entrada é composta por diversas instâncias. A primeira linha da entrada contém um inteiro T
indicando o número de instâncias. A primeira linha de cada instância possui um inteiro N (1 ≤ N ≤ 10000)
indicando o número de inteiros na sequência. A segunda linha contém uma permutação dos inteiros 1, 2, ... ,
N separados por espaço.

Saída
Para cada instância imprima uma linha contendo o número mínimo de tais trocas que ordena a sequência dada.*/

/*2 inteiros principais T, N*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>
#include <string.h>

// Tamanho máximo definido para determnar as duas sequencias de DNA
#define MAX 10000
int num[MAX];
bool pos[MAX];

// -----------------------------------------------------------------------//

int main(void)
{
    int T = 0; // Inteiro T indicando o número de instâncias.
    int N = 0; // Indicando o número de inteiros na sequência
    int i = 0;

    scanf("%d", &T);

    while (T--){
        
        scanf("%d", &N);

        for (i = 0; i < N; i++)
        {
            scanf("%d", &num[i]);
            num[i]--;
            pos[i] = false; // Uso da Boolean para testes
        }

        int ciclosResultantes = 0; // Contador de ciclos

        for (i = 0; i < N; i++)
        {
            if (!pos[i])
            {
                // Encontrar um ciclo no for
                ciclosResultantes++;
                int j = i;

                while (!pos[j])
                {
                    pos[j] = true; // Marca como visitado
                    j = num[j];    // Move para o próximo índice do ciclo
                }
            }
        }

        // O número mínimo de trocas é N - ciclosResultantes
        int j = N - ciclosResultantes;
        printf("%d\n", j);
    }

    return 0;
}
