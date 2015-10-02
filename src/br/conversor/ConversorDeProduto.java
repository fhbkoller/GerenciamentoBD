package br.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.dao.ProdutoDAO;
import br.modelo.Produto;


@FacesConverter(value = "conversorDeProduto")
public class ConversorDeProduto implements Converter{
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		if (valor != null && !valor.equals("")){
			if(valor.equals("Selecione")) return null;
			ProdutoDAO dao = new ProdutoDAO();
			return dao.buscarPorDescricao(valor);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object valor) {
		if (valor != null){
			Produto produto = (Produto)valor;
			return produto.getDescricao();
		}
		return null;
	}
	
}

