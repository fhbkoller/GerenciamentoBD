package br.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.dao.ProdutoDAO;
import br.modelo.Produto;

@ManagedBean
@RequestScoped
public class ProdutoMB implements Serializable{

	private static final long serialVersionUID = -235160517842536749L;
	private Produto produto;
	private List<Produto> listaProduto;
	private ProdutoDAO produtoDAO;

	@PostConstruct
	public void iniciaProduto() {
		produtoDAO = new ProdutoDAO();
		produto = new Produto();
		listaProduto = new ArrayList<Produto>();
		listaProduto = produtoDAO.buscarTodos();
	}

	public String salvar() {
		if (produto.getIdProduto() == 0) {
			produtoDAO.insere(produto);
		} else {
			produtoDAO.atualizar(produto);
		}
		criaProduto();
		listaProduto = produtoDAO.buscarTodos();
		return "";
	}

	public void criaProduto() {
		produto = new Produto();
	}

	public String excluir() {
		produtoDAO.excluir(produto.getIdProduto());
		criaProduto();
		listaProduto = produtoDAO.buscarTodos();
		return "";
	}

	public String pesquisar() {
		listaProduto = produtoDAO.buscarPorDescricao(produto.getDescricao());
		return "";
	}

	public String editarProduto() {
		return "/cadastroProduto.jsf";
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

}
