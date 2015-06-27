import java.util.Random;

class Tirage {
// tirage uniforme entre 0 et 1
	public static float Uniforme() 
	{ 
		return( new Random().nextFloat());
	}
	
// tirage uniforme entre min et max
public static long UniformeMinMax (int min, int max){
	
return (long) java.lang.Math.round(((double)(max-min)*Uniforme()+ (double)min));
	
	}

}