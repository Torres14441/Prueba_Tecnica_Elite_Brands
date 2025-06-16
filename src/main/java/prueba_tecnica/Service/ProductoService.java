package prueba_tecnica.Service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba_tecnica.Bean.Producto;
import prueba_tecnica.DAO.ProductoDAO;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ProductoService {

    @Autowired
    private ProductoDAO productoDAO;

    public List<Producto> ListarProductos(){
        return productoDAO.findAll();
    }

    public void exportarProductosAExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");

        List<Producto> productos = ListarProductos();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Productos");
            String[] columnas = {"ID", "Descripción", "Costo", "Precio", "Existencia"};

            //Ajustar tamaño de columnas
            sheet.setColumnWidth(0, 10 * 256); // ID
            sheet.setColumnWidth(1, 30 * 256); // Descripción
            sheet.setColumnWidth(2, 15 * 256); // Costo
            sheet.setColumnWidth(3, 15 * 256); // Precio
            sheet.setColumnWidth(4, 15 * 256); // Existencia

            // Encabezados
            Row header = sheet.createRow(0);
            for (int i = 0; i < columnas.length; i++) {
                header.createCell(i).setCellValue(columnas[i]);
            }

            // Datos
            int filaNum = 1;
            for (Producto p : productos) {
                Row fila = sheet.createRow(filaNum++);
                fila.createCell(0).setCellValue(p.getId());
                fila.createCell(1).setCellValue(p.getDescripcion());
                fila.createCell(2).setCellValue(p.getCosto());
                fila.createCell(3).setCellValue(p.getPrecio());
                fila.createCell(4).setCellValue(p.getExistencia());
            }

            // Ajuste automático de columnas
            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(response.getOutputStream());
        }
    }

}
