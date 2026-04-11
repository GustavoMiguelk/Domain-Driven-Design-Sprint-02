package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Instituicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstituicaoDao {

    Connection minhaConexao;

    public InstituicaoDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Insert / Inserir
    public String inserir(Instituicao instituicao) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_FIAP_INSTITUICOES (nome, cnpj) VALUES (?,?)"
        );

        stmt.setString(1, instituicao.getNome());
        stmt.setString(2, instituicao.getCnpj());

        stmt.execute();
        stmt.close();

        return "Cadastrado com sucesso!";
    }
}