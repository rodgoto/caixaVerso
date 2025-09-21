import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int codigo;
    private Cliente cliente;
    private LocalDateTime dataHora;
    private String status;
    private List<ItemVenda> itens;

    public Venda(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto, int quantidade, BigDecimal precoUnitario) {
        itens.add(new ItemVenda(produto, quantidade, precoUnitario));
    }

    public Venda(Cliente cliente, LocalDateTime dataHora, String status) {
        this.codigo = codigo + 1;
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void exibirResumo() {
        System.out.println("Pedido para o cliente: " + cliente.getNome());
        BigDecimal total = null;
        for (ItemVenda item : itens) {
            System.out.println(item);
            total = total.add(item.getSubtotal());
        }
        System.out.println("Total do pedido: R$ " + total);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "codigo=" + codigo +
                ", cliente=" + cliente +
                ", dataHora=" + dataHora +
                ", status='" + status + '\'' +
                ", itens=" + itens +
                '}';
    }
}
