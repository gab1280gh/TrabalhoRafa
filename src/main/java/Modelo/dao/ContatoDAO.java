package modelo.dao;

import modelo.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private final Connection conexao;

    public ContatoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Contato contato) throws SQLException {
        String insercao = "INSERT INTO contato (nome, endereco, telefone, email, fundos) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getEndereco());
            pstmt.setString(3, contato.getTelefone());
            pstmt.setString(4, contato.getEmail());
            pstmt.setDouble (5, contato.getFundos());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    contato.setIdContato(rs.getInt(1));
                }
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public Contato buscar(long idContato) throws SQLException {
        Contato contato = null;
        String selecao = "SELECT * FROM contato WHERE idContatos = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, idContato);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    contato = new Contato();
                    contato.setIdContato(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setEndereco(rs.getString(3));
                    contato.setTelefone(rs.getString(4));
                    contato.setEmail(rs.getString(5));
                    contato.setFundos(rs.getInt(6));
                }
            }
        }
        return contato;
    }

    public List<Contato> buscarTodos() throws SQLException {
        Contato contato;
        List<Contato> contatos = new ArrayList<>();
        String selecao = "SELECT * FROM contato";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    contato = new Contato();
                    contato.setIdContato(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setEndereco(rs.getString(3));
                    contato.setTelefone(rs.getString(4));
                    contato.setEmail(rs.getString(5));
                    contato.setFundos(rs.getInt(6));
                    contatos.add(contato);
                }
            }
        }
        return contatos;
    }

    public List<Contato> buscarNome(String nome) throws SQLException {
        Contato contato;
        List<Contato> contatos = new ArrayList<>();
        String selecao = "SELECT * FROM contato WHERE nome LIKE ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    contato = new Contato();
                    contato.setIdContato(rs.getInt(1));
                    contato.setNome(rs.getString(2));
                    contato.setEndereco(rs.getString(3));
                    contato.setTelefone(rs.getString(4));
                    contato.setEmail(rs.getString(5));
                    contato.setFundos(rs.getInt(6));
                    contatos.add(contato);
                }
            }
        }
        return contatos;
    }

    public void remover(Contato contato) throws SQLException {
        String remocao = "DELETE FROM contato WHERE idContatos = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(remocao)) {
            pstmt.setLong(1, contato.getIdContato());
            int remocoes = pstmt.executeUpdate();
            if (remocoes == 1) {
                System.out.println("Remoção efetuada com sucesso.");
            } else {
                System.out.println("Não foi possível efetuar a remoção.");
            }
        }
    }

    public void atualizar(Contato contato) throws SQLException {
        String alteracao = "UPDATE contato SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE idContatos = ?;";
        try (PreparedStatement pstmt = conexao.prepareStatement(alteracao)) {
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getEndereco());
            pstmt.setString(3, contato.getTelefone());
            pstmt.setString(4, contato.getEmail());
            pstmt.setDouble(5, contato.getFundos());
            pstmt.setLong(5, contato.getIdContato());
            int alteracoes = pstmt.executeUpdate();
            if (alteracoes == 1) {
                System.out.println("\nAlteracao bem sucedida.");
            } else {
                System.out.println("A alteracao não foi feita corretamente.");
            }
        }
    }
}
