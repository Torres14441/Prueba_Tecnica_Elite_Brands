package prueba_tecnica.Controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import prueba_tecnica.Service.ProductoService;
import org.springframework.ui.Model;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@Slf4j
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String index(Model model){
        var productos = productoService.ListarProductos();
        System.out.println(productos.size() + " productos encontrados");
        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("productos", productos);
        return "Index";
    }

    @GetMapping("/exportar/excel")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        productoService.exportarProductosAExcel(response);
    }
}
