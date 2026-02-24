import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private static final double DESCONTO = 0.25;
    private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String descricao, double precoCusto, double margemLucro, LocalDate validade) {
        super(descricao, precoCusto, margemLucro);
        
        if (validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O produto está vencido.");
        }
        
        this.dataDeValidade = validade;
    }

    @Override
    public double valorVenda() {
        double desconto = 0d;
        int diasValidade = LocalDate.now().until(dataDeValidade).getDays();

        if(diasValidade <= PRAZO_DESCONTO) {
            desconto = DESCONTO;
        }

        return (precoCusto * (1.0 + margemLucro)) * (1 - desconto);
    }

    @Override
    public String toString() {
        DateTimeFormater formato = DateTimeFormater.ofPattern("dd/MM/yyyy");

        String dados = super.toString();
        dados += "\n Válido até " + formato.format(dataDeValidade);
        return dados;
    }
}