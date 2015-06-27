import java.util.Scanner;

class Parametres {
public static String unite;
public static int nb_noeuds, nb_liaisons;
public static double un_sur_Mu, lambda, gamma, longueur_chemin, C, Rho, Raclambda;
public static int reponse = 0;
public static int origine, destination;


// Calcul des paramètres pour les algorithmes
	public static void calcul()
	{
	Scanner lire = new Scanner(System.in);
	int reponse = 0;
	unite = "";
	nb_noeuds = Trafic_Externe.nb_noeuds;
	gamma =0.0; lambda = 0.0; Raclambda = 0.0; nb_liaisons = 0; Rho = 0.0; un_sur_Mu = 0.0; longueur_chemin = 0.0; C = 0.0;
	for (origine = 1; origine<nb_noeuds;origine++){
		for (destination=1; destination<nb_noeuds;destination++)
		{
			gamma = gamma + Trafic_Externe.matrice_trafic[origine][destination];
			lambda = lambda + Trafic_Interne.matrice_lambda[origine][destination];
			Raclambda = Raclambda + Math.sqrt(Trafic_Interne.matrice_lambda[origine][destination]);
				if (Trafic_Interne.matrice_lambda[origine][destination] != 0)
				{
					nb_liaisons++;
				}
		}
	}
	System.out.print(" donner l'unité de référence (bits, Kbits, Mbits, octets, Koctets, Moctets) : ");
	unite = lire.next();
	System.out.print ("Donner la taille moyenne des paquets dans l'unité choisie : "); 
	un_sur_Mu = Clavier.lireDouble();
	System.out.println();
	System.out.print(" Connaissez-vous la capacité globale C ( = 0) ou la charge Rho ( = 1) :"); 
	reponse = lire.nextInt(); 
	System.out.println();
	
		if (reponse == 0) 
		{
			System.out.println(" la valeur de C doit être supérieure à "+ (lambda * un_sur_Mu));
			System.out.print(" donner la valeur de C dans l'unité choisie = ");
			C = Clavier.lireDouble(); 
			System.out.println();
		}
		else
		{
			System.out.println(" la valeur de Rho doit être positive et inférieure à " + (gamma/lambda));
			System.out.print(" donner la valeur de Rho = ");
			Rho = Clavier.lireDouble(); 
			System.out.println();
		}
			longueur_chemin = lambda/gamma;
	    if (reponse == 0)
	    {
	    	Rho = ((un_sur_Mu * gamma) / C);
	    }
	    else 
	    {
	    	C = ((un_sur_Mu * gamma)/Rho);
	    }
	
	    affichage();
	    
	}

// Affichage des paramètres du problème
	public static void affichage()
	{
		System.out.print(" la valeur de C = "+ format_double(C)+ " " + unite+"/s soit ");
		conversion_debit(C);
		System.out.println();
		System.out.println(" la valeur de Rho = "+ format_double(Rho));
		System.out.println(" la valeur de Gamma = "+ format_double(gamma)+ " messages/s");
		System.out.println(" la valeur de Lambda = "+ format_double(lambda)+ " messages/s");
		System.out.println(" la valeur de RacLambda = "+ format_double(Raclambda));
		System.out.println(" la valeur de n = "+ format_double(longueur_chemin));
		System.out.println(" la valeur de 1/Mu = "+ format_double(un_sur_Mu)+ " " + unite);
		System.out.println(" nombre de liaisons M = "+ nb_liaisons);
		System.out.println();
	}
	
	// Formattage d'un double avec trois chiffres derrière la virgule
	public static double format_double (double valeur){
		double val1 = valeur * 1000;
		long val2 = (long) val1;
	
		return (double)val2/1000;
	}
	
	// conversion des unités de débit
	public static void conversion_debit (double valeur) {
	double y = valeur; 
	int puissance = 0;
	String coefficient ="";
		while (Math.ceil(y)> 1000) {
			y = y / 1000;
		puissance++;
		}
		switch (puissance){
			case 0 : {coefficient = " "; break;}
			case 1 : {coefficient = " K"; break;}
			case 2 : {coefficient = " M"; break;}
			case 3 : {coefficient = " T"; break;}
		}
		//System.out.print(" " + format_double(Math.ceil(y)) + coefficient+unite+"/s" );
		System.out.printf("%2f   ", format_double(Math.ceil(y)) , coefficient,unite,"/s");  
		//System.out.printf("%2d   ",matrice_trafic [origine][destination]);  
	}
}