public class Producto {
    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private float precio;

    public Producto(int id, String nombre, String categoria, int cantidad, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto: " +
                " | id: " + id +
                " | nombre: " + nombre +
                " | categoria: " + categoria  +
                " | cantidad: " + cantidad +
                " | precio: " + precio;
    }
}
