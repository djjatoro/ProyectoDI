package vista;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

class LocalizadorImagenes
{
  private File directorio;
  private Frame ventana;
  private Map mapa = new HashMap();
  
  public LocalizadorImagenes(File directorio, JFrame ventana)
  {
    this.directorio = directorio;
    
    this.ventana = ventana;
    
    carga();
  }
  
  private void carga()
  {
    File[] ficheros = this.directorio.listFiles();
    if (!this.directorio.exists())
    {
      System.err.println("El directorio \"imagenes\" no existe. Se utilizará una imagen por defecto");
      return;
    }
    MediaTracker tracker = new MediaTracker(this.ventana);
    for (int i = 0; i < ficheros.length; i++) {
      if (ficheros[i].isFile())
      {
        String nombreCompleto = ficheros[i].getName();
        if (nombreCompleto.lastIndexOf('.') != -1)
        {
          String nombre = nombreCompleto.substring(0, nombreCompleto.lastIndexOf('.'));
          
          String extension = nombreCompleto.substring(nombreCompleto.lastIndexOf('.') + 1)
            .toLowerCase();
          if ((extension.equals("gif")) || (extension.equals("jpg")) || 
            (extension.equals("jpeg")) || (extension.equals("png"))) {
            if (this.mapa.get(nombre) != null)
            {
              System.err.println(
                "Error en la carga de imágenes: existen dos ficheros con el mismo nombre ->" + 
                nombre);
            }
            else
            {
              Image imagen = Toolkit.getDefaultToolkit().getImage(
                ficheros[i].getAbsolutePath());
              
              tracker.addImage(imagen, 0);
              try
              {
                tracker.waitForAll();
              }
              catch (InterruptedException localInterruptedException) {}
              this.mapa.put(nombre, imagen);
            }
          }
        }
      }
    }
  }
  
  public Image localiza(String nombre)
  {
    if (nombre == null) {
      throw new NullPointerException("Error gráfico: el nombre de la imagen es nulo");
    }
    return (Image)this.mapa.get(nombre);
  }
}
