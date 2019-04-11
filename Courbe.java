import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JPanel;

public class Courbe extends JPanel{ 

	ArrayList<Depenses> dep = new ArrayList<Depenses>();
	double[] tmp;
	double[] mont;
	double[] polynomes;
	double[] coorLeastSaquares;
	double x;
	double y;
	
	public Courbe (ArrayList<Depenses> d){
		this.setLayout(null);
		this.setBackground(Color.white);
		dep=d;
		tmp= new double[dep.size()];
		mont= new double[dep.size()];
		x=dep.get(0).getDate();
		y=665/2 + dep.get(0).getAmount();
		for (int i=0; i<dep.size(); i++){
			tmp[i]=(dep.get(i).getDate()) -x;
			mont[i]=(dep.get(i).getAmount()+ y);
		}
		
		Interpolationv2 inter=new Interpolationv2(tmp,mont);
		polynomes = inter.getCoeff();
		System.out.print(polynomes[0]);
		LeastSquares approximation=new LeastSquares(tmp,mont);
		coorLeastSaquares= approximation.tabCourbe();
	}
	

	
	public void tracerPoint(Graphics g){
		
		g.setColor(Color.red);
		for(int i=0; i<dep.size();i++){
			
			g.fillOval((int)tmp[i]-5, this.getHeight()-(int)mont[i]-5, 10, 10);
		}
	}
	
	public void tracerCourbe(Graphics g){
		g.setColor(Color.black);
		for(int i=0; i<tmp.length-1;i++){
			
			g.drawLine((int)tmp[i], this.getHeight()-(int)mont[i], (int)tmp[i+1], this.getHeight()-(int)mont[i+1]);
		}
	}
	
	public void tracerCourbeLagrange(Graphics g){
		double y1=0;
		double y2=0;
		g.setColor(Color.blue);
		for (int i=1; i<801;i++){
			for (int j=0; j<dep.size();j++){
				y1=y1+(Math.pow(i,j))*(polynomes[j]);
				y2=y2+(Math.pow((i+1),j))*(polynomes[j]);
			}
			g.drawLine(i, this.getHeight()-(int)y1, i+1, this.getHeight()-(int)y2);
			y1=0;
			y2=0;
		}
	}
	
	public void tracerCourbeLeastSquares(Graphics g){
		double a=coorLeastSaquares[0];
		int b=(int)coorLeastSaquares[1];
		g.setColor(Color.green);
		g.drawLine(0, this.getHeight()-b, this.getWidth(), this.getHeight()-((int)(a*this.getWidth()) + b));
	}
		
	public void paintComponent (Graphics g){
		tracerPoint(g);
		tracerCourbe(g);
		tracerCourbeLagrange(g);
		tracerCourbeLeastSquares(g);
	}
	
	
}
		

//drawLine(int x1, int y1, int x2, int y2)
