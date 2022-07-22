package Vistas;

import Controlador.*;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserMenu extends javax.swing.JFrame {

    //1.Instancia de la clase conexion
    Conexion conexion = new Conexion();
    Connection connection;
    //2. L a libreria Statement permite ejecutar los query SQL
    Statement st;
    ResultSet rs;
    //3. Creamos una instancia de la interfaz de la tabla empleados
    DefaultTableModel contenidoTabla, contenidoTablaDepartamento, contenidoTablaEmpleados;
    ComboBoxModel enumDepartamento, enumZona, enumTipoCalle;

    public UserMenu() {
        enumDepartamento = new DefaultComboBoxModel(EnumDepartamento.values());
        enumZona = new DefaultComboBoxModel(EnumTipoZona.values());
        enumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
        initComponents();
        setLocationRelativeTo(null);
        listarEmpleados();
        listarDepartamentos();
    }

    private void listarDepartamentos() {

        String query = "SELECT nombreSucursal,nombreDepartamento FROM direccion INNER JOIN sucursal  ON idDireccion = FK_idDireccion;";
        System.out.println("Entro al metodo listarDepartamentos");
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Object[] departamentos = new Object[2];
            contenidoTablaDepartamento = (DefaultTableModel) tblDepartamentos.getModel();
            while (rs.next()) {
                departamentos[0] = rs.getString("nombreSucursal");
                departamentos[1] = rs.getString("nombreDepartamento");
                System.out.println("Sucursal: " + departamentos[0] + ", departamento: " + departamentos[1]);
                contenidoTablaDepartamento.addRow(departamentos);
                tblDepartamentos.setModel(contenidoTablaDepartamento);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

   

    // 4. Metodo que trae todos los empleados existentes en la base de datos
    public void listarEmpleados() {
        String nombre = txtBuscarEmp.getText();
        if (nombre.isEmpty()) {
            String queryConsulta = "SELECT * FROM empleado";
            // 5. Intentar ejecutar el query y obtener una respuesta de la base de datos
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(queryConsulta);
                //6. Crear un objeto donde se almacene el resultado de la consulta
                Object[] empleados = new Object[6];
                //7. Actualizar la propiedad Model de la tabla 
                contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
                //8. Recorremos el resultado de la consulta del query
                while (rs.next()) {
                    empleados[0] = rs.getInt("idEmp");
                    empleados[1] = rs.getString("nombreEmp");
                    empleados[2] = rs.getString("apellidos");
                    empleados[3] = rs.getString("tipoDocumento");
                    empleados[4] = rs.getString("documento");
                    empleados[5] = rs.getString("correo");
                    System.out.println("id: " + empleados[0] + ", nombre: "
                            + empleados[1] + " , apellidos: "
                            + empleados[2] + ", documento: "
                            + empleados[3] + " " + empleados[4]
                            + ", correo: " + empleados[5]);
                    //9.Creamos una fila   dentro de la tabla para cada elemento que devuelve
                    //el query
                    contenidoTablaEmpleados.addRow(empleados);

                }
                tblEmpleados.setModel(contenidoTablaEmpleados);
            } catch (SQLException e) {
                System.out.println("Error para generar tabla de datos ");
                System.out.println(e);

            }

        } else {
            String queryEncontrar = "SELECT * FROM empleado WHERE nombreEmp LIKE '% " + nombre + "%' OR apellidos LIKE '%" + nombre + "%';";
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(queryEncontrar);
                //6. Crear un objeto donde se almacene el resultado de la consulta
                Object[] empleados = new Object[6];
                //7. Actualizar la propiedad Model de la tabla 
                contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
                //8. Recorremos el resultado de la consulta del query
                while (rs.next()) {
                    empleados[0] = rs.getInt("idEmp");
                    empleados[1] = rs.getString("nombreEmp");
                    empleados[2] = rs.getString("apellidos");
                    empleados[3] = rs.getString("tipoDocumento");
                    empleados[4] = rs.getString("documento");
                    empleados[5] = rs.getString("correo");
                    System.out.println("id: " + empleados[0] + ", nombre: " + empleados[1] + " , apellido: " + empleados[2] + ", documento: "
                            + empleados[3] + " " + empleados[4] + ", correo: " + empleados[5]);
                    //9.Creamos una fila   dentro de la tabla para cada elemento que devuelve
                    //el query
                    contenidoTablaEmpleados.addRow(empleados);

                }
                tblEmpleados.setModel(contenidoTablaEmpleados);
            } catch (SQLException e) {
                System.out.println("Error para buscar empleado ");
                System.out.println(e);

            }
        }

    }

    //Cuando añado un nuevo empleado bakground es el siguiente
    //1.Se almacena el nuevo empleado en la base de datos
    //2. Se eliminan los datos que tiene la tabla tblEmpleados
    //3.Se llama el metodolistarEmpleados para consultar nuevamente la bd
    //4. Se cargan los empleados en la tabla incluyendo el recien creado
    private void borrarDatosTabla() {
        //El metodo getRowCount devuelve la cantidad de registro existentes
        for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
            contenidoTablaEmpleados.removeRow(i);
            i -= 1;

        }

    }

    public void borrarDatosTablaDepartamentos() {
        for (int i = 0; i < tblDepartamentos.getRowCount(); i++) {
            contenidoTablaDepartamento.removeRow(i);
            i = i - 1;

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbDepartamento = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbCalle = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbZona = new javax.swing.JComboBox<>();
        txtNumero1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNumero2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNumero3 = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnListarEmpleados = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarEmp = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane8.setBackground(new java.awt.Color(186, 254, 254));

        jPanel2.setBackground(new java.awt.Color(154, 244, 244));

        jPanel4.setBackground(new java.awt.Color(172, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Departamento");

        cbDepartamento.setModel(enumDepartamento);
        cbDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDepartamentoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Tipo Calle");

        cbCalle.setModel(enumTipoCalle);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Zona");

        cbZona.setModel(enumZona);
        cbZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbZonaActionPerformed(evt);
            }
        });

        jLabel2.setText("#");

        jLabel9.setText("-");

        txtNumero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumero3ActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Reto3_g53EricGarcia\\src\\assets\\confirmIcon.png")); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(19, 19, 19))
        );

        tblDepartamentos.setBackground(new java.awt.Color(167, 254, 254));
        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Departamento"
            }
        ));
        jScrollPane3.setViewportView(tblDepartamentos);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        btnListarEmpleados.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Reto3_g53EricGarcia\\src\\ezgif.com-gif-maker.gif")); // NOI18N
        btnListarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarEmpleadosActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Reto3_g53EricGarcia\\src\\assets\\showUser (1).png")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListarEmpleados))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Sucursales", jPanel2);

        jPanel3.setBackground(new java.awt.Color(200, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("INFORMACION EMPLEADO");

        btnAddUser.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAddUser.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Reto3_g53EricGarcia\\src\\assets\\avatar.png")); // NOI18N
        btnAddUser.setText("Nuevo");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nombre");

        btnSearch.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Downloads\\showUser (1).png")); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(159, 247, 247));

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido(s)", "Tipo de Documento", "Documento", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEmpleados);
        if (tblEmpleados.getColumnModel().getColumnCount() > 0) {
            tblEmpleados.getColumnModel().getColumn(0).setHeaderValue("ID");
            tblEmpleados.getColumnModel().getColumn(1).setHeaderValue("Nombre");
            tblEmpleados.getColumnModel().getColumn(2).setHeaderValue("Apellido(s)");
            tblEmpleados.getColumnModel().getColumn(3).setHeaderValue("Tipo de Documento");
            tblEmpleados.getColumnModel().getColumn(4).setHeaderValue("Documento");
            tblEmpleados.getColumnModel().getColumn(5).setHeaderValue("Correo");
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddUser)
                .addGap(39, 39, 39))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 179, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(153, 153, 153)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(292, 292, 292))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane8.addTab("Empleados", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane8)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        //1.Crear una variable que permite capturar la informacion seleccionada
        int row = tblEmpleados.getSelectedRow();
        System.out.println("Fila seleccionada n°: " + row);
        //2.Validar si el usuario ha seleccionado un empleado
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un empleado", "", JOptionPane.WARNING_MESSAGE);
        } else {
            //3. Partiendo del hecho de que cada fila es un registro de empleado
            int idEmp = Integer.parseInt(tblEmpleados.getValueAt(row, 0).toString());
            String nombreEmp = tblEmpleados.getValueAt(row, 1).toString();
            String apellidos = tblEmpleados.getValueAt(row, 2).toString();
            String tipoDocumento = tblEmpleados.getValueAt(row, 3).toString();
            String documento = tblEmpleados.getValueAt(row, 4).toString();
            String correo = tblEmpleados.getValueAt(row, 5).toString();

            System.out.println("idEmp: " + idEmp + "\nnombre: " + nombreEmp + " "
                    + apellidos + "\ndocumento: " + tipoDocumento + " " + documento
                    + "\ncorreo: " + correo);
            ShowUserForm showUserForm = new ShowUserForm(this, true);
            //Mediante la instancia del JDialog enviamos los datos del empleado
            //de esta vista actual a la vista que muestra la información en detalle
            showUserForm.recibeDatos(idEmp, nombreEmp, apellidos, tipoDocumento, documento, correo);
            showUserForm.setVisible(true);
            //Para que se actualice la informacion del empleado que se acaba de editar
            //se debe borar todos los datos de la tabla y volverlos a cargar
            borrarDatosTabla();
            listarEmpleados();
        }
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        borrarDatosTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // 1. Creamos uns instancia del dialogo
        AddUserForm addUserF = new AddUserForm(this, true);
        addUserF.setVisible(true);
        borrarDatosTabla();
        listarEmpleados();

    }//GEN-LAST:event_btnAddUserActionPerformed

    private void cbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDepartamentoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String departamentoOption = cbDepartamento.getSelectedItem().toString();
        String zonaOption = cbZona.getSelectedItem().toString();
        String tipoCalleOption = cbCalle.getSelectedItem().toString();
        String numero1 = txtNumero1.getText();
        String numero2 = txtNumero2.getText();
        String numero3 = txtNumero3.getText();

        String queryDireccion = "INSERT INTO `direccion`(`zona`, `tipoCalle`, `numero1`, "
                + "`numero2`, `numero3`, `nombreDepartamento`) VALUES ('" + zonaOption
                + "','" + tipoCalleOption + "','" + numero1 + "','" + numero2 + "','" + numero3
                + "','" + departamentoOption + "');";
        System.out.println(queryDireccion);
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            st.executeUpdate(queryDireccion);
           String queryIdDireccion = "SELECT idDireccion FROM `direccion` WHERE nombreDepartamento = '" 
                    + departamentoOption + "'AND zona ='" + zonaOption  + "'AND tipoCalle ='" 
                    + tipoCalleOption + "'AND numero1 ='" + numero1 + "'AND numero2 ='" + numero2 + "'AND numero3 ='"+ numero3 +"' ;";
           
            try{
                rs = st.executeQuery(queryIdDireccion);
                SucursalForm sucursalForm = new SucursalForm(this,true);
                sucursalForm.setVisible(true);
                while(rs.next()){
                    int idDireccion = rs.getInt("idDireccion");
                     sucursalForm.recibeIdDireccion(idDireccion);
                     System.out.println("Envia" + idDireccion);
                }
               
                borrarDatosTablaDepartamentos();
                listarDepartamentos();
            }catch (SQLException e ){
                System.out.println(e);
                
            }
            
            
            
            JOptionPane.showMessageDialog(this, "La Sucursal ha sido creada con exito.");   

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "No se pudo añadir el departamento", "", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbZonaActionPerformed

    private void txtNumero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumero3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumero3ActionPerformed

    private void btnListarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarEmpleadosActionPerformed
      int row = tblDepartamentos.getSelectedRow();
        System.out.println(row);
        String sucursal = tblDepartamentos.getValueAt(row, 0).toString();
        String querysucursal = "SELECT  idsucursal FROM sucursal WHERE nombreSucursal = '"+ sucursal +"';";
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(querysucursal);
            while(rs.next()){
            int idSucursal = rs.getInt("idSucursal");    
            EmpleadoForm empleadoForm = new EmpleadoForm(this, true);
            empleadoForm.setVisible(true);
            empleadoForm.recibeIdsucursal(idSucursal);
            }
        }catch(SQLException e){
            System.out.println(e);
            
        }
        
        System.out.println(querysucursal);
        
        
       
        
    }//GEN-LAST:event_btnListarEmpleadosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarEmpleados;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbCalle;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtBuscarEmp;
    private javax.swing.JTextField txtNumero1;
    private javax.swing.JTextField txtNumero2;
    private javax.swing.JTextField txtNumero3;
    // End of variables declaration//GEN-END:variables
}
