
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;


    public static final List<ProdutosDTO> dados = new ArrayList<>();

    public static List<ProdutosDTO> Listar() {
        return dados;
    }

    public List<ProdutosDTO> listagem(String filtro) {
        String sql = "select * from produtos";
        if (!filtro.isEmpty()) {
            sql = sql + " where nome like?";
        }
        try {
            st = conn.prepareStatement(sql);
            if (!filtro.isEmpty()) {
                st.setString(1, "%" + filtro + "%");
            }
            rs = st.executeQuery();
            List<ProdutosDTO> listaProdutos = new ArrayList<>();
            //verificar se a consulta encontrou o funcionário com a matrícula informada
            while (rs.next()) { // se encontrou o funcionário, vamos carregar os dados
                ProdutosDTO pdto = new ProdutosDTO();
                pdto.setId(rs.getInt("id"));
                pdto.setNome(rs.getString("nome"));
                pdto.setValor(rs.getInt("valor"));
                pdto.setStatus(rs.getString("status"));
                listaProdutos.add(pdto);

            }
            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }
    
    

    
  
}
