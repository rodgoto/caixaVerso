import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private static int proximoCodigo = 1;
    private int codigo;
    private Cliente cliente;
    private LocalDateTime dataHora;
    private String status;
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemVenda adicionarItem(Produto produto, int quantidade, double precoUnitario) {
        ItemVenda item = new ItemVenda(produto, quantidade, precoUnitario);
        itens.add(item);
        return item;
    }

    public Venda(Cliente cliente, LocalDateTime dataHora, String status) {
        this.codigo = proximoCodigo++;
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

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void exibirResumo() {
        System.out.println("Pedido para o cliente: " + cliente.getNome());
        double total = 0;
        for (ItemVenda item : itens) {
            System.out.println(item);
            total = total + item.getSubtotal();
        }
        System.out.println("Total do pedido: R$ " + total);
        System.out.println("Status do pedido: " + status);
    }
}
