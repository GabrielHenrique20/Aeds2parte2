/*Implemente um programa denominado Combinador, que recebe duas strings e deve combiná-las, alternando as letras de cada string,
começando com a primeira letra da primeira string, seguido pela primeira letra da segunda string, em seguida pela segunda letra da primeira string,
e assim sucessivamente. As letras restantes da cadeia mais longa devem ser adicionadas ao fim da string resultante e retornada.*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{

    // Criação das palavras que cabem 100 caracteres cada
    char palavra1[100];
    char palavra2[100];

    while (1)
    {

        scanf("%s %s", palavra1, palavra2);

        if (strcmp(palavra1, " ") == 0 || strcmp(palavra2, " ") == 0)
        {
            break;
        }

        int tam1 = strlen(palavra1);
        int tam2 = strlen(palavra2);

        if (tam1 < tam2)
        {
            for (int i = 0; i < tam1; i++)
            {
                printf("%c%c", palavra1[i], palavra2[i]);
            }
            for (int i = tam1; i < tam2; i++)
            {
                printf("%c", palavra2[i]);
            }
        }
        else
        {
            for (int i = 0; i < tam2; i++)
            {
                printf("%c%c", palavra1[i], palavra2[i]);
            }
            for (int i = tam2; i < tam1; i++)
            {
                printf("%c", palavra1[i]);
            }
        }
        printf("\n");
    }

    return 0;
}