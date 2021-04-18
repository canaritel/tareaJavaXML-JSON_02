# tareaJavaXML-JSON_02

Tarea donde grabamos y leemos ficheros XML y JSON haciendo uso de la librería JAXB.

Importante incluir las dependencias en el fichero pom.xml  , además añadir esta intrucción dentro del método Main principal que permitirá usar correctamente las librerías JSON:
System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
