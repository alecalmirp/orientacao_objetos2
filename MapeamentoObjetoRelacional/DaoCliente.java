import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoCliente {
    private Connection conn;
    private Statement st;

    private void conectar(){
        try {
            this.conn=GerenciadorConexao.pegarConexao();
            st=conn.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void desconectar(){
        try {
            st.close();
            conn.close();            
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }

    public boolean inserir(Cliente c){
        boolean resultado=false;
        try {
            this.conectar();

            String comando="INSERT INTO tb_clientes VALUES(NULL,"+"'"+c.getNome()+"','"+c.getCpf()+"','"+c.getRg()+"',"+c.getEmail()+");";
            System.out.println(comando);
            st.executeUpdate(comando);
            resultado=true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }finally{
            this.desconectar();
        }
        return resultado;
    }

    public int alterar(Cliente c){
        int qtde=0;
        try {
            this.conectar();

            String comando="UPDATE tb_clientes SET nome='"+c.getNome()+"',cpf='"+c.getCpf()+"',rg='"+c.getRg()+"',email="+c.getEmail()+" WHERE codigo="+c.getCodigo()+";";
            System.out.println(comando);
            st.executeUpdate(comando);
            qtde=st.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Erro ao alterar registro: "+e.getMessage());
        }finally{
            this.desconectar();
        }
        return qtde;
    }

    public ArrayList<Cliente> buscarTodos(){
        ArrayList<Cliente> resultados = new ArrayList<Cliente>();
        try {
            this.conectar();
            ResultSet rs=st.executeQuery("SELECT * FROM tb_clientes ORDER BY nome;");
            while(rs.next()){
                Cliente c=new Cliente();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setRg(rs.getString("rg"));
                c.setEmail(rs.getString("email"));

                resultados.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }finally{
            this.desconectar();
        }
        return resultados;
    }

    public Cliente buscarUm(int cod){
        Cliente c=null;
        try {
            this.conectar();
            ResultSet rs=st.executeQuery("SELECT * FROM tb_clientes WHERE codigo="+cod);
                if(rs.next()){
                    c=new Cliente();
                    c.setCodigo(rs.getInt("codigo"));
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setRg(rs.getString("rg"));
                    c.setEmail(rs.getString("email"));
                }
                
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }finally{
            this.desconectar();
        }
        return c;
    }

    public int excluir(int cod){  
        int qtde=0;     
        try {
            this.conectar();

            String comando="DELETE FROM tb_clientes WHERE codigo="+cod+";";
            System.out.println(comando);
            st.executeUpdate(comando);
            qtde=st.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }finally{
            this.desconectar();
        } 
        return qtde;
    }
}
