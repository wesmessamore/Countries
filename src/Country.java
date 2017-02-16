/**
 * Created by Wesley on 2/10/17.
 */
public class Country {
    String name;
    String abbreviation;

    public Country(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return name;
    }
}
