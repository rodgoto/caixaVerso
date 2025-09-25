public class ItemVenda {
    private int codigo;
    private Produto produto;
    private int quantidade;
    private double valor;
    private double valorVenda;

    public ItemVenda(Produto produto, int quantidade, double valor) {
        this.codigo = codigo + 1;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorVenda = valor * 1.1;
    }

    public double getSubtotal() {
        return valorVenda * quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Item " + produto.getCodigo() +
                " - " + produto.getNome() +
                " - Quantidade: " + quantidade +
                ", Preço origem: R$ " + valor +
                ", Preço venda: R$ " + valorVenda +
                ", Subtotal: R$ " + getSubtotal();
    }
}
