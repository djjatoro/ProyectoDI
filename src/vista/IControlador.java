package vista;

import java.util.Collection;

public abstract interface IControlador
{
  public abstract int getAnchoEscenario();
  
  public abstract int getAltoEscenario();
  
  public abstract Collection<Dibujable> getEntidadesDibujables();
  
  public abstract Collection<Dibujable> getCeldasDibujables();
  
  public abstract void mueveIzquierda();
  
  public abstract void mueveDerecha();
  
  public abstract void mueveAbajo();
  
  public abstract void mueveArriba();
  
  public abstract void accion();
  
  public abstract int getPosicionXJugador();
  
  public abstract int getPosicionYJugador();
  
  public abstract void nueva();
  
  public abstract void abrir(String paramString);
  
  public abstract boolean dibujarEscenario();
  
  public abstract void setPantalla(IPantalla paramIPantalla);
}