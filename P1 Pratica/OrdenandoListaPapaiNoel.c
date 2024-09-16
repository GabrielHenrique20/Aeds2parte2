/*Entrada
A entrada é composta por vários nomes.
O primeiro valor N (0 ≤ N ≤ 100), indica quantos nomes tem na lista.
As N linhas seguintes, contem um caracter especial correspondente ao comportamento da criança
(+ indica que a criança foi bem comportada, - indica que a criança não foi bem comportada).
Após o caracter especial, segue o nome da criança com no máximo 20 caracteres.

Saída
Para cada lista de crianças, você deve imprimir os nomes em ordem alfabética.
Após imprimir os nomes das crianças, você deve mostrar
o total de crianças que se comportaram bem ou mal durante o ano.*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_N 100
#define MAX_PALAVRA 100

// Criar a struct aluno com seu nome e comportamento par cada aluno
typedef struct Aluno
{
    char nome[MAX_PALAVRA];
    char comportamento;
} Aluno;

// Metodo de ordenação bolha (passa de letra em letra como ua bolha) para ordenar cada aluno que digitar em ordem alfabetica
void ordenarAlunos(Aluno alunos[], int n)
{
    int i = 0;
    int j = 0;

    for (i = 0; i < (n - 1); i++)
    {
        for (j = 0; j < (n - i - 1); j++)
        {
            if (strcmp(alunos[j].nome, alunos[j + 1].nome) > 0)
            {
                Aluno temp = alunos[j]; // variavel temporaria
                alunos[j] = alunos[j + 1];
                alunos[j + 1] = temp;
            }
        }
    }
} // obs: esse e o metodo padrao da olha, sem as alteracoes pra melhorar sua eficiencia e complexidade

int main(void)
{

    int N;
    Aluno Alunos[MAX_N];
    int comportouBEM = 0;
    int comportouMAL = 0;
    int i = 0;

    scanf("%d", &N);

    // Leitura dos dados dos Alunos
    for (i = 0; i < N; i++)
    {
        scanf(" %c %s", &Alunos[i].comportamento, Alunos[i].nome); // scanf (" %[^ \n]")

        // + indica que a criança foi bem comportada, - indica que a criança não foi bem comportada
        if (Alunos[i].comportamento == '+')
        {
            comportouBEM++;
        }
        else if (Alunos[i].comportamento == '-')
        {
            comportouMAL++;
        }
    }

    // Ordenação dos Alunos por ordem alfabetica
    ordenarAlunos(Alunos, N);

    // Impressão da lista de Alunos ordenados em ordem alfabetica
    for (i = 0; i < N; i++)
    {
        printf("%s\n", Alunos[i].nome);
    }

    // Impressão dos resultados
    printf("Se comportaram: %d | Nao se comportaram: %d\n", comportouBEM, comportouMAL);

    return 0;
}
