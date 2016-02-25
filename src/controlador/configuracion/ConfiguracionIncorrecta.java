package controlador.configuracion;

public class ConfiguracionIncorrecta
  extends Exception
{
  public ConfiguracionIncorrecta(String mensaje)
  {
    super(mensaje);
  }
  
  public String getMensaje()
  {
    return getMessage();
  }
}
