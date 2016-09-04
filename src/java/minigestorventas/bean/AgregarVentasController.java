package minigestorventas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.JOptionPane;
import minigestorventas.entities.Clientes;
import minigestorventas.entities.DetVentas;
import minigestorventas.entities.Productos;
import minigestorventas.entities.Operadores;
import minigestorventas.entities.Ventas;
import minigestorventas.sessions.ClientesFacade;
import minigestorventas.sessions.DetVentasFacade;
import minigestorventas.sessions.ProductosFacade;
import minigestorventas.sessions.OperadoresFacade;
import minigestorventas.sessions.VentasFacade;
import org.primefaces.context.RequestContext;

@Named("agregarVentasController")
@SessionScoped
public class AgregarVentasController implements Serializable {

    private String nroVenta;
    private String fechaVenta;
    private Connection con = null;
    private List<Clientes> clientesList = new ArrayList<Clientes>(); //comboClientes
    private List<Operadores> vendedoresList = new ArrayList<Operadores>(); //comboVendedores
    private List<Productos> productosList = new ArrayList<Productos>(); //comboProductos
    private List<Ventas> ventasList = new ArrayList<Ventas>();
    private List<DetVentas> det_ventaList = new ArrayList<DetVentas>();

    private Clientes idCliente;
    private Operadores vendedor;
    private Productos producto;
    private int cantidad;
    private int total_producto;
    private int total_venta;
    Ventas venta = new Ventas();

    @EJB
    private ClientesFacade clientesFacade = new ClientesFacade();
    @EJB
    private OperadoresFacade vendedoresFacade = new OperadoresFacade();
    @EJB
    private ProductosFacade productosFacade = new ProductosFacade();
    @EJB
    private VentasFacade ventasFacade = new VentasFacade();
    @EJB
    private DetVentasFacade det_ventasFacade = new DetVentasFacade();


    @PostConstruct
    void initialiseSession() {
        con = DataConnect.getConnection();
        this.cargarVista();
    }

    public void cargarVista() {

        int nuevaSeq = obtenerNroVenta();
        if (nuevaSeq < 10) {
            this.nroVenta = "000" + String.valueOf(nuevaSeq);
        } else if (nuevaSeq < 100) {
            this.nroVenta = "0" + String.valueOf(nuevaSeq);
        } else {
            this.nroVenta = String.valueOf(nuevaSeq);
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        fechaVenta = today;

        this.clientesList = clientesFacade.findAll();
        this.vendedoresList = vendedoresFacade.findAll();
        this.productosList = productosFacade.findAll();
        this.ventasList = ventasFacade.findAll();
        //this.det_ventaList = det_ventasFacade.findAll();
    }

    public int obtenerNroVenta() {
        int ultimoValor = 0;
        try {
            PreparedStatement ps
                    = con.prepareStatement("SELECT last_value FROM ventas_id_seq");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal uv = rs.getBigDecimal("last_value");
                ultimoValor = uv.toBigInteger().intValue();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener secuencia -->" + ex.getMessage());
        }
        return ultimoValor;
    }
    
    public void agregarProducto() {
        this.total_producto = this.cantidad * this.producto.getPrecio();
        
        venta.setIdCliente(this.idCliente);
        venta.setIdOperador(this.vendedor);
        
        DetVentas det_venta = new DetVentas();
        det_venta.setCantidad(this.cantidad);
        det_venta.setIdProducto(this.producto);
        det_venta.setIdVentas(venta);
        det_venta.setTotalProducto(this.total_producto);
       
        this.det_ventaList.add(det_venta);
    }
    
    public void registrar(){
        this.total_venta = 0;
        
        for (DetVentas det_venta : this.det_ventaList){
            this.total_venta += det_venta.getTotalProducto();//calcula el total de la venta
        }
        
        venta.setTotalVenta(this.total_venta);
        
        this.ventasFacade.create(venta);
        
        for (DetVentas det_venta : this.det_ventaList){
            this.det_ventasFacade.create(det_venta);//crea un detalle ventas por cada objeto en la lista
        }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Cargado Correctamente!!");
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
           
    }
    
  

    public String getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(String nroVenta) {
        this.nroVenta = nroVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }
    

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public List<Operadores> getVendedoresList() {
        return vendedoresList;
    }

    public void setVendedoresList(List<Operadores> vendedoresList) {
        this.vendedoresList = vendedoresList;
    }
    
    public List<Ventas> getVentasList() {
        return ventasList;
    }
    
     public List<DetVentas> getDetVentasList() {
        return det_ventaList;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Operadores getVendedor() {
        return vendedor;
    }

    public void setVendedor(Operadores vendedor) {
        this.vendedor = vendedor;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    

}
