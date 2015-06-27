import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

class Trafic_Externe {
public static long matrice_trafic [][]; // la matrice de trafic externe Gamma
public static int nb_noeuds; // nombre de noeuds du réseau
public static int origine, destination;

//initialisation
public static void RAZ (int noeuds) {
nb_noeuds = noeuds;
matrice_trafic = new long[nb_noeuds][nb_noeuds];
	for (origine = 0; origine < nb_noeuds; origine++)
	{
		for (destination = 0; destination < nb_noeuds; destination++)
		{
			matrice_trafic[origine][destination] = 0;
		}
	}
}

// génération automatique de la matrice de trafic externe Gamma
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
			if (origine == destination)
			{ 
				matrice_trafic [origine][destination] = 0;
			}
			else 
			{
				matrice_trafic [origine][destination] = Tirage.UniformeMinMax(min,max);
			}
		}
	}
}

// génération manuelle de la matrice de trafic externe Gamma
public static void manuel(String rep, int noeuds)
{
int reponse;
Scanner lire = new Scanner(System.in);
RAZ (noeuds);
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		System.out.print(" le noeud[" + origine + "] est-il un noeud d'accès ? (0 = non) :"); reponse = lire.nextInt();
		if (reponse == 0)
		{
			continue;
		}
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (origine == destination)
			{
				matrice_trafic [origine][destination] = 0;
			}
			else
			{
				System.out.print (" Donner le trafic entre le noeud origine [" + origine + "] pour aller vers le noeud destination[" + destination + "] = ");
				matrice_trafic [origine][destination] = lire.nextLong();
				System.out.println ();
			}
		}
	}
}

// lecture du trafic externe entre une origine et une destination
public static long lire (int origine, int destination)
{
	return matrice_trafic [origine][destination];
}

// ecriture du trafic externe entre une origine et une destination
public static void ecrire (){
int origine, destination, trafic;
Scanner lire = new Scanner(System.in);
	do {
		System.out.print (" donner le noeud origine : "); origine = lire.nextInt();
		System.out.print (" donner le noeud destination : "); destination = lire.nextInt();
		System.out.print (" donner la valeur du trafic : "); trafic = lire.nextInt();
		matrice_trafic [origine][destination] = trafic;
		System.out.print(" autre modification ? (0 = non)"); origine = lire.nextInt();
		}while (origine != 0);
}

// affichage des trafics externe Gamma sous forme de coefficients
public static void affiche_coef()
{
	System.out.println (" Affichage des trafics externes ");
	System.out.println(" les termes qui n'apparaissent pas sont nuls ");System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (matrice_trafic [origine][destination] !=0){
				System.out.println("G[" + origine + "," + destination + "] = " + matrice_trafic [origine][destination]);}
		}
	}
	System.out.println ();
}

// affichage de la matrice de trafic externe Gamma sous forme matricielle
public static void affiche_matrice()
{
	System.out.println (" Affichage de la matrice de trafic ");
	System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			System.out.printf("%2d   ",matrice_trafic [origine][destination]);  
		}
		System.out.println ();
	}
	System.out.println ();
}

// sauvegarde la matrice de trafic externe Gamma
public static void sauve(String rep){
try {
	System.out.println ("dans la fonction ===> "+ rep );
	FileOutputStream fichier = new FileOutputStream (rep);
	ObjectOutputStream ecrire = new ObjectOutputStream(fichier);
	ecrire.writeObject(matrice_trafic);
	ecrire.flush(); 
	ecrire.close();
	}
	catch (IOException e) {e.printStackTrace();}
}

// chargement de la matrice de trafic externe Gamma
public static void charge(String rep)
{
	try {
			FileInputStream fichier = new FileInputStream (rep); // on ouvre le fichier de la matrice de trafic
			ObjectInputStream lecture = new ObjectInputStream (fichier); // en lecture
			matrice_trafic = (long[][]) lecture.readObject(); // on charge le fichier dans matrice_trafic
			nb_noeuds = matrice_trafic[0].length; // nombre de noeuds du réseau test
			System.out.println(" on a chargé "+(nb_noeuds - 1)+" noeuds pour ce réseau");
		}
	catch ( IOException e) {e.printStackTrace();}
	catch (ClassNotFoundException e){e.printStackTrace();}
	
	}


}
