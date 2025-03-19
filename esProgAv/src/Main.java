import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Word: ");
        String w = s.nextLine();

        System.out.print("Number of wanted anagrams: ");
        int n = s.nextInt();

        // Calcola il numero massimo di permutazioni
        int maxAnagrams = factorial(w.length());
        if (n > maxAnagrams) {
            System.out.println("The number of requested anagrams exceeds the maximum possible anagrams. Showing all available anagrams:\n");
            n = maxAnagrams;
        }

        // Array per memorizzare gli anagrammi
        String[] anagrams = new String[n];
        int[] count = {0}; // Contatore per tracciare il numero di anagrammi aggiunti

        // Genera permutazioni
        permute(w.toCharArray(), 0, anagrams, n, count);

        // Stampa gli anagrammi
        printAnagrams(anagrams, count[0]);
    }

    // Funzione per calcolare il fattoriale
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot compute with a negative number");
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Funzione per generare permutazioni senza duplicati
    public static void permute(char[] chars, int index, String[] anagrams, int maxCount, int[] count) {
        // Se il limite massimo di anagrammi è già stato raggiunto, interrompi
        if (count[0] >= maxCount) {
            return;
        }

        // Caso base: se siamo arrivati alla fine, aggiungi la permutazione all'array
        if (index == chars.length - 1) {
            String potentialAnagram = new String(chars);

            // Controlla manualmente se l'anagramma già esiste
            if (!isDuplicate(anagrams, potentialAnagram, count[0])) {
                anagrams[count[0]] = potentialAnagram; // Aggiungi all'array
                count[0]++; // Incrementa il conteggio
            }
            return;
        }

        // Altrimenti, genera tutte le permutazioni possibili con backtracking
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i); // Effettua uno scambio
            permute(chars, index + 1, anagrams, maxCount, count); // Passa al livello successivo
            swap(chars, index, i); // Ripristina l'ordine originale per il backtracking
        }
    }

    // Funzione per controllare duplicati nell'array
    public static boolean isDuplicate(String[] anagrams, String newAnagram, int count) {
        for (int i = 0; i < count; i++) {
            if (anagrams[i] != null && anagrams[i].equals(newAnagram)) {
                return true; // Trovato duplicato
            }
        }
        return false; // Nessun duplicato trovato
    }

    // Funzione per scambiare due caratteri
    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Funzione per stampare gli anagrammi
    public static void printAnagrams(String[] anagrams, int n) {
        for (int i = 0; i < n; i++) {
            if (anagrams[i] != null) {
                System.out.println(anagrams[i].substring(0, 1).toUpperCase() + anagrams[i].substring(1));
            }
        }
    }
}