public class Local {

    private String cnpjInstituicao;
    private String nome;
    private String endereco;
    private String tipo;

    public Local(String cnpjInstituicao, String nome, String endereco, String tipo) {
        this.cnpjInstituicao = cnpjInstituicao;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public String getCnpjInstituicao() {
        return cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao) {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
