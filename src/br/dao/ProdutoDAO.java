package br.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.modelo.Produto;

public class ProdutoDAO extends BaseDAO {

	public void excluir(int idproduto) {
		conectar();
		try {
			comando.execute("delete from produto where idproduto = " + idproduto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> buscarTodos() {
		conectar();
		List<Produto> resultados = new ArrayList<Produto>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT produto.idproduto, produto.descricao, produto.valor, produto.quantidade"
					+ " FROM produto ");
			while (rs.next()) {
				Produto temp = new Produto();

				temp.setIdProduto(rs.getInt("idproduto"));
				temp.setDescricao(rs.getString("descricao"));
				temp.setValor(rs.getDouble("valor"));
				temp.setQuantidade(rs.getInt("quantidade"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Produto> buscarPorDescricao(String descricao) {
		conectar();
		List<Produto> produtos = new ArrayList<Produto>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT produto.idproduto, produto.descricao, produto.valor, produto.quantidade"
					+ " FROM produto" + " WHERE produto.descricao LIKE '%" + descricao +"%'");
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("idproduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void atualizar(Produto produto) {
		conectar();
		String com = "UPDATE produto p " + "set p.descricao = '" + produto.getDescricao() + "', p.valor = '"
				+ produto.getValor() + "', p.quantidade = '" + produto.getQuantidade() + "' WHERE p.idproduto = '"
				+ produto.getIdProduto() + "';";
		System.out.println("Atualizado!");
		try {
			comando.executeUpdate(com);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public void insere(Produto produto) {
		conectar();
		try {
			comando.executeUpdate(
					"INSERT INTO produto (descricao, valor, quantidade) VALUES ('" + produto.getDescricao() + "'," + "'"
							+ produto.getValor() + "'," + "'" + produto.getQuantidade() + "');");

			System.out.println("Inserido!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
}