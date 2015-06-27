class min_Max {
	public static double [][] C_minMax, Tps_minMax;
	public static double transit_minMax, capa_minMax;
	public static int nb_noeuds;
	public static int origine, destination;
// constructeur
	public static void RAZ(){
	nb_noeuds = Trafic_Externe.nb_noeuds;
	transit_minMax =0.0; capa_minMax =0.0;
	C_minMax = new double [nb_noeuds][nb_noeuds];
	Tps_minMax = new double [nb_noeuds][nb_noeuds];
	for (origine=0; origine<nb_noeuds;origine++)
	{
		for (destination=0; destination<nb_noeuds;destination++)
		{
			C_minMax[origine][destination] = 0.0;
			Tps_minMax[origine][destination] =0.0;
		}
	}
}
// Algorithme du Min-Max
public static void calcul(){
double temps =0.0;
RAZ();
	for (origine = 0; origine<nb_noeuds;origine++)
	{
		for (destination = 0; destination<nb_noeuds;destination++)
		{     
			if (origine != destination) 
			{
				if (Trafic_Interne.matrice_lambda[origine][destination] != 0)
				{
					C_minMax[origine][destination] = ((Trafic_Interne.matrice_lambda[origine][destination] * Parametres.un_sur_Mu)+ ((Parametres.C * (1 - (Parametres.longueur_chemin * Parametres.Rho)))/Parametres.nb_liaisons ));
					capa_minMax = capa_minMax + C_minMax[origine][destination];
					Tps_minMax[origine][destination] = ((Parametres.nb_liaisons * Parametres.un_sur_Mu) / (Parametres.C*(1-(Parametres.longueur_chemin * Parametres.Rho))));
					temps = Tps_minMax[origine][destination];
				}
			}
		}
	}

	transit_minMax = Parametres.longueur_chemin * temps;
}
// affichage des résultats Min_max
public static void affiche_matrice()
{
	System.out.println (" Les capacités min_Max ");
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			Parametres.conversion_debit(C_minMax [origine][destination]);System.out.print(": ");
		}
		System.out.println ();
	}
	System.out.println ();
	System.out.print (" Capacité totale minMax C_minMax = ");
	Parametres.conversion_debit(capa_minMax);
	System.out.println ();
	System.out.println ();
	System.out.println (" Les temps min_Max exprimés en s");
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (Tps_minMax[origine][destination] > 0)
			{
				System.out.print(Parametres.format_double(Tps_minMax [origine][destination])+ ": ");
			}
		}
		System.out.println ();
	}
	System.out.println ();
	System.out.println (" Temps de transit min_Max T_minMax = "+ Parametres.format_double(transit_minMax)+" s");
	System.out.println ();
}
// affichage des résultats Min_Max sous forme de coefficients
public static void affiche_coef()
{
	System.out.println ();
	System.out.println (" Les capacités Min_Max ");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (C_minMax [origine][destination] !=0)
			{
				System.out.print("C[" + origine + "," + destination + "] = "); 
				Parametres.conversion_debit(C_minMax [origine][destination]);System.out.println(": ");
			}
		}
	}
	System.out.println ();
	System.out.print (" Capacité totale minMax C_minMax = ");
	Parametres.conversion_debit(capa_minMax); System.out.println();
	System.out.println ();
	System.out.println (" Les Temps Min_Max exprimés en s");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (Tps_minMax [origine][destination] > 0)
			{
				System.out.println("T[" + origine + "," + destination + "] = " + Parametres.format_double(Tps_minMax [origine][destination])+" s");
			}
		}
	}
	System.out.println ();
	System.out.println (" Termps de transit Min_max T_minMax = " + Parametres.format_double(transit_minMax)+" s");
	System.out.println ();

  }


}
