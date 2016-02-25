package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.PrintStream;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Pantalla
  extends JFrame
  implements IPantalla
{
  public static final int TASA_REFRESCO = 25;
  public static final int PIXELES_UNIDAD = 30;
  private int tasa;
  private int pixelesUnidad;
  
  private class Hilo
    extends Thread
  {
    private int periodo;
    
    public Hilo(int tasa)
    {
      this.periodo = (1000 / tasa);
    }
    
    public void run()
    {
      while (!interrupted())
      {
        try
        {
          Thread.sleep(this.periodo);
        }
        catch (InterruptedException e)
        {
          break;
        }
        Pantalla.this.actualizaTodo();
      }
    }
  }
  
  private int anchoEscenario = 0;
  private int altoEscenario = 0;
  private IControlador controlador;
  private Thread hiloRefresco;
  private LocalizadorImagenes localizador;
  private Image noImagen;
  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jButtonNueva = new JButton();
  JButton jButtonAbrir = new JButton();
  ImageIcon imageNueva;
  ImageIcon imageAbrir;
  JLabel statusBar = new JLabel();
  Border border1;
  JPanel escenario = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  private Color colorSuelo;
  
  public Pantalla(IControlador controlador, int tasaRefresco, int pixelesUnidad)
  {
    if (controlador == null) {
      throw new IllegalArgumentException(
        "Error gráfico: el controlador no puede ser nulo");
    }
    if ((tasaRefresco <= 0) || (tasaRefresco > 1000)) {
      throw new IllegalArgumentException(
        "Error gráfico: la tasa de refresco debe estar entre 1 y 1000");
    }
    if (pixelesUnidad <= 0) {
      throw new IllegalArgumentException(
        "Error gráfico: el valor de píxeles por unidad debe ser mayor que 0");
    }
    this.controlador = controlador;
    
    this.tasa = tasaRefresco;
    
    this.pixelesUnidad = pixelesUnidad;
    
    this.hiloRefresco = new Hilo(tasaRefresco);
    
    this.localizador = new LocalizadorImagenes(new File("imagenes/"), 
      this);
    
    this.noImagen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("no-imagen.png"));
    
    this.colorSuelo = Color.LIGHT_GRAY;
    
    MediaTracker tracker = new MediaTracker(this);
    tracker.addImage(this.noImagen, 0);
    try
    {
      tracker.waitForAll();
    }
    catch (InterruptedException localInterruptedException) {}
    enableEvents(64L);
    try
    {
      jbInit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    setVisible(true);
  }
  
  public Pantalla(IControlador controlador)
  {
    this(controlador, 25, 50);
  }
  
  private void actualizaTodo()
  {
    if (!this.controlador.dibujarEscenario()) {
      return;
    }
    int ancho = this.controlador.getAnchoEscenario() * this.pixelesUnidad;
    int alto = this.controlador.getAltoEscenario() * this.pixelesUnidad;
    if ((ancho <= 0) || (alto <= 0))
    {
      System.err.println("Error gráfico: el escenario tiene dimensiones negativas. No se actualiza");
      return;
    }
    if ((this.anchoEscenario != ancho) || (this.altoEscenario != alto))
    {
      this.anchoEscenario = ancho;
      this.altoEscenario = alto;
      redimensionar();
    }
    Graphics2D original = (Graphics2D)this.escenario.getGraphics();
    VolatileImage buffer = createVolatileImage(this.anchoEscenario, this.altoEscenario);
    Graphics grafico = buffer.getGraphics();
    
    grafico.setPaintMode();
    grafico.setColor(this.colorSuelo);
    grafico.fillRect(0, 0, this.escenario.getWidth(), this.escenario.getHeight());
    
    int diametroX = this.controlador.getAnchoEscenario() / 2;
    int diametroY = this.controlador.getAltoEscenario() / 2;
    IRegion region = calcularRegion(diametroX, diametroY);
    IPunto origen = region.getPosicionInferiorIzquierda();
    
    dibujarCeldas(grafico, region, origen);
    dibujarEntidades(grafico, region, origen);
    
    original.drawImage(buffer, 0, 0, this);
  }
  
  private IRegion calcularRegion(int diametroX, int diametroY)
  {
    IPunto p = new PuntoImpl(this.controlador.getPosicionXJugador(), 
      this.controlador.getPosicionYJugador());
    return new RegionImpl(p.getCoordenadaX() - diametroX, 
      p.getCoordenadaY() - diametroY, 
      this.controlador.getAnchoEscenario(), this.controlador.getAltoEscenario());
  }
  
  private void dibujarCeldas(Graphics grafico, IRegion region, IPunto origen)
  {
    Collection<Dibujable> todas = this.controlador.getCeldasDibujables();
    if (todas == null) {
      System.err.println("Error gráfico: la colección de objetos dibujables 2D es nula.");
    } else {
      for (Dibujable item : todas)
      {
        Image imagen = this.localizador.localiza(item.getImagen());
        if (imagen == null)
        {
          System.err.println("Error gráfico: no se encuentra la imagen de la celda");
          System.out.println("Se muestra la imagen por defecto");
          imagen = this.noImagen;
        }
        int menorX = item.getPosicionX();
        int menorY = item.getPosicionY();
        int ladoX = 1;
        int ladoY = 1;
        
        int posX = menorX - origen.getCoordenadaX();
        int posY = menorY - origen.getCoordenadaY();
        if ((posX >= 0) && (posY >= 0))
        {
          grafico.setColor(Color.BLACK);
          grafico.drawRect(posX * this.pixelesUnidad, 
            this.altoEscenario - (posY + ladoY) * this.pixelesUnidad, ladoX * this.pixelesUnidad, 
            ladoY * this.pixelesUnidad);
          
          grafico.drawImage(imagen, posX * this.pixelesUnidad + 1, 
            this.altoEscenario - (posY + ladoY) * this.pixelesUnidad + 1, ladoX * this.pixelesUnidad - 1, 
            ladoY * this.pixelesUnidad - 1, null);
        }
      }
    }
  }
  
  private void dibujarEntidades(Graphics grafico, IRegion region, IPunto origen)
  {
    Collection<Dibujable> todas = this.controlador.getEntidadesDibujables();
    if (todas == null) {
      System.err.println("Error gráfico: la colección de objetos dibujables 2D es nula.");
    } else {
      for (Dibujable item : todas)
      {
        Image imagen = this.localizador.localiza(item.getImagen());
        if (imagen == null)
        {
          System.err.println("Error gráfico: no se encuentra la imagen --> " + item.getImagen());
          System.out.println("Se muestra la imagen por defecto");
          imagen = this.noImagen;
        }
        int menorX = item.getPosicionX();
        int menorY = item.getPosicionY();
        int ladoX = 1;
        int ladoY = 1;
        
        int posX = menorX - origen.getCoordenadaX();
        int posY = menorY - origen.getCoordenadaY();
        if ((posX >= 0) && (posY >= 0)) {
          grafico.drawImage(imagen, posX * this.pixelesUnidad + 1, 
            this.altoEscenario - (posY + ladoY) * this.pixelesUnidad + 1, ladoX * this.pixelesUnidad - 1, 
            ladoY * this.pixelesUnidad - 1, null);
        }
      }
    }
  }
  
  private void jbInit()
    throws Exception
  {
    this.imageNueva = new ImageIcon(Pantalla.class.getResource("nueva.gif"));
    this.imageAbrir = new ImageIcon(Pantalla.class.getResource("abrir.png"));
    
    this.contentPane = ((JPanel)getContentPane());
    this.contentPane.setLayout(this.borderLayout1);
    
    this.border1 = new EtchedBorder(0, Color.white, new Color(
      165, 163, 151));
    this.statusBar.setBorder(this.border1);
    this.statusBar.setText(" ");
    
    setResizable(false);
    setSize(new Dimension(400, 400));
    setTitle("Proyecto de Desarrollo de Interfaces");
    
    validate();
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    setLocation((screenSize.width - frameSize.width) / 2, 0);
    
    this.jMenuFile.setText("Archivo");
    this.jMenuFileExit.setText("Salir");
    this.jMenuFileExit
      .addActionListener(new Pantalla_jMenuFileExit_ActionAdapter(
      this));
    
    this.jMenuHelp.setText("Ayuda");
    this.jMenuHelpAbout.setText("Acerca de");
    this.jMenuHelpAbout
      .addActionListener(new Pantalla_jMenuHelpAbout_ActionAdapter(
      this));
    
    this.jButtonNueva.setIcon(this.imageNueva);
    this.jButtonNueva.addActionListener(new Pantalla_jButtonNueva_actionAdapter(
      this));
    this.jButtonNueva.setToolTipText("Nueva Partida");
    
    this.jButtonAbrir.setIcon(this.imageAbrir);
    this.jButtonAbrir.addActionListener(new Pantalla_jButtonAbrir_actionAdapter(
      this));
    this.jButtonAbrir.setToolTipText("Elegir Fichero");
    
    this.jToolBar.add(this.jButtonNueva);
    this.jToolBar.add(this.jButtonAbrir);
    
    this.jMenuFile.add(this.jMenuFileExit);
    this.jMenuHelp.add(this.jMenuHelpAbout);
    this.jMenuBar1.add(this.jMenuFile);
    this.jMenuBar1.add(this.jMenuHelp);
    setJMenuBar(this.jMenuBar1);
    
    this.escenario.setBackground(Color.WHITE);
    this.escenario.setSize(1500, 1500);
    
    InputMap imap = ((JPanel)getContentPane())
      .getInputMap(1);
    
    ActionMap amap = ((JPanel)getContentPane()).getActionMap();
    
    KeyStroke arriba = KeyStroke.getKeyStroke("I");
    imap.put(arriba, "arriba");
    
    amap.put("arriba", new AbstractAction()
    {
      public void actionPerformed(ActionEvent a)
      {
        Pantalla.this.controlador.mueveArriba();
      }
    });
    KeyStroke izquierda = KeyStroke.getKeyStroke("J");
    imap.put(izquierda, "izquierda");
    
    amap.put("izquierda", new AbstractAction()
    {
      public void actionPerformed(ActionEvent a)
      {
        Pantalla.this.controlador.mueveIzquierda();
      }
    });
    KeyStroke derecha = KeyStroke.getKeyStroke("L");
    imap.put(derecha, "derecha");
    amap.put("derecha", new AbstractAction()
    {
      public void actionPerformed(ActionEvent a)
      {
        Pantalla.this.controlador.mueveDerecha();
      }
    });
    KeyStroke abajo = KeyStroke.getKeyStroke("K");
    imap.put(abajo, "abajo");
    amap.put("abajo", new AbstractAction()
    {
      public void actionPerformed(ActionEvent a)
      {
        Pantalla.this.controlador.mueveAbajo();
      }
    });
    KeyStroke accion = KeyStroke.getKeyStroke("A");
    imap.put(accion, "accion");
    amap.put("accion", new AbstractAction()
    {
      public void actionPerformed(ActionEvent a)
      {
        Pantalla.this.controlador.accion();
      }
    });
    this.contentPane.add(this.jToolBar, "North");
    this.contentPane.add(this.escenario, "Center");
    this.contentPane.add(this.statusBar, "South");
  }
  
  public void setBarraEstado(String mensaje)
  {
    if (mensaje == null) {
      throw new IllegalArgumentException(
        "Error gráfico: la cadena para establecer la barra de estado es nula");
    }
    this.statusBar.setText(mensaje);
  }
  
  public void abrirDialogo(String titulo, String mensaje)
  {
    if (titulo == null) {
      throw new IllegalArgumentException(
        "Error gráfico: el título del diálogo es nulo");
    }
    if (mensaje == null) {
      throw new IllegalArgumentException(
        "Error gráfico: el mensaje del diálogo es nulo");
    }
    JOptionPane.showMessageDialog(null, titulo, mensaje, 
      1);
    
    actualizaTodo();
  }
  
  private void redimensionar()
  {
    int desfaseAncho = getWidth() - this.escenario.getWidth();
    
    int desfaseAlto = getHeight() - this.escenario.getHeight();
    
    int anchoEscenario = this.controlador.getAnchoEscenario() * this.pixelesUnidad;
    
    int altoEscenario = this.controlador.getAltoEscenario() * this.pixelesUnidad;
    
    setSize(anchoEscenario + desfaseAncho, altoEscenario + 
      desfaseAlto);
    
    this.escenario.setSize(anchoEscenario, altoEscenario);
    
    setVisible(true);
  }
  
  void jMenuFileExit_actionPerformed(ActionEvent e)
  {
    System.exit(0);
  }
  
  void jMenuHelpAbout_actionPerformed(ActionEvent e)
  {
    Pantalla_AcercaDe dlg = new Pantalla_AcercaDe(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, 
      (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }
  
  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == 201) {
      jMenuFileExit_actionPerformed(null);
    }
  }
  
  int valor = 0;
  
  void jButtonNueva_actionPerformed(ActionEvent e)
  {
    this.hiloRefresco.interrupt();
    while (this.hiloRefresco.isAlive()) {}
    this.controlador.nueva();
    
    this.hiloRefresco = new Hilo(this.tasa);
    this.hiloRefresco.start();
  }
  
  void jButtonAbrir_actionPerformed(ActionEvent e)
  {
    this.hiloRefresco.interrupt();
    while (this.hiloRefresco.isAlive()) {}
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));
    
    int opcion = chooser.showOpenDialog(this);
    if (opcion == 0)
    {
      String ruta = chooser.getSelectedFile().getAbsolutePath();
      
      this.controlador.abrir(ruta);
    }
  }
  
  class Pantalla_jMenuFileExit_ActionAdapter
    implements ActionListener
  {
    Pantalla adaptee;
    
    Pantalla_jMenuFileExit_ActionAdapter(Pantalla adaptee)
    {
      this.adaptee = adaptee;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      this.adaptee.jMenuFileExit_actionPerformed(e);
    }
  }
  
  class Pantalla_jMenuHelpAbout_ActionAdapter
    implements ActionListener
  {
    Pantalla adaptee;
    
    Pantalla_jMenuHelpAbout_ActionAdapter(Pantalla adaptee)
    {
      this.adaptee = adaptee;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      this.adaptee.jMenuHelpAbout_actionPerformed(e);
    }
  }
  
  class Pantalla_jButtonNueva_actionAdapter
    implements ActionListener
  {
    Pantalla adaptee;
    
    Pantalla_jButtonNueva_actionAdapter(Pantalla adaptee)
    {
      this.adaptee = adaptee;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      this.adaptee.jButtonNueva_actionPerformed(e);
    }
  }
  
  class Pantalla_jButtonAbrir_actionAdapter
    implements ActionListener
  {
    Pantalla adaptee;
    
    Pantalla_jButtonAbrir_actionAdapter(Pantalla adaptee)
    {
      this.adaptee = adaptee;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      this.adaptee.jButtonAbrir_actionPerformed(e);
    }
  }
}
