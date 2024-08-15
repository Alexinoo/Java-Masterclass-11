package java_collections.part11_gotcha_with_equals_n_subclassing;
/*
 * Dog class
 *
 * Fields
 *  final name : String
 *
 * Constructor
 *  Dog(String name)
 *
 * Getter
 *  getName() : String
 *
 * @Override
 *  equals(Object obj) : boolean
 *      - check if the obj that we're comparing is the DOg class
 *      - if so
 *          - use the String.equals() to test whether the String's are the same
 */
public class Dog {

    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Dog){
            String objName = ((Dog) obj).getName();
            return this.name.equals(objName);
        }
        return false;
    }
}
