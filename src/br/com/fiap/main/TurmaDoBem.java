package br.com.fiap.main;

import br.com.fiap.dao.*;
import br.com.fiap.entities.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;

public class TurmaDoBem {

    static String texto(String j) { return JOptionPane.showInputDialog(j); }
    static int inteiro(String j) { return Integer.parseInt(JOptionPane.showInputDialog(null, j)); }
    static void mensagem(String j) { JOptionPane.showMessageDialog(null, j); }

    public static void main(String[] args) {

        ArrayList<Instituicao> instituicoes = new ArrayList<>();
        ArrayList<Local> locais = new ArrayList<>();
        ArrayList<Programa> programas = new ArrayList<>();
        ArrayList<Beneficiario> beneficiarios = new ArrayList<>();
        ArrayList<Profissional> profissionais = new ArrayList<>();
        ArrayList<Consulta> consultas = new ArrayList<>();
        ArrayList<Responsavel> responsaveis = new ArrayList<>();

        programas.add(new Programa("Dentista do Bem", "Atendimento para jovens carentes menores de idade"));
        programas.add(new Programa("Apolônias do Bem", "Atendimento para mulheres vitimas de violência"));

        while (true) {
            int opcao = inteiro("""
                ===== Sistema de Agendamento Turma do Bem =====
                1 - Cadastrar Instituição
                2 - Cadastrar Local
                3 - Cadastrar Beneficiário
                4 - Cadastrar Profissional
                5 - Agendar Consulta
                6 - Listar Consultas
                0 - Sair
                Escolha uma opção:""");

            switch (opcao) {
                case 1:
                    Instituicao instituicao = new Instituicao(
                            texto("Nome da Instituição:"),
                            texto("CNPJ da Instituição:")
                    );

                    try {
                        InstituicaoDao instituicaoDao = new InstituicaoDao();
                        mensagem(instituicaoDao.inserir(instituicao));
                        instituicoes.add(instituicao);
                    } catch (Exception e) {
                        mensagem("Erro ao salvar instituição: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (instituicoes.isEmpty()) { mensagem("Cadastre uma instituição antes de continuar!"); break; }

                    StringBuilder listaInst = new StringBuilder();
                    for (int i = 0; i < instituicoes.size(); i++)
                        listaInst.append(i).append(" - ").append(instituicoes.get(i).getNome()).append("\n");

                    Local local = new Local(
                            instituicoes.get(inteiro("Selecione a instituição:\n" + listaInst)).getCnpj(),
                            texto("Nome do Local:"),
                            texto("Endereço:"),
                            texto("Tipo:")
                    );

                    try {
                        LocalDao localDao = new LocalDao();
                        mensagem(localDao.inserir(local));
                        locais.add(local);
                    } catch (Exception e) {
                        mensagem("Erro ao salvar local: " + e.getMessage());
                    }
                    break;

                case 3:
                    String nomeBen = texto("Nome do Beneficiário:");
                    int idade      = inteiro("Idade do Beneficiário:");
                    String cpfBen  = texto("CPF do Beneficiário:");
                    String sexo    = texto("Sexo (masculino/feminino):").toLowerCase();

                    StringBuilder listaPgm = new StringBuilder();
                    for (int i = 0; i < programas.size(); i++)
                        listaPgm.append(i).append(" - ").append(programas.get(i).getNome()).append("\n");
                    int idPgm = inteiro("Selecione o programa:\n" + listaPgm);

                    if (idPgm == 0 && idade >= 18) { mensagem("Somente menores de idade podem participar desse programa."); break; }
                    if (idPgm == 1 && sexo.equals("masculino")) { mensagem("Somente mulheres podem participar desse programa."); break; }

                    Responsavel resp;
                    if (idade < 18) {
                        mensagem("Insira os dados do responsável:");
                        resp = new Responsavel(texto("Nome do Responsável:"), texto("CPF do Responsável:"), texto("Contato:"));
                    } else {
                        resp = new Responsavel(nomeBen, cpfBen, texto("Contato:"));
                    }

                    Beneficiario beneficiario = new Beneficiario(nomeBen, idade, cpfBen, sexo, resp, programas.get(idPgm));

                    try {
                        // Salva responsável primeiro (por causa da FK)
                        ResponsavelDao responsavelDao = new ResponsavelDao();
                        responsavelDao.inserir(resp);
                        responsaveis.add(resp);

                        BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
                        mensagem(beneficiarioDao.inserir(beneficiario));
                        beneficiarios.add(beneficiario);
                    } catch (Exception e) {
                        mensagem("Erro ao salvar beneficiário: " + e.getMessage());
                    }
                    break;

                case 4:
                    Profissional profissional = new Profissional(
                            texto("Nome do Profissional:"),
                            texto("CPF do Profissional:"),
                            texto("CRO do Profissional:"),
                            texto("Especialização:")
                    );

                    try {
                        ProfissionalDao profissionalDao = new ProfissionalDao();
                        mensagem(profissionalDao.inserir(profissional));
                        profissionais.add(profissional);
                    } catch (Exception e) {
                        mensagem("Erro ao salvar profissional: " + e.getMessage());
                    }
                    break;

                case 5:
                    if (profissionais.isEmpty() || beneficiarios.isEmpty() || locais.isEmpty()) {
                        mensagem("Cadastre Profissional, Beneficiário e Local antes de agendar.");
                        break;
                    }

                    String cpfBusca = texto("CPF do Beneficiário:");
                    int idBenef = -1;
                    for (int i = 0; i < beneficiarios.size(); i++)
                        if (cpfBusca.equals(beneficiarios.get(i).getCpf())) { idBenef = i; break; }

                    if (idBenef == -1) { mensagem("Beneficiário não encontrado!"); break; }

                    StringBuilder listaProf = new StringBuilder();
                    for (int i = 0; i < profissionais.size(); i++)
                        listaProf.append(i).append(" - ").append(profissionais.get(i).getNome()).append("\n");
                    int idProf = inteiro("Escolha o profissional:\n" + listaProf);

                    StringBuilder listaLocais = new StringBuilder();
                    for (int i = 0; i < locais.size(); i++)
                        listaLocais.append(i).append(" - ").append(locais.get(i).getNome()).append(" | ").append(locais.get(i).getEndereco()).append("\n");
                    int idLocal = inteiro("Escolha o local:\n" + listaLocais);

                    String codigo = UUID.randomUUID().toString();
                    Profissional prof = profissionais.get(idProf);
                    if (prof.getAgenda() == null) prof.setAgenda(new Agenda(prof));

                    Consulta consulta = new Consulta(
                            codigo, prof, beneficiarios.get(idBenef),
                            locais.get(idLocal), texto("Tipo da Consulta:"), texto("Descrição:")
                    );

                    try {
                        ConsultaDao consultaDao = new ConsultaDao();
                        mensagem(consultaDao.inserir(consulta));
                        consultas.add(consulta);
                        prof.getAgenda().adicionarConsulta(consulta);
                    } catch (Exception e) {
                        mensagem("Erro ao salvar consulta: " + e.getMessage());
                    }
                    break;

                case 6:
                    if (consultas.isEmpty()) { mensagem("Nenhuma consulta cadastrada."); break; }

                    StringBuilder lista = new StringBuilder("=== Consultas Turma do Bem ===\n\n");
                    for (Consulta c : consultas) {
                        lista.append("Código: ").append(c.getCodigoConsulta())
                                .append("\nProfissional: ").append(c.getProfissional().getNome())
                                .append("\nBeneficiário: ").append(c.getBeneficiario().getNome())
                                .append("\nLocal: ").append(c.getLocal().getNome()).append(" | ").append(c.getLocal().getEndereco())
                                .append("\nTipo: ").append(c.getTipo())
                                .append("\nDescrição: ").append(c.getDescricao())
                                .append("\n------------------------------------------\n");
                    }
                    mensagem(lista.toString());
                    break;

                case 0:
                    mensagem("Encerrando sistema...");
                    return;

                default:
                    mensagem("Opção inválida!");
            }
        }
    }
}