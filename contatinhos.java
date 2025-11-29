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

    public static void main(String[] args) {
        Agenda ag = new Agenda();
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos contatinhos deseja cadastrar? ");
        int qtd = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= qtd; i++) {
            System.out.println("Cadastro do contatinho " + i);

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            System.out.print("Categoria: ");
            String categoria = sc.nextLine();

            ag.addContatinho(new Contatinho(nome, email, telefone, categoria));
        }

        ag.salvarLista();
        sc.close();
    }

}
