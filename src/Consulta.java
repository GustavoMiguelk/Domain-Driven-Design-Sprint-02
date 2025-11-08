public class Consulta {

    private String codigoConsulta;
    private Profissional profissional;
    private Beneficiario beneficiario;
    private Local local;
    private String tipo;
    private String descricao;

    public Consulta(String codigoConsulta, Profissional profissional, Beneficiario beneficiario, Local local, String tipo, String descricao) {
        this.codigoConsulta = codigoConsulta;
        this.profissional = profissional;
        this.beneficiario = beneficiario;
        this.local = local;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getCodigoConsulta() {
        return codigoConsulta;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public Local getLocal() {
        return local;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }
}
