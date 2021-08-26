package obras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class BancoObras {

    private static Connection connection;
   
    public static Connection getConnection(){
        if(connection == null){
            try{
                //Class.forName("com.mysql.jdbc.Driver"); //para mysql
                Class.forName("org.postgresql.Driver");//para postgresql
                String host = "localhost";
                String port = "5432";
                String database = "postgres";
                String user = "postgres";
                String pass = "nha123";//digitar a senha do seu banco
                //String url = "jdbc:mysql://"+host+":"+port+"/"+database; //para mysql
                String url = "jdbc:postgresql://"+host+":"+port+"/"+database;//para postgresql
                connection = DriverManager.getConnection(url, user, pass);                
               
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void close(){
        if (connection == null){
            throw new RuntimeException("Nenhuma conexão aberta!");
        }
        else{
            try{
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void criar_tabela(String tabela, String... atributo){
        try{
    		Connection con = BancoObras.getConnection();
		
		String comando = "CREATE TABLE " + tabela + "(";

		for (String i : atributo){
			comando = comando + i + ",";
		}
		comando = comando.substring(0, comando.length()-1);
		comando = comando + ");";

                PreparedStatement ps = con.prepareStatement(comando);
        	ps.execute();
        	} catch (SQLException e) {
            	e.printStackTrace();
        }
    }
            
    public static void salvarArte(Arte arte){
        try{
            Connection con = BancoObras.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO arte (cod_arte, cod_local, nome_arte, data, autor, tipo) values(?, ?, ?, ?, ?, ?)");
            ps.setInt(1, arte.getCod_arte());
            ps.setInt(2, arte.getCod_local());
            ps.setString(3, arte.getNome_arte());
            ps.setString(4, arte.getData());
            ps.setString(5, arte.getAutor());
            ps.setString(6, arte.getTipo());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void salvarMuseu (Museu museu){
        try{
            Connection con = BancoObras.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO museu "
                    + "(cod_museu, nome_museu, endereco, telefone) values(?, ?, ?, ?)");
            ps.setInt(1, museu.getCod_museu());
            ps.setString(2, museu.getNome_museu());
            ps.setString(3, museu.getEndereco());
            ps.setString(4, museu.getTelefone());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void add_coluna(String tabela, String coluna, String tipo){
    try{Connection con = BancoObras.getConnection();
            PreparedStatement ps = con.prepareStatement("ALTER TABLE " + tabela + " ADD COLUMN " + coluna + tipo);
            ps.execute();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void visualiza_dados(String tabela, String... atributos){
    try{
            Connection con = BancoObras.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + tabela);
            ResultSet rs = ps.executeQuery();
            
            String resultado = "";
            while(rs.next()){
                for(String i : atributos){
                    resultado = resultado + " | " + rs.getString(i);
               }
                resultado = resultado + "\n";
            }
            System.out.println(resultado);
            
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
}
