/*Repita a questão de Ordenação por Seleção, contudo, usando o algoritmo Heapsort, fazendo com que a chave de pesquisa seja o atributo height. 
O nome do arquivo de log será matrícula_heapsort.txt.


(Lembre-se: em caso de empate, o critério de ordenação deverá ser o nome do Pokémon) */

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Pokemon {
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    public Pokemon() {
    }

    public Pokemon(int id, int generation, String name,
            String description, ArrayList<String> types, ArrayList<String> abilities, double weight,
            double height, int captureRate, boolean isLegendary, LocalDate captureDate) {
        setId(id);
        setGeneration(generation);
        setName(name);
        setDescription(description);
        setTypes(types);
        setAbilities(abilities);
        setWeight(weight);
        setHeight(height);
        setCaptureRate(captureRate);
        setIsLegendary(isLegendary);
        setCaptureDate(captureDate);
    }

    // id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // generation
    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // types
    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    // abilities
    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public void setAbilities(String abilities) {

        abilities = abilities.replaceAll("[\\[\\]\"']", "").trim();

        this.abilities = new ArrayList<>(Arrays.asList(abilities.split(",\\s*")));
    }

    // weight
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // height
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // captureRate
    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    // isLegendary
    public boolean getIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    // captureDate
    public LocalDate getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }

    void ler(String csvLine) {
        String[] data = csvLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

        setId(Integer.parseInt(data[0]));
        setGeneration(Integer.parseInt(data[1]));
        setName(data[2]);
        setDescription(data[3]);

        // types
        ArrayList<String> typesList = new ArrayList<>();
        typesList.add(data[4]);
        if (!data[5].isEmpty())
            typesList.add(data[5]);
        setTypes(typesList);

        // abilities
        String abilitiesStr = data[6].replace("[", "").replace("]", "").replace("'", "").trim();
        setAbilities(abilitiesStr);

        // weight
        if (!data[7].isEmpty()) {
            setWeight(Double.parseDouble(data[7]));
        } else {
            setWeight(0);
        }

        // height
        if (!data[8].isEmpty()) {
            setHeight(Double.parseDouble(data[8]));
        } else {
            setHeight(0); // Define 0 ou outro valor padrão se o campo estiver vazio
        }

        // captureRate
        if (!data[9].isEmpty()) {
            setCaptureRate(Integer.parseInt(data[9]));
        } else {
            setCaptureRate(0); // Define um valor padrão se o campo estiver vazio
        }

        setIsLegendary(data[10].equals("1") || data[10].equalsIgnoreCase("true"));

        // captureDate
        LocalDate date = parseDate(data[11]);
        setCaptureDate(date);

    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    String imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("[#");
        sb.append(getId()).append(" -> ");
        sb.append(getName()).append(": ");
        sb.append(getDescription()).append(" - ['");

        // types
        if (getTypes().size() > 0) {
            sb.append(getTypes().get(0));
        }
        sb.append("'");
        if (getTypes().size() > 1) {
            sb.append(", '");
            sb.append(getTypes().get(1)).append("'");
        }
        sb.append("] - ");

        // abilities
        sb.append("[");
        for (int i = 0; i < getAbilities().size(); i++) {
            sb.append("'");
            sb.append(getAbilities().get(i));
            sb.append("'");
            if (i < getAbilities().size() - 1) {
                // colocar a virgula caso ainda tenha abilities
                sb.append(", ");
            }
        }
        sb.append("] - ");

        sb.append(getWeight()).append("kg - ");
        sb.append(getHeight()).append("m - ");
        sb.append(getCaptureRate()).append("% - ");
        sb.append(getIsLegendary() ? "true" : "false").append(" - ");
        sb.append(getGeneration()).append(" gen] - ");
        sb.append(getCaptureDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return sb.toString();
    }

}

public class TP02Q09 {
    private static void swap(Pokemon[] findPokemon, int i, int j) {
        Pokemon temp = findPokemon[i];
        findPokemon[i] = findPokemon[j];
        findPokemon[j] = temp;
    }

    // Algoritmo Heapify
    private static void heapify(Pokemon[] findPokemon, int n, int i, int comp, int mov) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Verifica se o filho da esquerda é maior que o root
        comp++;
        if (left < n && findPokemon[left].getHeight() > findPokemon[largest].getHeight()) {
            largest = left;
        }

        // Verifica se o filho da direita é maior que o maior atual
        comp++;
        if (right < n && findPokemon[right].getHeight() > findPokemon[largest].getHeight()) {
            largest = right;
        }

        // Se o maior não for o root
        if (largest != i) {
            swap(findPokemon, i, largest);
            mov++;

            // Recursivamente heapify na subárvore afetada
            heapify(findPokemon, n, largest, comp, mov);
        }
    }

    // Algoritmo Heapsort
    private static void heapSort(Pokemon[] findPokemon, int tam, int comp, int mov) {
        // Construir o heap (reorganizar o array)
        for (int i = tam / 2 - 1; i >= 0; i--) {
            heapify(findPokemon, tam, i, comp, mov);
        }

        // Extrair um elemento por vez do heap
        for (int i = tam - 1; i > 0; i--) {
            swap(findPokemon, 0, i); // Mover o root atual para o final
            mov++;
            heapify(findPokemon, i, 0, comp, mov); // Chamar heapify no heap reduzido
        }
    }

    public static void main(String[] args) {
        // ler o csv
        String csvPath = "/tmp/pokemon.csv";

        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

        // inicio medicao tempo
        long start = System.nanoTime();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            br.readLine();

            while (br.ready()) {
                String linha = br.readLine();

                Pokemon p = new Pokemon();
                p.ler(linha);
                pokedex.add(p);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado em " + csvPath);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        String id = sc.nextLine();
        int tam = 0;
        Pokemon[] findPokemon = new Pokemon[51];
        int comp = 0;
        int mov = 0;

        while (!(id.equals("FIM"))) {
            for (Pokemon p : pokedex) {
                comp++;
                if (p.getId() == Integer.parseInt(id)) {
                    findPokemon[tam++] = p;
                    break;
                }
            }

            id = sc.nextLine();
        }

        heapSort(findPokemon, tam, comp, mov);

        for (int j = 0; j < tam; j++) {
            System.out.println(findPokemon[j].imprimir());
        }

        long end = System.nanoTime();

        double executionTime = (end - start);

        String conteudo = "775799" + "\t" + mov + "\t" + comp + '\t' + executionTime + "ms";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("775799_heapsort.txt"))) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        sc.close();
    }
}