package controlador.configuracion;

import controlador.configuracion.ConfiguracionIncorrecta;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesadorConfiguracion
{
  private static Pattern tuberia = Pattern.compile("tuberia");
  private static Pattern conexiones = Pattern.compile("conexiones");
  private static Pattern entidades = Pattern.compile("entidades");
  private static Pattern explosion = Pattern.compile("explosion");
  private static Pattern puerta = Pattern.compile("puerta");
  private static Pattern salida = Pattern.compile("salida");
  private static Pattern tubo = Pattern.compile("tubo\\s+(\\d+)");
  private static Pattern codoL = Pattern.compile("codol");
  private static Pattern codoNoL = Pattern.compile("codonol");
  private static Pattern conexion = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s+(\\d+)\\s+(arriba|abajo|derecha|izquierda)");
  private static Pattern entidad = Pattern.compile("(fuerza|ladooscuro|kyloren|stormtrooper|darthvader)\\s+\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
  
  private static enum Estado
  {
    INICIO,  TUBERIAS,  CONEXIONES,  ENTIDADES;
  }
  
  public static Map<String, List<List<String>>> procesar(String fichero)
    throws ConfiguracionIncorrecta, IOException
  {
    Estado estado = Estado.INICIO;
    
    Map<String, List<List<String>>> datos = new HashMap();
    
    List<List<String>> listaTuberias = new LinkedList();
    datos.put("tuberias", listaTuberias);
    List<String> ultimaTuberia = null;
    
    List<List<String>> listaConexiones = new LinkedList();
    datos.put("conexiones", listaConexiones);
    
    List<List<String>> listaEntidades = new LinkedList();
    datos.put("entidades", listaEntidades);
    
    FileReader fr = new FileReader(fichero);
    BufferedReader br = new BufferedReader(fr);
    
    String linea = "";
    int numLinea = 0;
    while ((linea = br.readLine()) != null)
    {
      numLinea++;
      
      linea = linea.trim();
      linea = linea.toLowerCase();
      if ((!linea.equals("")) && (!linea.startsWith("#")))
      {
        Matcher matcher = null;
        switch (estado)
        {
        case INICIO: 
          matcher = tuberia.matcher(linea);
          if (matcher.matches())
          {
            ultimaTuberia = new LinkedList();
            listaTuberias.add(ultimaTuberia);
            estado = Estado.TUBERIAS;
          }
          else
          {
            throw new ConfiguracionIncorrecta("Linea " + numLinea + ": " + 
              "El fichero de configuracion debe comenzar con la declaracion de una tuberia");
          }
          break;
        case TUBERIAS: 
          matcher = tuberia.matcher(linea);
          if (matcher.matches())
          {
            ultimaTuberia = new LinkedList();
            listaTuberias.add(ultimaTuberia);
          }
          else
          {
            matcher = explosion.matcher(linea);
            if (matcher.matches())
            {
              ultimaTuberia.add("explosion");
            }
            else
            {
              matcher = puerta.matcher(linea);
              if (matcher.matches())
              {
                ultimaTuberia.add("puerta");
              }
              else
              {
                matcher = salida.matcher(linea);
                if (matcher.matches())
                {
                  ultimaTuberia.add("salida");
                }
                else
                {
                  matcher = tubo.matcher(linea);
                  if (matcher.matches())
                  {
                    ultimaTuberia.add("tubo" + matcher.group(1));
                  }
                  else
                  {
                    matcher = codoL.matcher(linea);
                    if (matcher.matches())
                    {
                      ultimaTuberia.add("codol");
                    }
                    else
                    {
                      matcher = codoNoL.matcher(linea);
                      if (matcher.matches())
                      {
                        ultimaTuberia.add("codonol");
                      }
                      else
                      {
                        matcher = conexiones.matcher(linea);
                        if (matcher.matches()) {
                          estado = Estado.CONEXIONES;
                        } else {
                          throw new ConfiguracionIncorrecta("Linea " + numLinea + ": " + 
                            "Se espera una declaracion de una tubería, tramo , o la cabecera conexiones");
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          break;
        case CONEXIONES: 
          matcher = conexion.matcher(linea);
          if (matcher.matches())
          {
            LinkedList<String> conexion = new LinkedList();
            conexion.add(matcher.group(1));
            conexion.add(matcher.group(2));
            conexion.add(matcher.group(3));
            conexion.add(matcher.group(4));
            listaConexiones.add(conexion);
          }
          else
          {
            matcher = entidades.matcher(linea);
            if (matcher.matches()) {
              estado = Estado.ENTIDADES;
            } else {
              throw new ConfiguracionIncorrecta("Linea " + numLinea + ": " + 
                "Se esperaba una conexion o la cabecera entidades");
            }
          }
          break;
        case ENTIDADES: 
          matcher = entidad.matcher(linea);
          if (matcher.matches())
          {
            LinkedList<String> entidad = new LinkedList();
            entidad.add(matcher.group(1));
            entidad.add(matcher.group(2));
            entidad.add(matcher.group(3));
            listaEntidades.add(entidad);
          }
          else
          {
            throw new ConfiguracionIncorrecta("Linea " + numLinea + ": " + 
              "Se esperaba una entidad");
          }
          break;
        default: 
          throw new ConfiguracionIncorrecta("Linea " + numLinea + ": " + 
            "Declaracion no reconocida");
        }
      }
    }
    br.close();
    
    return datos;
  }
}
