package com.televoip.tareaxml_02;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "libro")  //especifica la clase raiz que vamos a convertir a XML
@XmlType(propOrder = {"titulo", "autor", "paginas", "precio"}) // indico el orden de los nodos del XML

public class ClassLibro implements java.io.Serializable{

    private String titulo;
    private String autor;
    private int paginas;
    private double precio;

    public ClassLibro() {
    }

    public ClassLibro(String titulo, String autor, int paginas, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.precio = precio;
    }

    @XmlElement(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement(name = "autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @XmlElement(name = "paginas")
    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @XmlElement(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ClassLibro{" + "titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + ", precio=" + precio + '}';
    }

}
