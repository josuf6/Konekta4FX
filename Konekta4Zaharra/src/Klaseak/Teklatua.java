package Klaseak;

import java.util.Scanner;

public class Teklatua {
	//atributuak
	private Scanner sc;
	private static Teklatua nireTeklatua=null;
	

	//eraikitzailea(k)
	private Teklatua() {
		this.sc=new Scanner(System.in);
	}

	//gainontzeko metodoak
	public static Teklatua getNireTeklatua() {
		if (nireTeklatua==null) {
			nireTeklatua=new Teklatua();
		}
		return nireTeklatua;
	}
	
	public int irakurriOsoa() {
		String zenb=this.irakurriString();
		boolean egokia=false;
		do {
			if (zenb.isEmpty()) {
				zenb=this.irakurriString();
			}
			else {
				try {
					Integer.parseInt(zenb);
					egokia=true;
				}
				catch (NumberFormatException e) {
					System.out.println("Bakarrik zenbaki osoak onartzen dira.");
					zenb=this.irakurriString();
				}
			}
		} while (!egokia);
		return Integer.parseInt(zenb);
	}
	
	public String irakurriString() {
		String mezua=this.sc.nextLine();
		return mezua;
	}
	
	public char irakurriChar() {
		String karakterea=this.irakurriString();
		boolean egokia=false;
		do {
			if (karakterea.length()>1) {
				System.out.println("Bakarrik karaktereak onartzen dira.");
				karakterea=this.irakurriString();
			}
			else {
				egokia=true;
			}
		} while (!egokia);
		return karakterea.charAt(0);
	}
	
	public void irakurriEnter() {
		this.sc.nextLine();
	}
}