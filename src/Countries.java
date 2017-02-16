import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    static ArrayList<Country> allCountriesList = new ArrayList<>();
    static HashMap<String, ArrayList<Country>> countryHashMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

       // Reads countries.txt and creates a list of Country objects

       readCountriesFromFile();

        // Parses countries.txt file into a HashMap<String, ArrayList<Country>>
        // where the key is a single letter and the value is a list of countries whose
        // names start with that letter.

       createHashMap();


        // Saves an "X_countries.txt" file, where X is a letter the user types, which lists the country's
        // name for just those countries that start with that letter.

       saveCountryList();


    }

    public static ArrayList readCountriesFromFile() throws FileNotFoundException {

        File file = new File("countries.txt");
        Scanner scanner = new Scanner(file);
        while ( scanner.hasNext() ) {
            String line = scanner.nextLine();
            String[] columns = line.split("\\|");

            Country country = new Country(columns[1], columns[0]);
            allCountriesList.add(country);


                // && scanner.nextLine().substring(3).startsWith(getLetter())){
           // allCountriesList.add( scanner.nextLine().substring(3) );
        }
        scanner.close();

        return allCountriesList;

    }

//    public static String getLetter() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Please enter a letter.");
//        String letterInput = scanner.nextLine();
//
//        System.out.println(countryHashMap.get(letterInput.toLowerCase()));
//
//        return letterInput;
//    }


    private static void createHashMap() {
        for (Country country : allCountriesList) {                             // for every country in this list

            String firstLetter = getFirstletter(country.name);
            countryHashMap.put(firstLetter, createCountryList(country, firstLetter));

        }

    }

    private static ArrayList<Country> createCountryList(Country country, String firstLetter) {
        ArrayList<Country> countryByLetter;
        countryByLetter = countryHashMap.get(firstLetter);

        if (countryByLetter == null) {
            countryByLetter = new ArrayList<>();
        }

        countryByLetter.add(country);

        return countryByLetter;
    }


    private static String getFirstletter(String name) {
        return name.substring(0,1);
    }


    private static void saveCountryList() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a letter to save a text file with a list\n"
                + "of just those countries that start with that letter.");
        String letterInput = scanner.nextLine();

        File file = new File(letterInput.toLowerCase() + "_countries.txt");
        FileWriter filewriter = new FileWriter(file);


        ArrayList<Country> list = (countryHashMap.get(letterInput.toLowerCase()));
        for ( Country country : list ) {
            System.out.println("[" + country + "]");
        }
        System.out.println("The above list has been saved with the filename "
                + letterInput.toLowerCase() + "_countries.txt");

        filewriter.write( countryHashMap.get( letterInput.toLowerCase() ).toString() );
        filewriter.close();

    }

}