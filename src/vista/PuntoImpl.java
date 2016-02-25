package vista;

class PuntoImpl
  implements IPunto
{
  private int y;
  private int x;
  
  public PuntoImpl(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  public int getCoordenadaX()
  {
    return this.x;
  }
  
  public int getCoordenadaY()
  {
    return this.y;
  }
  
  public void incX(int valor)
  {
    this.x += valor;
  }
  
  public void incY(int valor)
  {
    this.y += valor;
  }
}