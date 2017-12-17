package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Contato;
import modelo.dao.ContatoDAO;
import modelo.dao.DAOFactory;

/**
 *
 * @author aluno
 */
public class MeuServletinho extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String caminho = request.getServletPath();        
        if (caminho.equalsIgnoreCase("/Contatos/Todos")) {
            try {
                DAOFactory fabrica = new DAOFactory();
                fabrica.abrirConexao();
                ContatoDAO dao = fabrica.criarContatoDAO();
                List<Contato> contatos = dao.buscarTodos();
                fabrica.fecharConexao();
                request.setAttribute("contatos", contatos);
                RequestDispatcher rd = request.getRequestDispatcher("/mostrarcontatos.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
            
        }else
             if (caminho.equalsIgnoreCase("/Contatos/Buscar")) {
            try {
                DAOFactory fabrica = new DAOFactory();
                fabrica.abrirConexao();
                ContatoDAO dao = fabrica.criarContatoDAO();
                RequestDispatcher rd = request.getRequestDispatcher("/mostrarcontatosid.jsp");
                long id = Long.parseLong(request.getParameter("id"));
                Contato contatos = dao.buscar(id);
                fabrica.fecharConexao();
                request.getSession().setAttribute("contatos", contatos);
                  
                rd.forward(request, response);
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
            
        }else
                 if (caminho.equalsIgnoreCase("/Contatos/Inserir")) {
            try {
                DAOFactory fabrica = new DAOFactory();
                fabrica.abrirConexao();
                ContatoDAO dao = fabrica.criarContatoDAO();
                RequestDispatcher rd = request.getRequestDispatcher("/mensagem.jsp");
                //long id = Long.parseLong(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String end = request.getParameter("end");
                String tel = request.getParameter("tel");
                String mail = request.getParameter("mail");
                double fundos = Double.parseDouble(request.getParameter("fundos"));
                Contato contatos = new Contato();
                contatos.setNome(nome);
                contatos.setEndereco(end);
                contatos.setTelefone(tel);
                contatos.setEmail(mail);
                contatos.setFundos(fundos);
                
                dao.gravar(contatos);
                String mensagem = "Contato Inserido com sucesso";
                fabrica.fecharConexao();
                request.setAttribute("mensagem", mensagem);                
                rd.forward(request, response);
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
            
        }else
             if (caminho.equalsIgnoreCase("/Contatos/Remover")) {
            try {
                DAOFactory fabrica = new DAOFactory();
                fabrica.abrirConexao();
                ContatoDAO dao = fabrica.criarContatoDAO();
                Contato remove = (Contato) request.getSession().getAttribute("contatos");
                dao.remover(remove);
                RequestDispatcher rd = request.getRequestDispatcher("/mensagem.jsp");
                String mensagem = "Contato Removido com sucesso";
                fabrica.fecharConexao();
                request.setAttribute("mensgaem", mensagem);                
                rd.forward(request, response);
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
             }else
                 if (caminho.equalsIgnoreCase("/Contatos/Alterar")) {
            try {
                DAOFactory fabrica = new DAOFactory();
                fabrica.abrirConexao();
                ContatoDAO dao = fabrica.criarContatoDAO();
                Contato altera = (Contato) request.getSession().getAttribute("contatos");
                RequestDispatcher rd = request.getRequestDispatcher("/mensagem.jsp");
                //long id = Long.parseLong(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String end = request.getParameter("end");
                String tel = request.getParameter("tel");
                String mail = request.getParameter("mail");
                int fundos = Integer.parseInt(request.getParameter("mail"));
               
                altera.setNome(nome);
                altera.setEndereco(end);
                altera.setTelefone(tel);
                altera.setEmail(mail);               
                dao.atualizar(altera);
                
                String mensagem = "Contato Alterado com sucesso";
                fabrica.fecharConexao();
                request.setAttribute("mensagem", mensagem);                
                rd.forward(request, response);
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
           }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
