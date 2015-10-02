package br.dao;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BaseDAO {
	
	protected Connection conexao;
	protected Statement comando;
	
	protected void conectar(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
			comando = (Statement) conexao.createStatement();
			System.out.println("Conectado!");
		} catch (java.sql.SQLException e) {  
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		} 	
	}
	
	protected void fechar() {  
		try {  
			comando.close();  
			conexao.close();  
			System.out.println("Conexão Fechada");  
		} catch (java.sql.SQLException e) {  
			e.printStackTrace();  
		}  
	}  

}
