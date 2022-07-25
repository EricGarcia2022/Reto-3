package Modelo;

public class Sucursal {
    private int idSucursal;
    private String sucursal;

    public Sucursal() {
        this.idSucursal = 0;
        this.sucursal = "";
        
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return getSucursal();
    }
    
    
}
