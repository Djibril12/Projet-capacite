import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Clavier {
	
// lecture d'une chaine depuis le clavier
public static String lireString (){
String ligne = null;
boolean correct = false;

	while (! correct) 
	{
		ligne = null;
		try {InputStreamReader clavier = new InputStreamReader (System.in);
		BufferedReader entree = new BufferedReader (clavier);
		ligne = entree.readLine();
		correct = true;
		}
		catch (IOException err){System.out.println("erreur de frappe, recommencer :");}
	}
return ligne;
}

// lecture d'un r�el double depuis le clavier
public static double lireDouble (){
double d = Double.MIN_VALUE;
boolean correct = false;

	while (! correct) {
		d = Double.MIN_VALUE;
		try {String ligne = lireString();
		d = Double.parseDouble(ligne);
		correct = true;
		}
		catch (NumberFormatException err){System.out.println("double attendu, recommencer :");}
	}
return d;
}

// lecture d'un r�el flottant depuis le clavier
public static float lireFloat (){
float f = Float.MIN_VALUE;
boolean correct = false;
	
	while (! correct) {
		f = Float.MIN_VALUE;
		try {String ligne = lireString();
		f = Float.parseFloat(ligne);
		correct = true;
		}
		catch (NumberFormatException err){System.out.println("float attendu, recommencer :");}
	}
	return f;
  }

}