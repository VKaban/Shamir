import java.security.SecureRandom;
import java.util.*;

/**
 * Created by Vitalik on 19.12.2017.
 */
public class Scheme {
    private int modul;

    public static List<Pair<Integer,Integer>>  split(int secret, int n, int k, int modul)
    {
        List<Pair<Integer,Integer>> result = new ArrayList<>();
        Random random = new Random();
        int[] koefs = new int[k];
        koefs[0] = secret;

        for (int i = 1; i < k; i++)
            koefs[i] = random.nextInt()% modul;

        for (int i = 1; i <= n; i++) {
            int fx = 0;
            for (int j = 0; j < k; j++) {
                fx += koefs[j]*(int)Math.pow(i*1.0,j*1.0);
                fx%=modul;
            }
            result.add(new Pair(i, fx));
        }

        return result;
    }

    public static Integer join(List<Pair<Integer,Integer>>  parts,  int modul){
        int result = 0;
        for (int i = 0; i < parts.size(); i++) {
            double koef = 1;
            for (int j = 0; j < parts.size(); j++) {
                if (j!=i) {
                    double t = parts.get(j).getFirst()*1.0 / (parts.get(j).getFirst() - parts.get(i).getFirst());
                    koef *= t ;
                }
            }
            result += (int)koef * parts.get(i).getSecond();
        }

        return result%modul;
    }

     private  static  boolean isPrime(int num) {
        int sq_root = (int)Math.sqrt(num);
        for(int i = 2; i <= sq_root; i++)
            if (num % i == 0) {
                return false;
            }
            return true;
        }

    private static int fun(int num) {
        do {
            num++;    // you want to find a prime number greater than the argument
        } while (!isPrime(num));
        return num;
    }

    public static void main(String[] args) {
        int secret = 13;
        int n = 5;
        int k = 3;
        int modul = fun(secret);
        List<Pair<Integer,Integer>>  result = split(secret,n,k, modul);
        List<Pair<Integer,Integer>>  results = new ArrayList<>();
            results.add(result.get(2));
            results.add(result.get(1));
            results.add(result.get(4));

        Integer result1 = join(results, modul);
        System.out.println(result1);
    }
}
