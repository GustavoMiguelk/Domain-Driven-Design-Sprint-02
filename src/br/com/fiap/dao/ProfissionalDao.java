package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Profissional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfissionalDao {

    Connection minhaConexao;

    public ProfissionalDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Insert / Inserir
    public String inserir(Profissional profissional) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_FIAP_PROFISSIONAIS VALUES (?,?,?,?)"
        );

        stmt.setString(1, profissional.getNome());
        stmt.setString(2, profissional.getCpf());
        stmt.setString(3, profissional.getCro());
        stmt.setString(4, profissional.getEspecializacao());

        stmt.execute();
        stmt.close();

        return "Cadastrado com sucesso!";
    }
}