public class Cliente {
    private int codigo;
    private String nome;
    private String documento;

    public Cliente(int codigo, String nome, String documento) {
        this.codigo = codigo;
        this.nome = nome;
        this.documento = documento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                '}';
    }
}
