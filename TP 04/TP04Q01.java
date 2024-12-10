/*Crie uma Árvore Binária, fazendo inserções de registros conforme a entrada padrão. 
A chave de pesquisa é o atributo name. Não insira um elemento se sua chave estiver na árvore. 
Em seguida, pesquise se alguns registros estão cadastrados na Árvore, mostrando seus respectivos caminhos de pesquisa. 
A entrada padrão é igual a da questão de Pesquisa Sequencial.

A saída padrão é composta por várias linhas, uma para cada pesquisa. 
Cada linha é composta pelo caminho ou sequência de ponteiros (raiz, esq ou dir) utilizados na pesquisa e, no final, 
pelas palavras SIM ou NAO.

Além disso, crie um arquivo de log na pasta corrente com o nome matrícula_arvoreBinaria.txt com uma única linha 
contendo sua matrícula, tempo de execução do seu algoritmo e número de comparações. 
Todas as informações do arquivo de log devem ser separadas por uma tabulação '\textbackslash t'. */

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @SuppressWarnings("OverridableMethodCallInConstructor")
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

    // abilities
    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    /*
     * public void setAbilities(String abilities) {
     * 
     * abilities = abilities.replaceAll("[\\[\\]\"']", "").trim();
     * 
     * this.abilities = new ArrayList<>(Arrays.asList(abilities.split(",\\s*")));
     * }
     */
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

    public void ler(String csvLine) {
        String[] data = csvLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

        setId(Integer.parseInt(data[0]));
        setGeneration(Integer.parseInt(data[1]));
        setName(data[2]);
        setDescription(data[3]);

        // types
        ArrayList<String> typesList = new ArrayList<>();
        typesList.add(data[4]);
        if (!data[5].isEmpty()) {
            typesList.add(data[5]);
        }
        setTypes(typesList);

        // abilities
        String abilitiesStr = data[6].replace("[", "").replace("]", "").replace("'", "").trim();
        setAbilities(abilitiesStr);

        // weight
        setWeight(!data[7].isEmpty() ? Double.parseDouble(data[7]) : 0);

        // height
        setHeight(!data[8].isEmpty() ? Double.parseDouble(data[8]) : 0);

        // captureRate
        setCaptureRate(!data[9].isEmpty() ? Integer.parseInt(data[9]) : 0);

        setIsLegendary(data[10].equals("1") || data[10].equalsIgnoreCase("true"));

        // captureDate
        LocalDate date = parseDate(data[11]);
        setCaptureDate(date);
    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    public String getName() {
        return name;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public void setAbilities(String abilities) {
        abilities = abilities.replaceAll("[\\[\\]\"']", "").trim();
        this.abilities = new ArrayList<>(Arrays.asList(abilities.split(",\\s*")));
    }
}

class Node {

    Pokemon pokemon;
    Node left, right;

    public Node(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean insert(Pokemon pokemon) {
        if (root == null) {
            root = new Node(pokemon);
            return true;
        }
        return insert(root, pokemon);
    }

    private boolean insert(Node node, Pokemon pokemon) {
        int comparison = pokemon.getName().compareTo(node.pokemon.getName());
        if (comparison == 0) {
            return false; // Já existe na árvore
        } else if (comparison < 0) {
            if (node.left == null) {
                node.left = new Node(pokemon);
                return true;
            }
            return insert(node.left, pokemon);
        } else {
            if (node.right == null) {
                node.right = new Node(pokemon);
                return true;
            }
            return insert(node.right, pokemon);
        }
    }

    public String search(String name) {
        StringBuilder path = new StringBuilder();
        boolean found = search(root, name, path);
        return name + "\n=>" + path.toString() + (found ? " SIM" : " NAO");
    }

    private boolean search(Node node, String name, StringBuilder path) {
        if (node == null) {
            return false;
        }

        // Adicionar o caminho atual
        if (path.length() > 0) {
            path.append(" ");
        }
        path.append(node == root ? "raiz" : (name.compareTo(node.pokemon.getName()) < 0 ? "esq" : "dir"));

        int comparison = name.compareTo(node.pokemon.getName());

        if (comparison == 0) {
            // Encontrado
            return true;
        } else if (comparison < 0) {
            // Continuar para a esquerda
            return search(node.left, name, path);
        } else {
            // Continuar para a direita
            return search(node.right, name, path);
        }
    }
}

public class TP04Q01 {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        String csvPath = "/tmp/pokemon.csv";
        ArrayList<Pokemon> pokedex = new ArrayList<>();
        BinarySearchTree bst = new BinarySearchTree();

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

        // Inserções na Árvore Binária
        try (Scanner sc = new Scanner(System.in)) {
            // Inserções na Árvore Binária
            String idStr = sc.nextLine();
            while (!idStr.equals("FIM")) {
                int id = Integer.parseInt(idStr);
                Pokemon p = pokedex.get(id - 1);
                if (p != null) {
                    bst.insert(p);
                }
                idStr = sc.nextLine();
            }

            // Pesquisas na Árvore Binária
            String searchName = sc.nextLine();
            while (!searchName.equals("FIM")) {
                System.out.println(bst.search(searchName));
                searchName = sc.nextLine();
            }
        }
    }
}
