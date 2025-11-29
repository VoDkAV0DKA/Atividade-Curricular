import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Agenda {

    private static class Contatinho implements Comparable<Contatinho> {

        private String nome;
        private String email;
        private String telefone;
        private String categoria;

        public Contatinho(String nome, String email, String telefone, String categoria) {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.categoria = categoria;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public int compareTo(Contatinho outro) {
            return this.nome.compareToIgnoreCase(outro.getNome());
        }

        @Override
        public String toString() {
            return nome + "#" + email + "#" + telefone + "#" + categoria;
        }
    }

    private List<Contatinho> lista = new ArrayList<>();

    public void addContatinho(Contatinho c) {
        lista.add(c);
    }

    public void ordenarLista() {
        Collections.sort(lista);
    }

    public void salvarLista() {
        ordenarLista();
        try {
            FileWriter writer = new FileWriter("agenda.txt");
            for (Contatinho c : lista) {
                writer.write(c.toString() + "\n");
            }
            writer.close();
            System.out.println("Arquivo agenda.txt salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }

    public Contatinho buscarContatinho(String nomeProcurado) {

        try {
            Scanner leitor = new Scanner(new File("agenda.txt"));

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split("#");

                String nome = dados[0];
                String email = dados[1];
                String telefone = dados[2];
                String categoria = dados[3];

                if (nome.equalsIgnoreCase(nomeProcurado)) {
                    leitor.close();
                    return new Contatinho(nome, email, telefone, categoria);
                }
            }

            leitor.close();
        } catch (Exception e) {
            return null;
        }

        return null;
    }

}
