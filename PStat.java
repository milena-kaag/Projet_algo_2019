import java.awt.*;
import java.util.*;
import javax.swing.JPanel;


public class PStat extends JPanel{

	JPanel pCourbes=null;
	PanelImage Cartouche;
	PAxeX axeX;
	PAxeY axeY;
	Bouton retour = new Bouton("","media/retourCourbe.bmp");

	public PStat(ArrayList<Depenses> d){
		this.setLayout(new BorderLayout());
		this.setBackground(new Color (248,210,211));
		if(d.size()>1) {
			pCourbes = new Courbe(d);
		} else{
			pCourbes = new PanelImage("media/noDepense.bmp");
		}
		Cartouche= new PanelImage("media/legende.bmp");
		Cartouche.setPreferredSize(new Dimension(80,700));
		retour.setBounds(700,0,90,20);
		axeX = new PAxeX(d);
		axeX.add(retour);
		axeY = new PAxeY(d);
		this.add(axeX,BorderLayout.SOUTH);
		this.add(axeY,BorderLayout.WEST);
		this.add(Cartouche,BorderLayout.EAST);
		this.add(pCourbes,BorderLayout.CENTER);
		this.repaint();
	}



}
class PAxeX extends JPanel{
	double dateInitiale;
	public PAxeX(ArrayList<Depenses> d){
		this.setPreferredSize(new Dimension(700,40));
		this.setBackground(new Color(226,241,254));
		this.setLayout(null);
		dateInitiale=d.get(0).getDate();
		System.out.println(dateInitiale);
		System.out.println("je suis dateInitiale  "+dateInitiale);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		tracerAxeX(g);
		
	}
	
	public void tracerAxeX(Graphics g){
		g.drawLine(40, this.getHeight()/4, this.getWidth()-80, this.getHeight()/4);
		for (int i=0; i<((this.getWidth())-80)/50; i++){
			 g.drawLine(i*50+40,0 , i*50+40 ,this.getHeight()/4 );
			 g.drawString( Integer.toString(i*50), i*50+40, 3*this.getHeight()/4);
		 }
	}


}

class PAxeY extends JPanel{
	
	public PAxeY(ArrayList<Depenses> d){
		this.setBackground(new Color (226,241,254));
		this.setPreferredSize(new Dimension(40,680));
		this.setLayout(null);
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		tracerAxeY(g);
		
	}
	
	public void tracerAxeY(Graphics g){
		g.drawLine(3*this.getWidth()/4, 0, 3*this.getWidth()/4, this.getHeight());
		for (int i=0; i<(this.getHeight())/50; i++){
			 g.drawLine(3*this.getWidth()/4,this.getHeight()-(i*50)-1,this.getWidth() , this.getHeight()-(i*50)-1);
			 g.drawString( Integer.toString(i*50), this.getWidth()/4,this.getHeight()-(i*50));
		 }
	}


}
