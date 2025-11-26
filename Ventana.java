import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JList listaProductos;
    private JSpinner spnIdEditar;
    private JButton btnBuscarEdit;
    private JButton btnEditar;
    private JTextField txtNombreEditar;
    private JComboBox cmbCategoriaEditar;
    private JTextField txtPrecioEditar;
    private JSpinner spnCantidadEditar;
    private JTabbedPane tabbedPane3;
    private JComboBox cmbOrdenar;
    private JButton btnMostrarOrden;
    private JList listarOrdenar;
    private JButton btnOrdenar;
    private JTextField txtNombreBuscar;
    private JButton btnBuscar;
    private JTextArea txtInfoBuscado;
    private JList listaAlmacenados;
    private JTextField txtIdEditar;
    private JTextArea txtInfoOrdenar;
    InventarioProductos inventario = new InventarioProductos();

    public void llenarLista(){
        DefaultListModel lsm = new DefaultListModel();
        for (Producto producto: inventario.getProductos()){
            lsm.addElement(producto);
        }
        listaAlmacenados.setModel(lsm);
        listaProductos.setModel(lsm);

    }

    public Ventana(){
        llenarLista();
        SpinnerNumberModel spn1 = new SpinnerNumberModel(1, 1, 100000, 1);
        spnIdEditar.setModel(spn1);
        txtIdEditar.setEditable(false);
        txtInfoBuscado.setEditable(false);


        btnBuscarEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(spnIdEditar.getValue().toString());
                if (inventario.buscarPorCodigo(id)!= null){
                    JOptionPane.showMessageDialog(null, "Exito se lo encontro, mira la seccion de editar");
                    Producto producto = inventario.buscarPorCodigo(id);
                    txtIdEditar.setText(""+producto.getId());
                    txtNombreEditar.setText(producto.getNombre());
                    cmbCategoriaEditar.setSelectedItem(producto.getCategoria());
                    spnCantidadEditar.setValue(producto.getCantidad());
                    txtPrecioEditar.setText(""+producto.getPrecio());

                }else {
                    JOptionPane.showMessageDialog(null, "Error, no se encontro ese producto");
                }
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(spnIdEditar.getValue().toString());

                if (inventario.buscarPorCodigo(id) == null) {
                    JOptionPane.showMessageDialog(null, "Error, no se encontró un producto con ese ID");
                    return;
                }

                String nombre = txtNombreEditar.getText().trim();
                String categoria = cmbCategoriaEditar.getSelectedItem().toString();
                String textoPrecio = txtPrecioEditar.getText().trim();

                if (nombre.isEmpty() || categoria.isEmpty() || textoPrecio.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes");
                    return;
                }

                float precio;
                try {
                    precio = Float.parseFloat(textoPrecio);
                    if (precio < 0) {
                        JOptionPane.showMessageDialog(null, "El precio no puede ser negativo");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de precio inválido");
                    return;
                }

                int cantidad = Integer.parseInt(spnCantidadEditar.getValue().toString());

                Producto producto = new Producto(id, nombre, categoria, cantidad, precio);

                int indice = inventario.devolverIndex(id);
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Error, no se encontró el producto");
                    return;
                }

                inventario.getProductos().set(indice, producto);
                JOptionPane.showMessageDialog(null, "Se lo editó con éxito");
                llenarLista();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inventario.getProductos().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, no hay datos ingresados");
                }else {
                    String nombre = txtNombreBuscar.getText();
                    if (nombre.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Error, datos faltantes o erroneos");
                    }else {
                        if (inventario.buscarPorNombre(nombre) != null) {
                            JOptionPane.showMessageDialog(null, "Exito se lo encontro");
                            Producto producto = inventario.buscarPorNombre(nombre);
                            txtInfoBuscado.setText(producto.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Error, no se encontro dicho producto");
                        }
                    }
                }
            }
        });


        btnMostrarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String categoria = cmbOrdenar.getSelectedItem().toString();

                if (categoria.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes");
                    return;
                }

                String resultado = inventario.productos(categoria);

                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error, no hay productos de esa categoria");
                } else {
                    txtInfoOrdenar.setText(resultado);
                }
            }
        });


        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = cmbOrdenar.getSelectedItem().toString();
                if (categoria.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes");
                }else {
                    if (inventario.productos(categoria).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error, no hay productos de esa categoria ");
                    } else {
                        inventario.productos(categoria);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
