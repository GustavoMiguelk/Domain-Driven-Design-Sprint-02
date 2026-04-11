package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Responsavel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResponsavelDao {

    Connection minhaConexao;

    public ResponsavelDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Responsavel responsavel) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_FIAP_RESPONSAVEIS (cpf, nome, contato) VALUES (?,?,?)"
        );

        stmt.setString(1, responsavel.getCpf());
        stmt.setString(2, responsavel.getNome());
        stmt.setString(3, responsavel.getContato());

        stmt.execute();
        stmt.close();

        return "Cadastrado com sucesso!";
    }
}