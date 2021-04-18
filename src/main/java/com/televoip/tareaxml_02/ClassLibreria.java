package com.televoip.tareaxml_02;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "libreria") //Mediante una anotación le indicamos cual es la raiz del XML 
@XmlType(propOrder = {"libro"}) //indico el orden de los nodos del XML

public class ClassLibreria implements Serializable{

    private ArrayList<ClassLibro> libro = new ArrayList();

    public ClassLibreria() {
    }

    @XmlElementWrapper(name = "libros") // con wraper le indicamos que es un envoltorio, es decir que dentro hay datos hijos
    @XmlElement(name = "libro") // con el name le indicamos lo que retorna el envoltorio de XML - elemento=nodo
    public ArrayList<ClassLibro> getLibro() {
        return libro;
    }

    public void setLibro(ArrayList<ClassLibro> libro) {
        this.libro = libro;
    }

}
