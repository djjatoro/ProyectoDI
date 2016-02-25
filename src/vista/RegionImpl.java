package vista;

class RegionImpl
  implements IRegion
{
  private IPunto vertice;
  private int ladoY;
  private int ladoX;
  
  public RegionImpl(IPunto p, int ladoX, int ladoY)
  {
    this(p.getCoordenadaX(), p.getCoordenadaY(), ladoX, ladoY);
  }
  
  public RegionImpl(int x, int y, int ladoX, int ladoY)
  {
    this.vertice = new PuntoImpl(x, y);
    this.ladoX = ladoX;
    this.ladoY = ladoY;
  }
  
  public IPunto getPosicionInferiorDerecha()
  {
    return new PuntoImpl(this.vertice.getCoordenadaX() + this.ladoX, this.vertice.getCoordenadaY());
  }
  
  public IPunto getPosicionInferiorIzquierda()
  {
    return new PuntoImpl(this.vertice.getCoordenadaX(), this.vertice.getCoordenadaY());
  }
  
  public IPunto getPosicionSuperiorDerecha()
  {
    return new PuntoImpl(this.vertice.getCoordenadaX() + this.ladoX, this.vertice.getCoordenadaY() + this.ladoY);
  }
  
  public IPunto getPosicionSuperiorIzquierda()
  {
    return new PuntoImpl(this.vertice.getCoordenadaX(), this.vertice.getCoordenadaY() + this.ladoY);
  }
}