package com.burgerparadise.controller;

import com.burgerparadise.model.Producto;
import com.burgerparadise.repository.ProductoRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CRITERIO 1: Framework arquitectónico Front Controller MVC. Centraliza las
 * peticiones de la aplicación web.
 */
@WebServlet(name = "FrontController", urlPatterns = {"/main"})
public class FrontController extends HttpServlet {

    private final ProductoRepository repo = new ProductoRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Adjuntamos la lista de almacenamiento al alcance de la petición
        request.setAttribute("inventario", repo.findAll());
        // Despachamos el control hacia la vista JSP
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Captura y mapeo de parámetros de entrada
        String nombre = request.getParameter("nombreProducto");
        double precio = Double.parseDouble(request.getParameter("precioProducto"));
        int stock = Integer.parseInt(request.getParameter("stockProducto"));

        // Instanciación del modelo aplicando buenas prácticas
        Producto nuevo = new Producto(0, nombre, precio, stock);

        // Almacenamiento persistente
        if (repo.save(nuevo)) {
            request.setAttribute("feedback", "¡Éxito! Registrado bajo Framework MVC.");
        } else {
            request.setAttribute("feedback", "Error al procesar la solicitud.");
        }

        // Redirección interna
        doGet(request, response);
    }
}
