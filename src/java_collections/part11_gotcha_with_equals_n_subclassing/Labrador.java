package java_collections.part11_gotcha_with_equals_n_subclassing;
/*
 * Labrador class
 *
 * Constructor
 *  Labrador(String name)
 *      - call super constructor to initialize the name
 *
 * equals()
 *      - copy equals() from the Dog class and update the class name to Labrador
 *
 */
public class Labrador extends Dog{

    public Labrador(String name) {
        super(name);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj instanceof Labrador){
//            String objName = ((Labrador) obj).getName();
//            return this.getName().equals(objName);
//        }
//        return false;
//    }
}
