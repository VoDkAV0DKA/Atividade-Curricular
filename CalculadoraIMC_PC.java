
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Year;

public class CalculadoraIMCSwing extends JFrame {
    private JTextField campoNome, campoAno, campoAltura, campoPeso;
    private JTextArea areaSaida;

    public CalculadoraIMCSwing() {
        super("Calculadora de IMC - Academia");

        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

        painelCampos.add(new JLabel("Nome do usuário:"));
        campoNome = new JTextField();
        painelCampos.add(campoNome);

        painelCampos.add(new JLabel("Ano de nascimento:"));
        campoAno = new JTextField();
        painelCampos.add(campoAno);

        painelCampos.add(new JLabel("Altura (m):"));
        campoAltura = new JTextField();
        painelCampos.add(campoAltura);

        painelCampos.add(new JLabel("Peso (kg):"));
        campoPeso = new JTextField();
        painelCampos.add(campoPeso);

        add(painelCampos, BorderLayout.NORTH);

        areaSaida = new JTextArea(10, 30);
        areaSaida.setEditable(false);
        add(new JScrollPane(areaSaida), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnLimpar);

        add(painelBotoes, BorderLayout.SOUTH);

        btnCalcular.addActionListener(e -> calcularIMC());

        btnLimpar.addActionListener(e -> {
            campoNome.setText("");
            campoAno.setText("");
            campoAltura.setText("");
            campoPeso.setText("");
            areaSaida.setText("");
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calcularIMC() {
        try {
            String nome = campoNome.getText();
            int anoNasc = Integer.parseInt(campoAno.getText());
            double altura = Double.parseDouble(campoAltura.getText());
            double peso = Double.parseDouble(campoPeso.getText());

            int idade = Year.now().getValue() - anoNasc;
            double imc = peso / (altura * altura);

            String classificacao;
            if (imc < 18.5) classificacao = "Abaixo do Peso";
            else if (imc < 24.9) classificacao = "Peso Normal";
            else if (imc < 29.9) classificacao = "Sobrepeso";
            else if (imc < 34.9) classificacao = "Obesidade I";
            else if (imc < 39.9) classificacao = "Obesidade II";
            else classificacao = "Obesidade III";

            areaSaida.setText(
                "Nome: " + nome + "\n" +
                "Idade: " + idade + " anos\n" +
                String.format("IMC: %.2f", imc) + "\n" +
                "Classificação: " + classificacao
            );

        } catch (Exception ex) {
            areaSaida.setText("Erro: Verifique os dados informados.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraIMCSwing());
    }
}
