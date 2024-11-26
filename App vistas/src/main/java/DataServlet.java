
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Computadora
 */
public class DataServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Crear un JSONArray de ejemplo
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i <= 5; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", i);
            jsonObject.put("name", "Item " + i);
            jsonObject.put("value", i * 10);
            jsonArray.put(jsonObject);
        }

        out.print(jsonArray.toString());
        out.flush();
    }
    
}
