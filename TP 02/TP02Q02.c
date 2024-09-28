/*Repita a anterior criando o registro Pokémon na linguagem C.*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

// Definição da struct Pokemon
typedef struct
{
    int id;
    int generation;
    char name[100];
    char description[256];
    char types[2][50];      // Máximo de 2 tipos
    char abilities[10][50]; // Máximo de 10 habilidades
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    struct tm captureDate;
} Pokemon;

// Função para inicializar um Pokemon a partir de uma linha de CSV
Pokemon createPokemon(char *linha)
{
    Pokemon p;
    char *token;
    int counter = 0;

    // Divide a linha usando a vírgula como delimitador
    token = strtok(linha, ",");
    while (token != NULL)
    {
        switch (counter)
        {
        case 0:
            p.id = atoi(token);
            break;
        case 1:
            p.generation = atoi(token);
            break;
        case 2:
            strcpy(p.name, token);
            break;
        case 3:
            strcpy(p.description, token);
            break;
        case 4:
            strcpy(p.types[0], token);
            break;
        case 5:
            strcpy(p.types[1], token);
            break;
        default:
            if (counter >= 6 && counter < 16)
            {
                // Verifica se o campo contém um número para sair da leitura de habilidades
                char *endptr;
                strtod(token, &endptr);
                if (*endptr != '\0')
                {
                    strcpy(p.abilities[counter - 6], token);
                }
                else
                {
                    p.weight = atof(token);
                    counter++;
                    p.height = atof(strtok(NULL, ","));
                    counter++;
                    p.captureRate = atoi(strtok(NULL, ","));
                    counter++;
                    p.isLegendary = atoi(strtok(NULL, ","));
                    counter++;

                    char *dateToken = strtok(NULL, ",");
                    strptime(dateToken, "%d/%m/%Y", &p.captureDate);
                    counter++;
                    break;
                }
            }
        }
        counter++;
        token = strtok(NULL, ",");
    }
    return p;
}

// Função para imprimir os dados de um Pokemon
void imprimirPokemon(Pokemon p)
{
    printf("[#%d -> %s: %s - [", p.id, p.name, p.description);

    // Imprime tipos
    printf("'%s'", p.types[0]);
    if (strlen(p.types[1]) > 0)
    {
        printf(", '%s'", p.types[1]);
    }

    printf("] - [");

    // Imprime habilidades
    int first = 1;
    for (int i = 0; i < 10 && strlen(p.abilities[i]) > 0; i++)
    {
        if (!first)
        {
            printf(", ");
        }
        printf("'%s'", p.abilities[i]);
        first = 0;
    }

    // Formata a data
    char buffer[20];
    strftime(buffer, sizeof(buffer), "%d/%m/%Y", &p.captureDate);

    // Imprime os atributos restantes
    printf("] - %.2fkg - %.2fm - %d%% - %s - %d gen] - %s\n",
           p.weight, p.height, p.captureRate,
           p.isLegendary ? "true" : "false",
           p.generation, buffer);
}

int main()
{
    char path[] = "/tmp/pokemon.csv";
    FILE *file = fopen(path, "r");

    if (!file)
    {
        printf("error\n");
        return 1;
    }

    // Array dinâmico para armazenar Pokemons
    Pokemon *pokemons = NULL;
    int pokemonCount = 0;
    char line[1024];

    // Ignora a primeira linha (cabeçalho)
    fgets(line, sizeof(line), file);

    // Lê cada linha do arquivo e cria um novo Pokemon
    while (fgets(line, sizeof(line), file))
    {
        pokemons = realloc(pokemons, (pokemonCount + 1) * sizeof(Pokemon));
        pokemons[pokemonCount] = createPokemon(line);
        pokemonCount++;
    }

    fclose(file);

    // Leitura do input do usuário
    char input[10];
    while (true)
    {
        scanf("%s", input);
        if (strcmp(input, "FIM") == 0)
        {
            break;
        }

        int number = atoi(input);
        if (number >= 1 && number <= pokemonCount)
        {
            imprimirPokemon(pokemons[number - 1]);
        }
    }

    // Libera a memória alocada
    free(pokemons);

    return 0;
}
