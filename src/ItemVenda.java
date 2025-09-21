import java.math.BigDecimal;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private BigDecimal valor;
    private BigDecimal valorVenda;

    public ItemVenda(Produto produto, int quantidade, BigDecimal valor) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorVenda = valor.multiply(new BigDecimal("0.1"));
    }

    public BigDecimal getSubtotal() {
        return valorVenda.multiply(new BigDecimal(quantidade));
    }

    @Override
    public String toString() {
        return produto.getNome() + " - Quantidade: " + quantidade +
                ", Preço origem: R$ " + valor +
                ", Preço unitário: R$ " + valorVenda +
                ", Subtotal: R$ " + getSubtotal();
    }
}
