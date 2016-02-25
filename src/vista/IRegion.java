package vista;

abstract interface IRegion
{
  public abstract IPunto getPosicionInferiorIzquierda();
  
  public abstract IPunto getPosicionInferiorDerecha();
  
  public abstract IPunto getPosicionSuperiorIzquierda();
  
  public abstract IPunto getPosicionSuperiorDerecha();
}
