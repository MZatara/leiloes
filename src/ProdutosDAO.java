
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
           private final String url = "jdbc:mysql://localhost:3306/uc11?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private final String usuario = "root";
    private final String senha = "michael1993";
    vendasVIEW vw;


    public static final List<ProdutosDTO> dados = new ArrayList<>();

    public static List<ProdutosDTO> Listar() {
        return dados;
    }

      public List<ProdutosDTO> listarProdutosVendidos() {
        List<ProdutosDTO> produtosVendidos = new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "michael1993");
            String query = "SELECT * FROM produtos WHERE STATUS = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, "Vendido");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                int valor = resultSet.getInt("VALOR");
                String status = resultSet.getString("STATUS");

                ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);
                produtosVendidos.add(produto);
            }

            resultSet.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return produtosVendidos;
    }
    
     
}
     

    


    
  

