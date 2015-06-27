import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Trafic_Interne {

public static long matrice_lambda [][]; // la matrice de trafic interne Lambda
public static int nb_noeuds; // nombre de noeuds du réseau
public static int origine, destination;


// initialisation

public static void RAZ (){
nb_noeuds = Trafic_Externe.nb_noeuds;
matrice_lambda = new long[nb_noeuds][nb_noeuds];
	for (origine = 0; origine < nb_noeuds; origine++)
	{
		for (destination = 0; destination < nb_noeuds; destination++)
		{
			matrice_lambda[origine][destination] = 0;
		}
	}	
}





//génération de la matrice de trafic interne Lambda
public static void lambda(String rep)
{
	int debut=0, suivant=0;
	long trafic;
	RAZ ();
	//parcours de la table des lambda
	System.out.println ("le repertoire dans la fonction est "+ rep);
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			trafic = Trafic_Externe.lire(origine,destination);
			debut = origine;
			//parcours de toutes les liaisons entre une origine et une destination
			while (debut != destination) 
			{
				System.out.println("debut = "+ debut +" destination = "+ destination);
				
				suivant = Routage.lire(debut, destination); // on prend le noeud suivant
				matrice_lambda [debut][suivant] = matrice_lambda [debut][suivant] + trafic; // on ajoute le trafic sur cette route
				debut = suivant; // on passe au noeud suivant
				System.out.println("debut = "+ debut +" suivant = "+ suivant);
				
				System.out.println("test while");
			}
		}
	}
}
	

// affichage de la matrice de trafic interne Lambda sous forme matricielle
public static void affiche_matrice()
{
	System.out.println (" Affichage de la matrice de lambda ");
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			//System.out.print(matrice_lambda [origine][destination]+ ": ");		
			System.out.printf("%2d   ",matrice_lambda [origine][destination]); 
		}
	System.out.println ();
	}
System.out.println ();
}


// affichage des trafics interne Lambda sous forme de coefficients
public static void affiche_coef()
{
	System.out.println (" Affichage des trafics internes ");
	System.out.println(" les termes qui n'apparaissent pas sont nuls ");System.out.println();
	for (origine = 1; origine < nb_noeuds; origine++)
	{
		for (destination = 1; destination < nb_noeuds; destination++)
		{
			if (matrice_lambda [origine][destination] !=0){
				System.out.println("L[" + origine + "," + destination + "] = " + matrice_lambda [origine][destination]);}
		}
	}
	System.out.println ();
}


// sauvegarde la matrice de trafic interne Lambda
public static void sauve(String rep){
	try {
		FileOutputStream fichier = new FileOutputStream (rep);
		ObjectOutputStream ecrire = new ObjectOutputStream(fichier);
		ecrire.writeObject(matrice_lambda);
		ecrire.flush(); ecrire.close();
	}
	catch (IOException e) {e.printStackTrace();}
}



// chargement de la matrice de trafic interne Lambda
public static void charge(String rep)
{
	try {
		System.out.println("le repertoire est ++++++++> "+ rep);
		FileInputStream fichier = new FileInputStream (rep); // on ouvre le fichier de la matrice lambda
		ObjectInputStream lecture = new ObjectInputStream (fichier); // en lecture
		matrice_lambda = (long[][]) lecture.readObject(); // on charge le fichier dans matrice_lambda
		nb_noeuds = matrice_lambda[0].length; // nombre de noeuds du réseau test
		}
	catch ( IOException e) {e.printStackTrace();}
	catch (ClassNotFoundException e){e.printStackTrace();}
	}
}
