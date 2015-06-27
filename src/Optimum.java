class Optimum {
	
public static double [][] C_opt, Tps_opt;
public static double transit_opt, capa_opt;
public static int nb_noeuds;
public static int origine, destination;
// constructeur
public static void RAZ (){
nb_noeuds = Trafic_Externe.nb_noeuds;
transit_opt =0.0; capa_opt =0.0;
C_opt = new double [nb_noeuds][nb_noeuds];
Tps_opt = new double [nb_noeuds][nb_noeuds];


for (origine=0; origine<nb_noeuds;origine++)
{
	for (destination=0; destination<nb_noeuds;destination++)
	{
		C_opt[origine][destination] = 0.0; Tps_opt[origine][destination] =0.0;
	}
 }
}


// Algorithme optimum par la méthode de la racine carrée
public static void calcul(){
RAZ();
for (origine=1; origine<nb_noeuds;origine++)
{
	for (destination=1; destination<nb_noeuds;destination++)
	{
		if (origine != destination)
		{
			C_opt[origine][destination] = ((Trafic_Interne.matrice_lambda[origine][destination] * Parametres.un_sur_Mu)+ 
			(((Parametres.C * (1 - (Parametres.longueur_chemin * Parametres.Rho)))/Parametres.Raclambda )* Math.sqrt(Trafic_Interne.matrice_lambda[origine][destination])));
			capa_opt = capa_opt + C_opt[origine][destination];
			if (Trafic_Interne.matrice_lambda[origine][destination] !=0) 
			{
				Tps_opt[origine][destination] = ((Parametres.un_sur_Mu) / ((C_opt[origine][destination])- (Trafic_Interne.matrice_lambda[origine][destination] * Parametres.un_sur_Mu)));
				transit_opt = (transit_opt + ((Trafic_Interne.matrice_lambda[origine][destination]*Tps_opt[origine][destination])/Parametres.gamma));
			}
		}
	}
 }
}





// affichage des résultats optimum sous forme matricielle
public static void affiche_matrice()
{

//double debit;
System.out.println (" Les capacités optimum ");
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			Parametres.conversion_debit(C_opt [origine][destination]);
			System.out.print(": ");
		}
		System.out.println ();
	}
	System.out.println ();
	System.out.print (" Capacité totale optimum C_opt = ");
	Parametres.conversion_debit(capa_opt);
	System.out.println ();
	System.out.println ();
	System.out.println (" Les temps optimum exprimés en s");
	
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (Tps_opt[origine][destination] > 0)
			{
				System.out.print(Parametres.format_double(Tps_opt [origine][destination])+ ": "); 
			}
	}
		System.out.println ();
	}

	System.out.println ();
	System.out.println (" Temps de transit optimum T_opt = "+ Parametres.format_double(transit_opt)+" s");
	System.out.println ();
}


// affichage des résultats optimum sous forme de coefficients
public static void affiche_coef()
{
	System.out.println ();
	System.out.println (" Les capacités optimum ");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if ((C_opt [origine][destination] !=0)& (destination != origine))
			{
				//System.out.printf("%2d   ",matrice_trafic [origine][destination]);  
				System.out.print("C[" + origine + "," + destination + "] = "); 
				Parametres.conversion_debit(C_opt [origine][destination]);
				System.out.println(": ");
			}
		}
	}

	System.out.println ();
	System.out.print (" Capacité totale optimum C_opt = ");
	Parametres.conversion_debit(capa_opt); System.out.println();
	System.out.println ();
	System.out.println (" Les Temps optimum ");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (Tps_opt [origine][destination] > 0)
			{
				System.out.println("T[" + origine + "," + destination + "] = " + Parametres.format_double(Tps_opt [origine][destination])+" s");
			}
		}
	}
	System.out.println ();
	System.out.println (" Temps de transit optimum T_opt = "+ Parametres.format_double(transit_opt)+" s");
	System.out.println ();
  }

}
