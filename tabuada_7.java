import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LerTabuadaDoArquivo {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Digite o número da tabuada: ");
        int num = input.nextInt();

        String nomeArquivo = "tabuada_" + num + ".txt";
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("O arquivo " + nomeArquivo + " não existe!");
        } else {
            try {
                Scanner leitorArquivo = new Scanner(arquivo);

                System.out.println("Conteúdo do arquivo " + nomeArquivo + ":");
                System.out.println("------------------------------------------");

                while (leitorArquivo.hasNextLine()) {
                    String linha = leitorArquivo.nextLine();
                    System.out.println(linha);
                }

                leitorArquivo.close();

            } catch (FileNotFoundException e) {
                System.out.println("Erro ao ler o arquivo.");
            }
        }

        input.close();
    }
}
