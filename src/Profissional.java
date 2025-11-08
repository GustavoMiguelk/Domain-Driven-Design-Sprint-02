 class Profissional {

    private String nome;
    private String cpf;
    private String cro;
    private String especializacao;
    private Agenda agenda;

    public Profissional(String nome, String cpf, String cro,String especializacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.cro = cro;
        this.especializacao = especializacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCro(){
        return cro;
    }

    public void setCro(String cro){
        this.cro = cro;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
