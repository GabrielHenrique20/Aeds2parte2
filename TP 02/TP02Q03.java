/*
 * Faça a inserção de alguns registros no final de um vetor e, em seguida, faça
 * algumas pesquisas sequenciais. A chave primária de pesquisa será o atributo
 * name.
 * 
 * 
 * Entrada
 * A entrada padrão é composta por duas partes onde a primeira é igual a entrada
 * da primeira questão. As demais linhas correspondem a segunda parte. A segunda
 * parte é composta por várias linhas. Cada uma possui um elemento que deve ser
 * pesquisado no vetor. A última linha terá a palavra FIM.
 * 
 * 
 * Saída
 * A saída padrão será composta por várias linhas contendo as palavras SIM/NAO
 * para indicar se existe cada um dos elementos pesquisados. Além disso, crie um
 * arquivo de log na pasta corrente com o nome matrícula_sequencial.txt com uma
 * única linha contendo sua matrícula, tempo de execução do seu algoritmo e
 * número de comparações. Todas as informações do arquivo de log devem ser
 * separadas por uma tabulação '\t'.
 */

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

public class TP02Q03 {
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
                // System.out.println("Linha lida: " + linha);

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

        // System.out.println("Pokémons carregados: " + pokedex.size());
        // for (Pokemon p : pokedex) {
        // System.out.println(p.imprimir());
        // }

        Scanner sc = new Scanner(System.in);

        String id = sc.nextLine();
        int i = 0;
        Pokemon[] findPokemon = new Pokemon[51];
        int comp = 0;

        while (!(id.equals("FIM"))) {
            for (Pokemon p : pokedex) {
                comp++;
                if (p.getId() == Integer.parseInt(id)) {
                    findPokemon[i++] = p;
                    break;
                }
            }

            id = sc.nextLine();
        }

        String name = sc.nextLine();

        while (!(name.equals("FIM"))) {
            boolean foundPokemon = false;

            for (int j = 0; j < i; j++) {
                comp++;
                if (findPokemon[j].getName().equals(name)) {
                    foundPokemon = true;
                    break;
                }
            }

            if (foundPokemon == true) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            name = sc.nextLine();
        }

        long end = System.nanoTime();

        double executionTime = (end - start);

        String conteudo = "775799" + "\t" + executionTime + "ms" + "\t" + comp;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("775799_sequencial.txt"))) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        sc.close();
    }
}