package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalDao {

    Connection minhaConexao;

    public LocalDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Insert / Inserir
    public String inserir(Local local) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_FIAP_LOCAIS (cnpj_inst, nome, endereco, tipo) VALUES (?,?,?,?)"
        );

        stmt.setString(1, local.getCnpjInstituicao());
        stmt.setString(2, local.getNome());
        stmt.setString(3, local.getEndereco());
        stmt.setString(4, local.getTipo());

        stmt.execute();
        stmt.close();

        return "Cadastrado com sucesso!";
    }
}