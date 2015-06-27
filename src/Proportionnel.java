class Proportionnel {
public static double [][] C_prop, Tps_prop;
public static double transit_prop, capa_prop;
public static int nb_noeuds;
public static int origine, destination;
// constructeur
public static void RAZ ()
{
	nb_noeuds = Trafic_Externe.nb_noeuds;
	transit_prop =0.0; capa_prop =0.0;
	C_prop = new double [nb_noeuds][nb_noeuds];
	Tps_prop = new double [nb_noeuds][nb_noeuds];
	for (origine=0; origine<nb_noeuds;origine++)
	{
		for (destination=0; destination<nb_noeuds;destination++)
		{
			C_prop[origine][destination] = 0.0; 
			Tps_prop[origine][destination] = 0.0;
		}
	}
}
// algorithme proportionnel
public static void calcul()
{
	RAZ();
	for (origine =0; origine<nb_noeuds;origine++)
	{
		for (destination =0; destination<nb_noeuds;destination++)
		{
			if (origine != destination)
			{
				C_prop[origine][destination] = ((Trafic_Interne.matrice_lambda[origine][destination] * Parametres.un_sur_Mu)+ (((Parametres.C * (1 - (Parametres.longueur_chemin * Parametres.Rho)))/Parametres.lambda )*Trafic_Interne.matrice_lambda[origine][destination]));
				capa_prop = capa_prop + C_prop[origine][destination];
				
				if (Trafic_Interne.matrice_lambda[origine][destination] !=0)
				{
					Tps_prop[origine][destination] = ((Parametres.lambda * Parametres.un_sur_Mu) / ((Parametres.C*(1-(Parametres.longueur_chemin * Parametres.Rho)))*Trafic_Interne.matrice_lambda[origine][destination]));
				}
			}
		}
	}
    transit_prop = ((Parametres.longueur_chemin * Parametres.nb_liaisons * Parametres.un_sur_Mu)/(Parametres.C * (1 - (Parametres.longueur_chemin * Parametres.Rho))));
}
// affichage des résultats proportionnels
public static void affiche_matrice()
{
	System.out.println (" Les capacités proportionnelles ");
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			Parametres.conversion_debit(C_prop [origine][destination]);System.out.print(": ");
		}
		System.out.println ();
	}
	System.out.println ();
	System.out.print (" Capacité totale proportionnelle C_prop = "); Parametres.conversion_debit(capa_prop);System.out.println ();
	System.out.println ();
	System.out.println (" Les temps propoptionnels exprimés en s ");
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (Tps_prop[origine][destination] > 0)
				System.out.print(Parametres.format_double(Tps_prop [origine][destination])+ ": ");
		}
	    System.out.println ();
	}	
	System.out.println ();
	System.out.println (" Temps de transit proportionnel T_prop = " + Parametres.format_double(transit_prop)+ " s");
	System.out.println ();
}
// affichage des résultats Min_Max sous forme de coefficients
public static void affiche_coef()
{
	System.out.println ();
	System.out.println (" Les capacités proportionnelles ");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (C_prop [origine][destination] !=0)
			{
				System.out.print("C[" + origine + "," + destination + "] = ");
				Parametres.conversion_debit(C_prop [origine][destination]);
				System.out.println(": ");
			}
		}
	}
	System.out.println ();
	System.out.print (" Capacité totale proportionnelle C_prop = "); Parametres.conversion_debit(capa_prop); System.out.println();
	System.out.println ();
	System.out.println (" Les Temps proportionnels exprimés en s ");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (Tps_prop [origine][destination] > 0)
			{
				System.out.println("T[" + origine + "," + destination + "] = " + Parametres.format_double(Tps_prop [origine][destination])+" s");}
			}
	}
	System.out.println ();
	System.out.println (" Temps de transit proportionnel T_prop = "+ Parametres.format_double(transit_prop)+" s");
	System.out.println ();
	
  }
}
