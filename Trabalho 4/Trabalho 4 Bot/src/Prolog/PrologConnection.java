package Prolog;

import org.jpl7.*;
import java.lang.System;
import java.util.Map;

public class PrologConnection {
	
	Atom testePl = new Atom("trabalho4.pl"); 
	
	public boolean connectProlog() {
		
		Term[] vet = new Term[] {testePl};
		
		Query q1 = new Query("consult", vet);
		
		try{
			
			 q1.hasSolution();
			 return true;
			 
		} catch(Exception e) {
			
			return false;
			
		}
		
	}
	
	public String consult() {
			
		Query q2 = new Query("melhor_movimento(X)");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null) 
			return solution[0].get("X").toString(); 

		
		return "";
		
	}
	
	public String custo() {
		
		Query q2 = new Query("custo(X)");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null) 
			return solution[0].get("X").toString(); 

		
		return "";
		
	}
	
	public String vida() {
		
		Query q2 = new Query("energia(X)");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null) 
			return solution[0].get("X").toString(); 

		
		return "";
		
	}
	
	public String tiros() {
		
		Query q2 = new Query("tiros(X)");
		
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null) 
			return solution[0].get("X").toString(); 

		
		return "";
		
	}
	
	public void reset() {
		
		this.setTemPowerUp("nao");
		this.setTemTesouro("nao");
		this.setTemParede("nao", "west");
		this.setTemBuraco("nao");
		
		for(int i=0; i<59; i++) {
			for(int j=0; i<34; i++) {
				retractMemoria(i, j, "nao");
			}
		}
		
		for(int i=0; i<59; i++) {
			for(int j=0; i<34; i++) {
				assertMemoria(i, j, "nao");
			}
		}
		
	}
	
	private void assertMemoria(int x, int y, String conhecimento) {
	
		Query q2 = new Query("assert(memoria("+ x +", "+ y +", "+ conhecimento +")).");
		
		q2.allSolutions();
		
	}
	
	private void retractMemoria(int x, int y, String conhecimento) {
		
		Query q2 = new Query("retract(memoria("+ x +", "+ y +", "+ conhecimento +")).");
		
		q2.allSolutions();
		
	}
	
	public void teste() {
		
		Term[] vet = new Term[] {testePl};
		
		Query q1 = new Query("consult", vet);
	    System.out.println("consult " + (q1.hasSolution() ? "succeeded" : "failed"));
		Query q2 = new Query("ancestral(X, jose)");

		Map<String, Term>[] solution = q2.allSolutions();		
		if (solution != null) 
		{	
			for (int i = 0; i < solution.length; i++)
				System.out.println("X = " + solution[i].get("X"));
		}
		
	}

	/******************************************************************
	**
	** Posi��o
	**
	*******************************************************************/
	
	public void setPosition(int x, int y, String dir) {
		
		removeAllPosition();
		
		Query q2 = new Query("assert(posicao(" + x + ", " + y + ", " + dir + ")).");
		
		q2.allSolutions();
		
	}
	
	public String getPosition() {
		
		Query q2 = new Query("posicao(X, Y, Z)");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null)  {
			return "POSICAO PROLOG: (" + solution[0].get("X").toString() + ", " +  solution[0].get("Y").toString() + ", " + solution[0].get("Z").toString() + ") "; 
		}

		return "";
		
	}
	
	public void removeAllPosition() {
		
		Query q2 = new Query("retract(posicao(_,_,_)).");
		
		q2.allSolutions();
		
	}
	
	/******************************************************************
	**
	** Tem Parede
	**
	*******************************************************************/
	
	public void setTemParede(String x, String dir) {
		
		removeAllTemParede();
		
		Query q2 = new Query("assert(temParede(" + x + ", " + dir + ")).");
		
		q2.allSolutions();
		
	}
	
	public String getTemParede() {
		
		Query q2 = new Query("temParede(X, Y).");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null)  {
			return "TEM PAREDE PROLOG: " + solution[0].get("X").toString() + ", " + solution[0].get("Y").toString(); 
		}

 		return "";
		
	}
	
	public void removeAllTemParede() {
		
		Query q2 = new Query("retract(temParede(_, _)).");
		
		q2.allSolutions();
		
	}
	
	/******************************************************************
	**
	** Tem Tesouro
	**
	*******************************************************************/
	
	public void setTemTesouro(String x) {
		
		removeAllTemTesouro();
		
		Query q2 = new Query("assert(temTesouro(" + x + ")).");
		
		q2.allSolutions();
		
	}
	
	public String getTemTesouro() {
		
		Query q2 = new Query("temTesouro(X).");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null)  {
			return "TEM TESOURO PROLOG: " + solution[0].get("X").toString(); 
		}

 		return "";
		
	}
	
	public void removeAllTemTesouro() {
		
		Query q2 = new Query("retract(temTesouro(_)).");
		
		q2.allSolutions();
		
	}
	
	/******************************************************************
	**
	** Tem Power Up
	**
	*******************************************************************/
	
	public void setTemPowerUp(String x) {
		
		removeAllTemPowerUp();
		
		Query q2 = new Query("assert(temPowerUp(" + x + ")).");
		
		q2.allSolutions();
		
	}
	
	public String getTemPowerUp() {
		
		Query q2 = new Query("temPowerUp(X).");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null)  {
			return "TEM POWER UP PROLOG: " + solution[0].get("X").toString(); 
		}

 		return "";
		
	}
	
	public void removeAllTemPowerUp() {
		
		Query q2 = new Query("retract(temPowerUp(_)).");
		
		q2.allSolutions();
		
	}
	
	/******************************************************************
	**
	** Atirar
	**
	*******************************************************************/
	
	public void setAtirar(String x) {
		
		removeAllAtirar();
		
		Query q2 = new Query("assert(atirar(" + x + ")).");
		
		q2.allSolutions();
		
	}
	
	public String getAtirar() {
		
		Query q2 = new Query("atirar(X).");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null)  {
			return "ATIRAR PROLOG: " + solution[0].get("X").toString(); 
		}

 		return "";
		
	}
	
	public void removeAllAtirar() {
		
		Query q2 = new Query("retract(atirar(_)).");
		
		q2.allSolutions();
		
	}
	
	/******************************************************************
	**
	** Limite de tiros
	**
	*******************************************************************/
	
	public void addQtdVezesQueAtiramos() {
		
		Query q2 = new Query("add_qtdVezesAtiramos");
			
		q2.allSolutions();
		
	}
	
	public void resetarQtdVezesQueAtiramos() {
		
		Query q2 = new Query("reseta_qtdVezesAtiramos");
			
		q2.allSolutions();
		
	}

	/******************************************************************
	**
	** Tem Buraco
	**
	*******************************************************************/

	public void setTemBuraco(String x) {
		
		removeAllTemBuraco();
		
		Query q2 = new Query("assert(temBuraco(" + x + ")).");
		
		q2.allSolutions();
		
	}
	
	public String getTemBuraco() {
		
		Query q2 = new Query("temBuraco(X).");
			
		Map<String, Term>[] solution = q2.allSolutions();
			
		if (solution != null)  {
			return "TEM BURACO PROLOG: " + solution[0].get("X").toString(); 
		}

 		return "";
		
	}
	
	public void removeAllTemBuraco() {
		
		Query q2 = new Query("retract(temBuraco(_)).");
		
		q2.allSolutions();
		
	}
	
}
