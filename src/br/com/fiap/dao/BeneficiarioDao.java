package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Beneficiario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BeneficiarioDao {

    Connection minhaConexao;

    public BeneficiarioDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Beneficiario beneficiario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_FIAP_BENEFICIARIOS (cpf, nome, idade, sexo, cpf_resp, id_programa) VALUES (?,?,?,?,?,?)"
        );

        stmt.setString(1, beneficiario.getCpf());
        stmt.setString(2, beneficiario.getNome());
        stmt.setInt(3,    beneficiario.getIdade());
        stmt.setString(4, beneficiario.getSexo());
        stmt.setString(5, beneficiario.getResponsavel().getCpf());
        stmt.setInt(6,    beneficiario.getPrograma().getIdPrograma());

        stmt.execute();
        stmt.close();

        return "Cadastrado com sucesso!";
    }
}