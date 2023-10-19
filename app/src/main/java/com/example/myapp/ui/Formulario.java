package com.example.myapp.ui;

public class Formulario {
    private String productora;
    private String nombre;
    private String correo;
    private String telefono;
    private String mensaje;

    public Formulario() {
        // Constructor vacío requerido para Firebase
    }

    public Formulario(String productora, String nombre, String correo, String telefono, String mensaje) {
        this.productora = productora;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.mensaje = mensaje;
    }

    // Métodos getter y setter para acceder y modificar los atributos

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

