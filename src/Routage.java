import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

class Routage {
	
	public static int matrice_routage [][]; // la matrice de routage
	public static int nb_noeuds; // nombre de noeuds du réseau
	public static int origine, destination;
	
	// initialisation
	public static void RAZ (int noeuds){
		nb_noeuds = noeuds;
		matrice_routage = new int[nb_noeuds][nb_noeuds];
		for (origine = 0; origine < nb_noeuds; origine++){
			for (destination = 0; destination < nb_noeuds; destination++){
				matrice_routage[origine][destination] = 0;
			}
		}
	}
	
	// génération de la matrice de routage
	public static void routage(String rep, int noeuds)
	{
		Scanner lire = new Scanner(System.in);
		RAZ (noeuds);
		for (origine = 1; origine < nb_noeuds; origine++)
		{
			for (destination = 1; destination < nb_noeuds; destination++)
				if (destination == origine)
				{
					matrice_routage [destination][origine] = origine;
				}
				else
				{
					System.out.print (" Pour la destination " + origine + " donner le noeud suivant lorsque l'on se trouve sur le noeud[" + destination + "] = ");
					matrice_routage [origine][destination] = lire.nextInt();
					System.out.println ();
				}
		}
	}



	public static void automatique(String rep, int noeuds)
	{
		Scanner lire = new Scanner(System.in);
		int min, max;
		RAZ (noeuds);
		System.out.print(" Donner la valeur min du trafic : "); 
		min =lire.nextInt();
		System.out.print(" Donner la valeur max du trafic : ");
		max =lire.nextInt();
		for (origine = 1; origine < nb_noeuds; origine++)
		{
			for (destination = 1; destination < nb_noeuds; destination++)
			{
				if (destination == origine)
				{
					matrice_routage [destination][origine] = origine;
				}
				else 
				{
					matrice_routage [origine][destination] = (int) Tirage.UniformeMinMax(min,max);
				}
			}
		}
	}

	
	// lecture du noeud suivant entre un noeud actuel et une destination
	public static int lire (int noeud_actuel, int destination)
	{
		return matrice_routage [destination][noeud_actuel];
	}

	// ecriture du noeud suivant entre un noeud actuel et une destination
	public static void ecrire (){
		int noeud;
		Scanner lire = new Scanner(System.in);
		do {
			System.out.print (" donner le noeud origine : "); origine = lire.nextInt();
			System.out.print (" donner le noeud destination : "); destination = lire.nextInt();
			System.out.print (" donner le noeud suivant : "); noeud = lire.nextInt();
			matrice_routage [origine][destination] = noeud;
			System.out.print(" autre modification ? (0 = non)"); origine = lire.nextInt();
		} while (origine != 0);
	}

	// affichage des routages sous forme de coefficients
	public static void affiche_coef()
	{
		System.out.println (" Affichage des routages ");
		System.out.println(" les termes qui n'apparaissent pas sont nuls ");
		System.out.println();
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			System.out.println(" table de routage du noeud["+destination+"] ");
			System.out.println();
			for (origine = 1; origine < nb_noeuds; origine++)
			{
				if ((matrice_routage [origine][destination] !=0) & (origine != destination)){
					System.out.println("R[" + origine + "," + destination + "] = " + matrice_routage [origine][destination]);
					}
			}
			System.out.println();
		}
		System.out.println ();
	}
	
	// affichage de la matrice de routage sous forme matricielle
	public static void affiche_matrice()
	{
		System.out.println (" Affichage de la matrice de routage ");
		System.out.println();
		for (origine = 1; origine < nb_noeuds; origine++)
		{
			for (destination = 1; destination < nb_noeuds; destination++)
			{
				System.out.printf("%2d   ",matrice_routage [origine][destination]);
				//System.out.printf("%2d   ",matrice_trafic [origine][destination]);  
			}
			System.out.println ();
		}
		System.out.println ();
	}
	
	// sauvegarde la matrice de routage
	public static void sauve(String rep){
	try {
		FileOutputStream fichier = new FileOutputStream (rep);
		ObjectOutputStream ecrire = new ObjectOutputStream(fichier);
		ecrire.writeObject(matrice_routage);
		ecrire.flush(); 
		ecrire.close();
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	// chargement de la matrice de routage
	public static void charge(String rep)
	{
		try 
		{
			FileInputStream fichier = new FileInputStream (rep); // on ouvre le fichier de la matrice de routage
			ObjectInputStream lecture = new ObjectInputStream (fichier); // en lecture
			matrice_routage = (int[][]) lecture.readObject(); // on charge le fichier dans matrice_routage
			nb_noeuds = matrice_routage[0].length; // nombre de noeuds du réseau test
			System.out.println(" on a chargé "+(nb_noeuds - 1)+" noeuds pour ce réseau");
		}
		catch ( IOException e) {e.printStackTrace();}
		catch (ClassNotFoundException e){e.printStackTrace();}
		}
	}
