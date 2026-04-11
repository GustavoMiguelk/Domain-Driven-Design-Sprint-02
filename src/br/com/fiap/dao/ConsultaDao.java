package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultaDao {

    Connection minhaConexao;

    public ConsultaDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Consulta consulta) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_FIAP_CONSULTAS (codigo_consulta, cpf_prof, cpf_benef, id_local, tipo, descricao) VALUES (?,?,?,?,?,?)"
        );

        stmt.setString(1, consulta.getCodigoConsulta());
        stmt.setString(2, consulta.getProfissional().getCpf());    // CPF do profissional, não o nome
        stmt.setString(3, consulta.getBeneficiario().getCpf());    // CPF do beneficiário, não o nome
        stmt.setInt(4,    consulta.getLocal().getIdLocal());        // ID do local, não o nome
        stmt.setString(5, consulta.getTipo());
        stmt.setString(6, consulta.getDescricao());

        stmt.execute();
        stmt.close();

        return "Cadastrado com sucesso!";
    }
}