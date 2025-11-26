import java.util.ArrayList;
import java.util.List;

public class InventarioProductos {
    private List<Producto> productos;
    private List<Producto> productosCategoria;

    public InventarioProductos() {
        this.productos = new ArrayList<Producto>();
        this.productosCategoria = new ArrayList<>();
        ordenarPorPrecio();
        predefinir();
    }
    public void  predefinir(){
        Producto producto1 = new Producto(12, "Pc", "Tecnologia", 30, 100.12f);
        Producto producto2 = new Producto(1, "Mouse", "Tecnologia", 10, 500.12f);
        Producto producto3 = new Producto(32, "Audifonos", "Tecnologia", 70, 200.12f);
        Producto producto4 = new Producto(23, "Celular", "Tecnologia", 40, 50.12f);
        Producto producto5 = new Producto(90, "Tablet", "Tecnologia", 90, 800.12f);
        Producto producto6 = new Producto(22, "Ventilador", "Electrodomestico", 40, 1000.12f);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);
        productos.add(producto5);
        productos.add(producto6);

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String productos(String categoria){
        productosCategoria.clear();

        for (Producto producto : productos) {
            if (categoria.equals(producto.getCategoria())) {
                productosCategoria.add(producto);
            }
        }

        if (productosCategoria.isEmpty()) {
            return "";
        }

        ordenarPorPrecio();

        StringBuilder sc = new StringBuilder();
        for (Producto p : productosCategoria) {
            sc.append(p.toString()).append("\n");
        }

        return sc.toString();
    }


    public int devolverIndex(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    public void ordenarPorPrecio(){
        Producto aux;
        for (int i = 0; i < productosCategoria.size() - 1; i ++){
            for (int j = i + 1 ; j < productosCategoria.size(); j++){
                if (productosCategoria.get(i).getPrecio() < productosCategoria.get(j).getPrecio()){
                    aux = productosCategoria.get(i);
                    productosCategoria.set(i, productosCategoria.get(j));
                    productosCategoria.set(j, aux);

                }
            }
        }
    }

    public Producto buscarPorNombre(String nombre){
        for (Producto producto: productos){
            if (nombre.equals(producto.getNombre())){
                return producto;
            }
        }
        return null;
    }

    public Producto buscarPorCodigo(int codigo){
        for (Producto producto: productos){
            if (codigo == producto.getId()){
                return producto;
            }
        }
        return null;
    }






}

