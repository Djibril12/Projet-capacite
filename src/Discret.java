
class Discret {

	public static double [][] C_discr, Tps_discr;
	public static double transit_discr, capa_discr;
	public static int nb_noeuds;
	public static int origine, destination;
	// constructeur
	public static void RAZ (){
	nb_noeuds = Trafic_Externe.nb_noeuds;
	transit_discr =0.0; capa_discr =0.0;
	C_discr = new double [nb_noeuds][nb_noeuds];
	Tps_discr = new double [nb_noeuds][nb_noeuds];


	for (origine=0; origine<nb_noeuds;origine++)
	{
		for (destination=0; destination<nb_noeuds;destination++)
		{
			C_discr[origine][destination] = 0.0; Tps_discr[origine][destination] =0.0;
		}
	 }
	}

	
	
	/*
	 * test methode discret
	 * 
	 */

	public static void discret (double cap)
	{
		RAZ();
		for (origine=1; origine<nb_noeuds;origine++)
		{
			for (destination=1; destination<nb_noeuds;destination++)
			{
				if (origine != destination)
				{
					
					if (Trafic_Interne.matrice_lambda[origine][destination] !=0) 
					{
						System.out.println(Trafic_Interne.matrice_lambda[origine][destination]);
						System.out.print("valeur de Mu " +(1 / Parametres.un_sur_Mu));
						System.out.print("valeur de C_discr" +C_discr[origine][destination] );
						C_discr[origine][destination]= cap;
						capa_discr = capa_discr + C_discr[origine][destination];
						Tps_discr[origine][destination]= (1 / ((1 / Parametres.un_sur_Mu)*C_discr[origine][destination]) - (Trafic_Interne.matrice_lambda[origine][destination])) ;
						transit_discr = (transit_discr + ((Trafic_Interne.matrice_lambda[origine][destination]*Tps_discr[origine][destination])/Parametres.gamma));
					}
				
				}
			}
		}
		

	}
	
		
	public static void affiche_coef()
	{
		System.out.println ();
		System.out.println (" Les capacités discret ");
		System.out.println();
		for (origine = 1; origine < nb_noeuds; origine++)
		{
			for (destination = 1; destination < nb_noeuds; destination++)
			{
				if ((C_discr [origine][destination] !=0)& (destination != origine))
				{
					//System.out.printf("%2d   ",matrice_trafic [origine][destination]);  
					System.out.print("C[" + origine + "," + destination + "] = "); 
					Parametres.conversion_debit(C_discr [origine][destination]);
					System.out.println(": ");
				}
			}
		}

		System.out.println ();
		System.out.print (" Capacité totale optimum C_opt = ");
		Parametres.conversion_debit(capa_discr); System.out.println();
		System.out.println ();
		System.out.println (" Les Temps disrets ");
		System.out.println();
		for (origine = 1; origine < nb_noeuds; origine++)
		{
			for (destination = 1; destination < nb_noeuds; destination++)
			{
				if (Tps_discr [origine][destination] > 0)
				{
					System.out.println("T[" + origine + "," + destination + "] = " + Parametres.format_double(Tps_discr [origine][destination])+" s");
				}
			}
		}
		System.out.println ();
		System.out.println (" Temps de transit optimum T_discr = "+ Parametres.format_double(transit_discr)+" s");
		System.out.println ();
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
