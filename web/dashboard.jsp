<%@page import="com.burgerparadise.model.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Burger Paradise - Enterprise Framework</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f3f4f6;
                margin: 40px;
            }
            .card {
                background: white;
                max-width: 850px;
                margin: auto;
                padding: 25px;
                border-radius: 12px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            }
            h1 {
                color: #1e3a8a;
                border-bottom: 3px solid #3b82f6;
                padding-bottom: 10px;
                font-size: 24px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: block;
                font-weight: 600;
                margin-bottom: 5px;
                color: #4b5563;
            }
            input[type="text"], input[type="number"] {
                width: 97%;
                padding: 10px;
                border: 1px solid #d1d5db;
                border-radius: 6px;
            }
            input[type="submit"] {
                background-color: #2563eb;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-weight: bold;
                width: 100%;
            }
            input[type="submit"]:hover {
                background-color: #1d4ed8;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 25px;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #e5e7eb;
            }
            th {
                background-color: #3b82f6;
                color: white;
            }
            .badge-success {
                background-color: #10b981;
                color: white;
                padding: 10px;
                border-radius: 6px;
                margin-bottom: 20px;
                font-weight: bold;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <h1>🍔 Burger Paradise - Módulo Web con Framework MVC</h1>

            <% if (request.getAttribute("feedback") != null) {%>
            <div class="badge-success"><%= request.getAttribute("feedback")%></div>
            <% } %>

            <form action="main" method="POST">
                <div class="form-group">
                    <label>Nombre de la Hamburguesa / Combo:</label>
                    <input type="text" name="nombreProducto" required />
                </div>
                <div class="form-group">
                    <label>Precio Unitario ($):</label>
                    <input type="number" step="0.01" name="precioProducto" required />
                </div>
                <div class="form-group">
                    <label>Cantidad en Stock:</label>
                    <input type="number" name="stockProducto" required />
                </div>
                <input type="submit" value="Registrar mediante Controlador MVC" />
            </form>

            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descripción</th>
                        <th>Precio Web</th>
                        <th>Inventario Disponible</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Producto> lista = (List<Producto>) request.getAttribute("inventario");
                        if (lista != null && !lista.isEmpty()) {
                            for (Producto p : lista) {
                    %>
                    <tr>
                        <td><strong>#<%= p.getIdProducto()%></strong></td>
                        <td><%= p.getNombre()%></td>
                        <td>$<%= p.getPrecio()%></td>
                        <td><%= p.getStock()%> unidades</td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" style="text-align: center; color: #6b7280;">
                            Presione <a href="main">aquí</a> para sincronizar el almacenamiento de datos local.
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </body>
</html>
