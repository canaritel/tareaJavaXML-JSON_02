/**
 *  PROYECTO ESCRITURA Y LECTURA XML & JSON CON JAXB
 *
 *  Para el correcto funcionamiento de los métodos a usar vamos a crear
 *  nuestro proyecto de tipo Java Maven con el IDE Netbeans 12 y Java 11.
 *  Añadimos al fichero pom.xml las dependencias necesarias
 *
 */
package com.televoip.tareaxml_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;

/**
 * @author antonio_gonzalez_bonilla
 *
 */
public class Main implements Serializable {

    /**
     * @param args
     * @throws java.io.IOException
     *
     * Aplicamos métodos JAXB para grabar y leer XML & JSON
     *
     */
    public static void main(String[] args) throws IOException {
        //Mediante este método establecemos las propiedades para hacer funcionar el JSON **********IMPORTANTE INCLUIRLO***********
        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        //************************************************************************************************************************
        //Declaramos los objetos que vamos a usar para crear nuestra estructura XML
        ClassLibreria libreria = new ClassLibreria();  //nodo raiz 
        ArrayList<ClassLibro> libros = new ArrayList(); //nodo padre
        //Creamos el libro hijo y lo añadimos al objeto nodo del padre
        ClassLibro libro = new ClassLibro();
        libro.setTitulo("Titulo1");
        libro.setAutor("Autor1");
        libro.setPaginas(1100);
        libro.setPrecio(11.10);
        libros.add(libro);
        //Creamos el libro hijo y lo añadimos al objeto nodo del padre
        libro = new ClassLibro();
        libro.setTitulo("Titulo2");
        libro.setAutor("Autor2");
        libro.setPaginas(2200);
        libro.setPrecio(22.20);
        libros.add(libro);
        //Creamos el libro hijo y lo añadimos al objeto nodo del padre
        libro = new ClassLibro();
        libro.setTitulo("Titulo3");
        libro.setAutor("Autor3");
        libro.setPaginas(3300);
        libro.setPrecio(33.30);
        libros.add(libro);
        //Anadimos todos los nodos creados al nodo raiz 
        libreria.setLibro(libros);

        Scanner sc = new Scanner(System.in);
        int valor;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("********************************************");
        System.out.println("***    Gestión de ficheros XML & JSON    ***");
        System.out.println("********************************************");

        do {
            do {
                System.out.println("\n1. Crear Fichero XML");
                System.out.println("2. Crear Fichero JSON");
                System.out.println("3. Leer Fichero XML");
                System.out.println("4. Leer Fichero JSON");
                System.out.println("Introduzca la opción que desee (0 para salir): ");
                valor = sc.nextInt();
            } while (valor < 0 || valor > 4);

            switch (valor) {
                case 1:
                    System.out.println("\nCreando el fichero XML...");
                    grabarXML(libreria);
                    break;

                case 2:
                    System.out.println("\nCreando el fichero XML con JDOM2...");
                    grabarJSON(libreria);
                    break;

                case 3:
                    System.out.println("\nLeyendo el fichero XML");
                    leerXML();
                    break;

                case 4:
                    System.out.println("\nLeyendo el fichero XML con JDOM2");
                    leerJSON();
                    break;

                case 0:
                    System.out.println("\nCerramos el programa");
                    break;

            }
        } while (valor != 0);
    }

    private static void leerXML() {
        System.out.println("\n**************************");
        System.out.println("*  Leyendo fichero XML   *");
        System.out.println("**************************\n");
        File fichero = new File("./libros.xml");  //nombre y ruta del fichero
        ArrayList<ClassLibro> librosIn;
        ClassLibreria libreriaIn = new ClassLibreria();
        try {
            // Creamos un JaxBContext
            JAXBContext context = JAXBContext.newInstance(ClassLibreria.class);
            // Creamos el objeto unmarshaller usando el JaxB contexto
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //cargamos en el objeto libreriaIn el fichero XML
            libreriaIn = (ClassLibreria) unmarshaller.unmarshal(fichero);

        } catch (JAXBException e) {
            System.err.println("Error leerXML " + e);
        }
        //Cargamos en el array librosIn el objeto de libreriaIN
        librosIn = (ArrayList<ClassLibro>) libreriaIn.getLibro();
        //mostramos el índice de los campos a mostrar
        System.out.println("Título" + "\t\t" + "Autor" + "\t\t" + "Páginas" + "\t\t" + "Precio");
        for (ClassLibro res : librosIn) {
            System.out.println(res.getTitulo() + "\t\t" + res.getAutor() + "\t\t" + res.getPaginas() + "\t\t" + res.getPrecio());
        }
    }

    private static void leerJSON() {
        System.out.println("\n\n***************************");
        System.out.println("*  Leyendo fichero JSON   *");
        System.out.println("***************************");
        File fichero = new File("./libros.json");  //nombre y ruta del fichero
        ArrayList<ClassLibro> librosIn;
        ClassLibreria libreriaIn = new ClassLibreria();
        try {
            // Creamos un JaxBContext
            JAXBContext context = JAXBContext.newInstance(ClassLibreria.class);
            // Creamos el objeto unmarshaller usando el JaxB contexto
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Establecemos las propiedades para el JSON
            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            unmarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            unmarshaller.setProperty(MarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@");
            //cargamos en el objeto libreriaIn el fichero JSON
            libreriaIn = (ClassLibreria) unmarshaller.unmarshal(fichero);

        } catch (JAXBException e) {
            System.err.println("Error leerJSON " + e);
        }
        //Cargamos en el array librosIn el objeto de libreriaIN
        librosIn = (ArrayList<ClassLibro>) libreriaIn.getLibro();
        //mostramos el índice de los campos a mostrar
        System.out.println("\nTítulo" + "\t\t" + "Autor" + "\t\t" + "Páginas" + "\t\t" + "Precio");
        for (ClassLibro res : librosIn) {  //recorremos el array librosIN y mostramos los resultados
            System.out.println(res.getTitulo() + "\t\t" + res.getAutor() + "\t\t" + res.getPaginas() + "\t\t" + res.getPrecio());
        }
    }

    private static void grabarXML(ClassLibreria libreriaOut) {
        System.out.println("**************************");
        System.out.println("*  Grabando fichero XML  *");
        System.out.println("**************************\n");
        File fichero = new File("./libros.xml");  //nombre y ruta del fichero
        try {
            // Creamos un JaxBContext
            JAXBContext contexto = JAXBContext.newInstance(ClassLibreria.class);
            // Creamos el objeto Marshaller usando el JaxB contexto
            Marshaller marshaller = contexto.createMarshaller();
            // Establecemos para el objeto marshaller el formato de salida
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Mostramos el XML y grababamos el objeto XML como fichero
            marshaller.marshal(libreriaOut, System.out);
            marshaller.marshal(libreriaOut, fichero);

        } catch (JAXBException e) {
            System.err.println("Error grabarXML " + e);
        }
    }

    private static void grabarJSON(ClassLibreria libreriaOut) throws IOException {
        System.out.println("\n***************************");
        System.out.println("*  Grabando fichero JSON  *");
        System.out.println("***************************\n");
        File file = new File("./libros.json");
        try {
            // Creamos un JaxBContext
            JAXBContext contexto = JAXBContext.newInstance(ClassLibreria.class);
            // Creamos el objeto Marshaller usando el JaxB contexto
            Marshaller marshaller = contexto.createMarshaller();
            // Establecemos las propiedades para el JSON
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            // Establecemos para el objeto marshaller el formato de salida
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Mostramos el JSON y grababamos el objeto JSON como fichero
            marshaller.marshal(libreriaOut, System.out);
            marshaller.marshal(libreriaOut, file);

        } catch (JAXBException e) {
            System.err.println("Error grabarJSON " + e);
        }
    }

}
