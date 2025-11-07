/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author Carlos Cabrera
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

// Import project classes (assumes this file is compiled with the project sources on classpath)
import DAO.ClienteDAO;
import DAO.MascotaDAO;
import DAO.VentaDAO;
import DTO.*;

public class MainFrame extends JFrame {
    private final MascotaDAO mascotaDAO = new MascotaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VentaDAO ventaDAO = new VentaDAO();
    private int idTotal = 0;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Table models
    private final DefaultTableModel mascotasModel = new DefaultTableModel(new String[]{"ID","Nombre","Raza","Edad","Tipo","Peso","Adoptado"},0) {
        @Override public boolean isCellEditable(int r,int c){return false;}
    };
    private final DefaultTableModel clientesModel = new DefaultTableModel(new String[]{"ID","Nombre","Dirección","Teléfono","#Mascotas"},0) {
        @Override public boolean isCellEditable(int r,int c){return false;}
    };
    private final DefaultTableModel ventasModel = new DefaultTableModel(new String[]{"Fecha","Valor","ClienteID","Vendedor","#Accesorios","#Mascotas"},0) {
        @Override public boolean isCellEditable(int r,int c){return false;}
    };

    public MainFrame(){
        super("Veterinaria - Interfaz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,700);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Mascotas", buildMascotasPanel());
        tabs.add("Clientes", buildClientesPanel());
        tabs.add("Ventas", buildVentasPanel());
        tabs.add("Consultas", buildConsultasPanel());
        tabs.add("Reportes", buildReportesPanel());

        add(tabs);

        refreshMascotasTable();
        refreshClientesTable();
        refreshVentasTable();
    }

    // ---------------- Panels ----------------
    private JPanel buildMascotasPanel(){
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));

        JTable tabla = new JTable(mascotasModel);
        JScrollPane sp = new JScrollPane(tabla);
        p.add(sp, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        JTextField tfNombre = new JTextField();
        JTextField tfRaza = new JTextField();
        JTextField tfEdad = new JTextField();
        JTextField tfTipo = new JTextField();
        JTextField tfPeso = new JTextField();
        JComboBox<String> cbGenero = new JComboBox<>(new String[]{"M","H","O"});
        JTextField tfFecha = new JTextField(dtf.format(LocalDate.now()));
        JTextField tfLugar = new JTextField();
        JTextField tfPrecio = new JTextField("0.0");
        JComboBox<String> cbEstado = new JComboBox<>(new String[]{"false","true"});

        form.add(new JLabel("Nombre:")); form.add(tfNombre);
        form.add(new JLabel("Raza:")); form.add(tfRaza);
        form.add(new JLabel("Edad (años):")); form.add(tfEdad);
        form.add(new JLabel("Tipo:")); form.add(tfTipo);
        form.add(new JLabel("Peso (kg):")); form.add(tfPeso);
        form.add(new JLabel("Género (M/H/O):")); form.add(cbGenero);
        form.add(new JLabel("Fecha ingreso (yyyy-MM-dd):")); form.add(tfFecha);
        form.add(new JLabel("Lugar origen:")); form.add(tfLugar);
        form.add(new JLabel("Precio:")); form.add(tfPrecio);
        form.add(new JLabel("Adoptado (true/false):")); form.add(cbEstado);

        JButton btnRegistrar = new JButton("Registrar Mascota");
        form.add(btnRegistrar);
        JButton btnEliminar = new JButton("Eliminar seleccionada");
        form.add(btnEliminar);

        p.add(form, BorderLayout.EAST);

        btnRegistrar.addActionListener(e -> {
            try{
                String nombre = tfNombre.getText().trim();
                String raza = tfRaza.getText().trim();
                int edad = Integer.parseInt(tfEdad.getText().trim());
                String tipo = tfTipo.getText().trim();
                String id = Integer.toString(idTotal++);
                double peso = Double.parseDouble(tfPeso.getText().trim());
                char genero = cbGenero.getSelectedItem().toString().charAt(0);
                String fechaIngreso = tfFecha.getText().trim();
                String lugar = tfLugar.getText().trim();
                double precio = Double.parseDouble(tfPrecio.getText().trim());
                boolean adoptado = Boolean.parseBoolean(cbEstado.getSelectedItem().toString());

                MascotaDTO m = new MascotaDTO(nombre, raza, edad, tipo, id, peso, fechaIngreso, lugar, genero, precio, adoptado);
                mascotaDAO.registrar(m);
                JOptionPane.showMessageDialog(this, "Mascota registrada. ID: " + id);
                refreshMascotasTable();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error al registrar mascota: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            int sel = tabla.getSelectedRow();
            if(sel==-1){ JOptionPane.showMessageDialog(this, "Seleccione una mascota en la tabla"); return; }
            String id = mascotasModel.getValueAt(sel,0).toString();
            mascotaDAO.eliminar(id);
            refreshMascotasTable();
        });

        return p;
    }

    private JPanel buildClientesPanel(){
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));

        JTable tabla = new JTable(clientesModel);
        JScrollPane sp = new JScrollPane(tabla);
        p.add(sp, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        JTextField tfId = new JTextField();
        JTextField tfNombre = new JTextField();
        JTextField tfDireccion = new JTextField();
        JTextField tfTelefono = new JTextField();

        form.add(new JLabel("ID (manual o cédula):")); form.add(tfId);
        form.add(new JLabel("Nombres:")); form.add(tfNombre);
        form.add(new JLabel("Dirección:")); form.add(tfDireccion);
        form.add(new JLabel("Teléfono:")); form.add(tfTelefono);

        JButton btnRegistrar = new JButton("Registrar Cliente");
        form.add(btnRegistrar);
        JButton btnAsociarMascota = new JButton("Asociar mascota a cliente (por IDs)");
        form.add(btnAsociarMascota);

        p.add(form, BorderLayout.EAST);

        btnRegistrar.addActionListener(e -> {
            try{
                String id = tfId.getText().trim();
                if(id.isEmpty()) id = UUID.randomUUID().toString();
                ClienteDTO c = new ClienteDTO();
                // set fields via setters
                c.setId(id);
                c.setNombre(tfNombre.getText().trim());
                c.setDireccion(tfDireccion.getText().trim());
                c.setTelefono(tfTelefono.getText().trim());
                clienteDAO.registrarCliente(c);
                JOptionPane.showMessageDialog(this, "Cliente registrado. ID: " + id);
                refreshClientesTable();
            }catch(Exception ex){ JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        });

        btnAsociarMascota.addActionListener(e -> {
            String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:");
            String idMascota = JOptionPane.showInputDialog(this, "ID Mascota:");
            if(idCliente==null || idMascota==null) return;
            ClienteDTO cliente = clienteDAO.getCliente(idCliente);
            MascotaDTO mascota = mascotaDAO.buscarMascotaPorId(idMascota);
            if(cliente==null){ JOptionPane.showMessageDialog(this, "Cliente no encontrado"); return; }
            if(mascota==null){ JOptionPane.showMessageDialog(this, "Mascota no encontrada"); return; }
            clienteDAO.addMascota(cliente, mascota);
            JOptionPane.showMessageDialog(this, "Mascota asociada al cliente");
            mascota.setAdoptado(true);
            refreshClientesTable();
            refreshMascotasTable();
        });

        return p;
    }

private JPanel buildVentasPanel(){
    JPanel p = new JPanel(new BorderLayout(8,8));
    p.setBorder(new EmptyBorder(8,8,8,8));

    JTable tabla = new JTable(ventasModel);
    JScrollPane sp = new JScrollPane(tabla);
    p.add(sp, BorderLayout.CENTER);

    JPanel form = new JPanel(new GridLayout(0,2,6,6));
    JTextField tfClienteId = new JTextField();
    JTextField tfVendedor = new JTextField();
    JTextField tfValor = new JTextField("0.0");
    JTextField tfMascotasCsv = new JTextField();
    JTextField tfAccesorio = new JTextField();

    form.add(new JLabel("ID Cliente:")); form.add(tfClienteId);
    form.add(new JLabel("Vendedor (nombre):")); form.add(tfVendedor);
    form.add(new JLabel("Valor total:")); form.add(tfValor);
    form.add(new JLabel("IDs mascotas (csv):")); form.add(tfMascotasCsv);
    form.add(new JLabel("Accesorio (nombre):")); form.add(tfAccesorio);

    JButton btnAddItem = new JButton("Añadir ítem"); // 🔹 nuevo botón
    JButton btnRegistrar = new JButton("Registrar Venta");

    form.add(btnAddItem);
    form.add(btnRegistrar);
    p.add(form, BorderLayout.EAST);

    ArrayList<MascotaDTO> mascotasTemp = new ArrayList<>();
    ArrayList<AccesorioDTO> accesoriosTemp = new ArrayList<>();

    btnAddItem.addActionListener(e -> {
        try {
            String[] ids = tfMascotasCsv.getText().split(",");
            for (String s : ids) {
                String idm = s.trim();
                if (idm.isEmpty()) continue;
                MascotaDTO m = mascotaDAO.buscarMascotaPorId(idm);
                if (m != null) {
                    mascotasTemp.add(m);
                }
            }

            String nombreAccesorio = tfAccesorio.getText().trim();
            if (!nombreAccesorio.isEmpty()) {
                AccesorioDTO accesorio = new AccesorioDTO();
                accesorio.setTipo(nombreAccesorio);
                accesoriosTemp.add(accesorio);
            }

            tfMascotasCsv.setText("");
            tfAccesorio.setText("");

            JOptionPane.showMessageDialog(this, "Ítem(s) añadidos temporalmente (" +
                mascotasTemp.size() + " mascotas, " + accesoriosTemp.size() + " accesorios)");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir ítem: " + ex.getMessage());
        }
    });

    btnRegistrar.addActionListener(e -> {
        try {
            String idCliente = tfClienteId.getText().trim();
            ClienteDTO cliente = clienteDAO.getCliente(idCliente);
            if (cliente == null) { JOptionPane.showMessageDialog(this, "Cliente no encontrado"); return; }

            double valor = Double.parseDouble(tfValor.getText().trim());
            String vendedorNombre = tfVendedor.getText().trim();
            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setNombre(vendedorNombre);

            VentaDTO venta = new VentaDTO(LocalDate.now(), valor, cliente, vendedorDTO);

            if (!mascotasTemp.isEmpty()) {
                venta.setMascotas(new ArrayList<>(mascotasTemp));
                for (MascotaDTO m : mascotasTemp) {
                    mascotaDAO.venderMascota(m, cliente);
                }
            }

            if (!accesoriosTemp.isEmpty()) {
                venta.setAccesorios(new ArrayList<>(accesoriosTemp));
            }

            ventaDAO.registrar(venta);
            clienteDAO.addVenta(idCliente, venta);

            JOptionPane.showMessageDialog(this, "Venta registrada correctamente");

            mascotasTemp.clear();
            accesoriosTemp.clear();

            refreshVentasTable();
            refreshMascotasTable();
            refreshClientesTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar venta: " + ex.getMessage());
        }
    });

    return p;
}

    private JPanel buildConsultasPanel(){
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));

        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        JTextField tfIdMascota = new JTextField();
        JTextField tfFecha = new JTextField(dtf.format(LocalDate.now()));
        JTextField tfSintomas = new JTextField();
        JTextField tfTratamiento = new JTextField();

        form.add(new JLabel("ID Mascota:")); form.add(tfIdMascota);
        form.add(new JLabel("Fecha (yyyy-MM-dd):")); form.add(tfFecha);
        form.add(new JLabel("Síntomas:")); form.add(tfSintomas);
        form.add(new JLabel("Tratamiento:")); form.add(tfTratamiento);
        JButton btnRegistrar = new JButton("Registrar Consulta");
        form.add(btnRegistrar);

        JTextArea taHistorial = new JTextArea();
        taHistorial.setEditable(false);

        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(taHistorial), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            try{
                String idMascota = tfIdMascota.getText().trim();
                MascotaDTO m = mascotaDAO.buscarMascotaPorId(idMascota);
                if(m==null){ JOptionPane.showMessageDialog(this, "Mascota no encontrada"); return; }
                LocalDate fecha = LocalDate.parse(tfFecha.getText().trim());
                String sintomas = tfSintomas.getText().trim();
                String tratamiento = tfTratamiento.getText().trim();
                mascotaDAO.setConsulta(idMascota, fecha, sintomas, tratamiento);
                JOptionPane.showMessageDialog(this, "Consulta registrada");
                // mostrar historial
                StringBuilder sb = new StringBuilder();
                for(ConsultaDTo c : m.getConsultas()){
                    sb.append(c.getFecha()).append(" - ").append(c.getSintomas()).append(" - ").append(c.getTratamiento()).append('\n');
                }
                taHistorial.setText(sb.toString());
            }catch(Exception ex){ JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        });

        return p;
    }

    private JPanel buildReportesPanel(){
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));

        JPanel left = new JPanel(new BorderLayout());
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        left.add(new JScrollPane(ta), BorderLayout.CENTER);

        JPanel controls = new JPanel(new GridLayout(0,1,6,6));
        JButton btnListByClient = new JButton("Listar mascotas por cliente");
        JButton btnOwnerByPet = new JButton("Buscar dueño por ID mascota");
        JButton btnHistoryByPet = new JButton("Historial consultas por ID mascota");
        controls.add(btnListByClient); controls.add(btnOwnerByPet); controls.add(btnHistoryByPet);
        left.add(controls, BorderLayout.EAST);

        p.add(left, BorderLayout.CENTER);

        // Actions
        btnListByClient.addActionListener(e -> {
            try{
                ArrayList<ClienteDTO> clientes = getAllClientesViaReflection();
                StringBuilder sb = new StringBuilder();
                for(ClienteDTO c : clientes){
                    sb.append("Cliente: ").append(c.getId()).append(" - ").append(c.getNombre()).append("\n");
                    for(MascotaDTO m : c.getMascotas()){
                        sb.append("    ").append(m.getId()).append(" - ").append(m.getNombre()).append("\n");
                    }
                }
                ta.setText(sb.toString());
            }catch(Exception ex){ JOptionPane.showMessageDialog(this, "Error al obtener clientes: " + ex.getMessage()); }
        });

        btnOwnerByPet.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "ID Mascota:");
            if(id==null) return;
            ArrayList<ClienteDTO> clientes = getAllClientesViaReflection();
            StringBuilder sb = new StringBuilder();
            for(ClienteDTO c : clientes){
                for(MascotaDTO m : c.getMascotas()){
                    if(m.getId().equals(id)){
                        sb.append("Dueño: ").append(c.getId()).append(" - ").append(c.getNombre());
                    }
                }
            }
            if(sb.length()==0) sb.append("No se encontró dueño o la mascota no está asociada a ningún cliente");
            ta.setText(sb.toString());
        });

        btnHistoryByPet.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "ID Mascota:");
            if(id==null) return;
            MascotaDTO m = mascotaDAO.buscarMascotaPorId(id);
            if(m==null){ JOptionPane.showMessageDialog(this, "Mascota no encontrada"); return; }
            StringBuilder sb = new StringBuilder();
            sb.append("Mascota: ").append(m.getId()).append(" - ").append(m.getNombre()).append('\n');
            for(ConsultaDTo c : m.getConsultas()){
                sb.append(c.getFecha()).append(" - ").append(c.getSintomas()).append(" - ").append(c.getTratamiento()).append('\n');
            }
            ta.setText(sb.toString());
        });

        return p;
    }

    // ---------------- Utilities ----------------
    private void refreshMascotasTable(){
        mascotasModel.setRowCount(0);
        for(MascotaDTO m : mascotaDAO.getMascotas()){
            mascotasModel.addRow(new Object[]{m.getId(), m.getNombre(), m.getRaza(), m.getEdad(), m.getTipo(), m.getPeso(), m.isAdoptado()});
        }
    }
    private void refreshClientesTable(){
        clientesModel.setRowCount(0);
        ArrayList<ClienteDTO> clientes = getAllClientesViaReflection();
        for(ClienteDTO c : clientes){
            clientesModel.addRow(new Object[]{c.getId(), c.getNombre(), c.getDireccion(), c.getTelefono(), c.getMascotas().size()});
        }
    }
    private void refreshVentasTable(){
        ventasModel.setRowCount(0);
        for(VentaDTO v : ventaDAO.getVentas()){
            ventasModel.addRow(new Object[]{v.getFecha(), v.getValor(), v.getCliente()!=null? v.getCliente().getId():"-", v.getVendedor()!=null?v.getVendedor().getNombre():"-", v.getAccesorios().size(),v.getMascotas().size()});
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<ClienteDTO> getAllClientesViaReflection(){
        try{
            Field f = ClienteDAO.class.getDeclaredField("clientes");
            f.setAccessible(true);
            Object val = f.get(clienteDAO);
            if(val instanceof ArrayList) return (ArrayList<ClienteDTO>) val;
        }catch(Exception ex){ ex.printStackTrace(); }
        return new ArrayList<>();
    }

    public static void main(String[] args){
        // Crear y mostrar la UI
        SwingUtilities.invokeLater(() -> {
            MainFrame app = new MainFrame();
            app.setVisible(true);
        });
    }
}
