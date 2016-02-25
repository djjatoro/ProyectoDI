package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Pantalla_AcercaDe
  extends JDialog
  implements ActionListener
{
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JPanel insetsPanel2 = new JPanel();
  JPanel insetsPanel3 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageLabel = new JLabel();
  JLabel label1 = new JLabel();
  JLabel label2 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  ImageIcon image1 = new ImageIcon();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  String product = "Proyecto de Desarrollo de Interfaces";
  String version = "Final";
  String copyright = "Curso 2015/16";
  String comments = "Teclas del Juego:\n \tIZQUIERDA: J, DERECHA: L, ARRIBA: I, ABAJO: K, ACCION: A";
  
  public Pantalla_AcercaDe(Frame parent)
  {
    super(parent);
    enableEvents(64L);
    try
    {
      jbInit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  Pantalla_AcercaDe()
  {
    this(null);
  }
  
  private void jbInit()
    throws Exception
  {
    this.image1 = new ImageIcon(Pantalla.class.getResource("about.png"));
    this.imageLabel.setIcon(this.image1);
    setTitle("Acerca de");
    this.panel1.setLayout(this.borderLayout1);
    this.panel2.setLayout(this.borderLayout2);
    this.insetsPanel1.setLayout(this.flowLayout1);
    this.insetsPanel2.setLayout(this.flowLayout1);
    this.insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.gridLayout1.setRows(4);
    this.gridLayout1.setColumns(1);
    this.label1.setText(this.product);
    this.label2.setText(this.version);
    this.label3.setText(this.copyright);
    this.label4.setText(this.comments);
    this.insetsPanel3.setLayout(this.gridLayout1);
    this.insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    this.button1.setText("Aceptar");
    this.button1.addActionListener(this);
    this.insetsPanel2.add(this.imageLabel, null);
    this.panel2.add(this.insetsPanel2, "West");
    getContentPane().add(this.panel1, null);
    this.insetsPanel3.add(this.label1, null);
    this.insetsPanel3.add(this.label2, null);
    this.insetsPanel3.add(this.label3, null);
    this.insetsPanel3.add(this.label4, null);
    this.panel2.add(this.insetsPanel3, "Center");
    this.insetsPanel1.add(this.button1, null);
    this.panel1.add(this.insetsPanel1, "South");
    this.panel1.add(this.panel2, "North");
    setResizable(true);
  }
  
  protected void processWindowEvent(WindowEvent e)
  {
    if (e.getID() == 201) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  
  void cancel()
  {
    dispose();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.button1) {
      cancel();
    }
  }
}
