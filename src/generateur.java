import java.io.File;
import java.util.Scanner;

public class generateur {
	
	public static void main(String[] args) {



	Scanner lire = new Scanner(System.in);
	int reponse, repons;
	//boolean trafic_charge =false, routage_charge = false, lambda_charge = false;
	int nb_noeuds=0; // nombre de noeuds du réseau
	//String rep = "c:"; // disque de stockage des jeux de tests
	String repS = "C:\\Users\\Barry\\workspace\\ModulariteProjetCap";
	String rep1 = "";
	//String dossier_des_tests = "tests_capacites"; // dossier ou sont rangés les dossiers de tests
	String nom_fichier = ""; // nom du fichier de test
	String s = File.separator; // séparateur de fichier

		
	/*
	 * voici le menu principal de notre programme 
	 *  avec l'option 1 on test juste les differents jeux de tests fourni dans notre nous testons plus 
	 *  particulierement le jeu de test nommé test1 que nous mis dans le repertoire de notre projet
	 *  avec l'option 2 on laisse le choix à l'utilisateur de creer par lui même son jeu de test
	 * 
	 */
	
		System.out.println("\n###################################################################################################");
		System.out.println("|                                      MENU PRINCIPAL                                             |");
		System.out.println("###################################################################################################\n|" +
				"                                                                                                 |");
		System.out.println("|               options :                                                                         |\n|" +
				"                                                                                                 |");
		System.out.println("| 			1 = choix du repertoire d'un jeu de test particulier à exécuter           |\n|" +
				"                                                                                                 |");
		System.out.println( "|			2 = creation de son propre jeu de test                                    |\n|" +
				"                                                                                                 |");
		System.out.println( "|			0 = quitter                                                               |\n|" +
				"                                                                                                 |");
		System.out.println("###################################################################################################");

		System.out.print( " votre choix = ");
		reponse = lire.nextInt();
		
		System.out.println("le repertoire est " +repS);
		System.out.println();System.out.println();
		
		switch(reponse)
		{
			case 0 :
				System.out.print("A bientôt !!!");
				break;
	
			
			
			case 1 :
			{
				System.out.print(" donner le nom du répertoire du jeux de test : "); 
				nom_fichier = lire.next();
				
				rep1 = repS+s+nom_fichier+s+"matrice.trafic";
				Trafic_Externe.charge(rep1);
				Trafic_Externe.affiche_matrice();
				
				rep1 = repS+s+nom_fichier+s+"matrice.routage";
				Routage.charge(rep1);
				Routage.affiche_matrice();
				
				rep1 = repS+s+nom_fichier+s+"matrice_lambda";
				
				Trafic_Interne.lambda(rep1);
				Trafic_Interne.charge(rep1);
				Trafic_Interne.sauve(rep1);
				Trafic_Interne.affiche_matrice();
		
				Parametres.calcul(); // calcul des differents parametres
		    
				// Méthode de la racine carrée
				System.out.println(" calcul avec la méthode racine carrée");
				Optimum.calcul();
				System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
				if (lire.nextInt()== 0)
					Optimum.affiche_matrice();
				else 
					Optimum.affiche_coef();
				
			    // Méthode du min_Max
			    System.out.println(" calcul avec la méthode min_max");
				min_Max.calcul();
			    System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
				if (lire.nextInt()== 0) 
					min_Max.affiche_matrice();
				 else
					min_Max.affiche_coef();
						
			    // Méthode proportionnelle
				System.out.println(" calcul avec la méthode proportionnelle");
				Proportionnel.calcul();
				System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
				if (lire.nextInt()== 0) 
					Proportionnel.affiche_matrice(); 
				else
					Proportionnel.affiche_coef();
						
				 
				  // Méthode discrête
				
				System.out.println("Le nombre de noeud est "+ Parametres.nb_liaisons);
				System.out.println("La capacite est "+ Parametres.C);
				 
				double cout= 512;
				double val = 0;
				while(val < Parametres.C * 1000)
				{
					
					val = Parametres.nb_liaisons * cout;
					System.out.println(" la val est :" + val);
					cout = cout + 512;
					System.out.println(" le cout est :" + cout);

				}
				
				
				double cout_opt =  cout - 512;
				System.out.println("cout discret Optimum = " + cout_opt);
				
					
				
				Discret.discret(512000);
				Discret.affiche_coef();
				
			
				
				break;
					
			}
			case 2:
			{
				

				System.out.println("\n###################################################################################################");
				System.out.println("|                                      MENU CREATION                                              |");
				System.out.println("###################################################################################################\n|" +
						"                                                                                                 |");
				System.out.println("|               options :                                                                         |\n|" +
						"                                                                                                 |");
				System.out.println("| 			1 = creation manuelle des differentes matrices                            |\n|" +
						"                                                                                                 |");
				System.out.println( "|			2 = creation automatique des differentes matrices                         |\n|" +
						"                                                                                                 |");
				System.out.println( "|			0 = quitter                                                               |\n|" +
						"                                                                                                 |");
				System.out.println("###################################################################################################");

				System.out.print( " votre choix = ");
				repons = lire.nextInt();
				
				
				
				
				switch(repons)
				{
				case 0:  break;
				case 1:
				{
					System.out.print(" donner le nom du répertoire du jeux de test : "); 
					nom_fichier = lire.next();
					System.out.print (" Donner le nombre de noeuds du réseaux = ");
					nb_noeuds = lire.nextInt()+1;
					rep1 = repS+s+nom_fichier;//+s+"matrice.trafic";
					System.out.println ("le repertoire est "+ rep1);
					
					System.out.println("SAISIR DE LA MATRICE DE TRAFIC");	
					File repertoire = new File(rep1);
					
					if (!repertoire.isDirectory())
					{
						repertoire.mkdir();
					} // on le crée s'il n'existe pas
					//File [] fichiers = repertoire.listFiles();
				//	int nbfichiers = (int) fichiers.length;
					System.out.println("repertoire ======>" +rep1 + s + "matrice.trafic");
					Trafic_Externe.manuel(rep1 + s + "matrice.trafic", nb_noeuds);
					//trafic_charge = true;
					Trafic_Externe.sauve(rep1 + s + "matrice.trafic");
					Trafic_Externe.charge(rep1 + s + "matrice.trafic");
					Trafic_Externe.affiche_matrice();
					
					
					System.out.println("##########     SAISIR DE LA MATRICE DE ROUTAGE      ############");
					
					System.out.print (" Donner le nombre de noeuds du réseaux = ");
					nb_noeuds = lire.nextInt()+1;
					Routage.routage(rep1 + s + "matrice.routage", nb_noeuds);
					Routage.sauve(rep1 + s + "matrice.routage");
					Routage.charge(rep1 + s + "matrice.routage");
					Routage.affiche_matrice();
					
					System.out.println("##########     CALCUL DE LA MATRICE INTERNE      ############");
					
					rep1 = repS+s+nom_fichier+s+"matrice_lambda";
					
					Trafic_Interne.lambda(rep1);
					Trafic_Interne.sauve(rep1);
					Trafic_Interne.charge(rep1);
					Trafic_Interne.affiche_matrice();
					
					
				 Parametres.calcul(); // calcul des differents parametres
		    
				// Méthode de la racine carrée
				System.out.println(" calcul avec la méthode racine carrée");
				Optimum.calcul();
				System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
				if (lire.nextInt()== 0)
					Optimum.affiche_matrice();
				else 
					Optimum.affiche_coef();
				
			    // Méthode du min_Max
			    System.out.println(" calcul avec la méthode min_max");
				min_Max.calcul();
			    System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
				if (lire.nextInt()== 0) 
					min_Max.affiche_matrice();
				 else
					min_Max.affiche_coef();
						
			    // Méthode proportionnelle
				System.out.println(" calcul avec la méthode proportionnelle");
				Proportionnel.calcul();
				System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
				if (lire.nextInt()== 0) 
					Proportionnel.affiche_matrice(); 
				else
					Proportionnel.affiche_coef();
						
				 
				  // Méthode discrête
				
				double cout= 512;
				double val = 0;
				while(val < Parametres.C * 1000)
				{
					
					val = Parametres.nb_liaisons * cout;
					System.out.println(" la val est :" + val);
					cout = cout + 512;
					System.out.println(" le cout est :" + cout);

				}
				
				double cout_opt =  cout - 512;
				System.out.println("cout discret Optimum = " + cout_opt);
				
					
					break;
				}
				case 2:
				{
					
					
					System.out.print(" donner le nom du répertoire du jeux de test : "); 
					nom_fichier = lire.next();
					System.out.print (" Donner le nombre de noeuds du réseaux = ");
					nb_noeuds = lire.nextInt()+1;
					rep1 = repS+s+nom_fichier;//+s+"matrice.trafic";
					System.out.println ("le repertoire est "+ rep1);
					
					System.out.println("SAISIR DE LA MATRICE DE TRAFIC");	
					File repertoire = new File(rep1);
					
					if (!repertoire.isDirectory())
					{
						repertoire.mkdir();
					} // on le crée s'il n'existe pas
				
					// traitement de la matrice de trafic esterne
					System.out.println("repertoire ======>" +rep1 + s + "matrice.trafic");
					Trafic_Externe.automatique(rep1 + s + "matrice.trafic", nb_noeuds);
					Trafic_Externe.sauve(rep1 + s + "matrice.trafic");
					Trafic_Externe.charge(rep1 + s + "matrice.trafic");
					Trafic_Externe.affiche_matrice();
					
					
					// traitement la matrice routage
					System.out.print (" Donner le nombre de noeuds du réseaux = ");
					nb_noeuds = lire.nextInt()+1;
					Routage.automatique(rep1 + s + "matrice.routage", nb_noeuds);
					Routage.sauve(rep1 + s + "matrice.routage");
					Routage.charge(rep1 + s + "matrice.routage");
					Routage.affiche_matrice();
					
					
					// génération de la matrice de trafic interne
					System.out.println("##########     CALCUL DE LA MATRICE INTERNE      ############");
					rep1 = repS+s+nom_fichier+s+"matrice_lambda";
					Trafic_Interne.lambda(rep1);
					Trafic_Interne.charge(rep1);
					Trafic_Interne.sauve(rep1);
					Trafic_Interne.affiche_matrice();
					
					
					Parametres.calcul(); // calcul des differents parametres
				    
					
					// Méthode de la racine carrée
					System.out.println(" calcul avec la méthode racine carrée");
					Optimum.calcul();
					System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
					if (lire.nextInt()== 0)
						Optimum.affiche_matrice();
					else 
						Optimum.affiche_coef();
					
				    // Méthode du min_Max
				    System.out.println(" calcul avec la méthode min_max");
					min_Max.calcul();
				    System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
					if (lire.nextInt()== 0) 
						min_Max.affiche_matrice();
					 else
						min_Max.affiche_coef();
							
				    // Méthode proportionnelle
					System.out.println(" calcul avec la méthode proportionnelle");
					Proportionnel.calcul();
					System.out.println(" Voulez-vous les résultats sous forme matricielle (0), sous forme de coefficients (1) : ");
					if (lire.nextInt()== 0) 
						Proportionnel.affiche_matrice(); 
					else
						Proportionnel.affiche_coef();
							
					 
					  // Méthode discrête
					
					double cout= 512;
					double val = 0;
					while(val < Parametres.C * 1000)
					{
						
						val = Parametres.nb_liaisons * cout;
						System.out.println(" la val est :" + val);
						cout = cout + 512;
						System.out.println(" le cout est :" + cout);

					}
					
					double cout_opt =  cout - 512;
					System.out.println("cout discret Optimum = " + cout_opt);
					
					
						break;
					}
				
				
				}
				
				break;
			}
			
		}//fin switch menu principal
     
	}// fin main

}//fin class
	