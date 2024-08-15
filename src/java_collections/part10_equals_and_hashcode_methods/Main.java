package java_collections.part10_equals_and_hashcode_methods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* HashSet - equals() and hashCode()
*
* - The Java documentation does warn you that if you're using your own objects as a key in a map or as an element in a Set, that you should override
*   the equals() and hashCode() methods
*
* - Let's look at what happens if we don't actually override them and the problems that we can get ourself into
*
* ///////////////////////
*  - Let's create a new Pluto object - with a different orbital period from the 1st one
*
*        HeavenlyBody plutoDuplicate = new HeavenlyBody("Pluto",842);
*   
*  - and add it to the Set<HeavenlyBody> planets set
*       planets.add(plutoDuplicate);
*
*  - Then print the planets again plus their orbital period
*
*        for (HeavenlyBody planet: planets) {
            System.out.println(planet.getName() +" : "+ planet.getOrbitalPeriod());
        }
* /////////////////////
*   - The Pluto is now shown twice and because Set<E> aren't necessary in order, they might appear in any order
*
*   - However, we made a big point about the fact that a Set<E> can't contain duplicates and how come now that we've got a duplicate
*
*
* /////// Object.equals () /////////////
*   - The reason is the 2 Java objects do not compare equal and the Set<E> is happy to accept both of them for that reason
*   - And if we've used them as Keys in a Map<K,V>, same thing would have happened, and we would have 2 entries in a map
*
*   - This is why the equals() has to be overwritten in our HeavenlyBody class
*   - Now the reason why they don't compare equal, is because the base object class from which all the other classes are derived just defines a very
*     simple equals() that performs what is known as "referential equality"
*
*   - And if both references point to the same obj, then they're equal , otherwise they're not
*
*       HeavenlyBody temp = new HeavenlyBody("Pluto",248);
        System.out.println(temp.equals(temp)); // true
 *
 *  - The above statement returns true which tells us that both are not referencing the same obj
 *
 *  - And that's the default that comes with the base Object class that all the Java classes inherit from
 *
 *  - It's just to say that it just uses (==) to perform the comparison, which is exactly the same way as comparing 2 strings using (==) ,
 *      something that we shouldn't do
 *
 *
 * /// Solution
 *  - Make some changes to our HeavenlyBody class, but before we do that let's first see how the standard "equals" on the Standard Object class
 *    and the equals on String class both works
 *
 *      Object o = new Object();
        o.equals(o);            // Object.equals()
        "pluto".equals("");     // String.equals()
 *
 *  - So, if we actually Ctrl + click on the o.equals(o) method, the standard equals() is just the obj, the current obj is compared to whatever has
 *    been passed as a parameter
 *      - Which is a very basic referential equality, which isn't generally what we want
 *      - returns true if and only if both objects refer to the same object
 *
 * public boolean equals(Object obj) {
        return (this == obj);
    }
 *
 *  - It's basically saying if both references point to the same obj, then they're equal
 *
 *
 * /////// String.equals()
 *  - If we look at the String.equals() , we've got a completely different set of equals
 *  - It's clear that the String class, which inherits from the Object class has overwritten the equals() , and that's the code they're using to
 *    actually do a comparison from 1 string to another
 *
 *       public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof String) {
                String aString = (String)anObject;
                if (coder() == aString.coder()) {
                    return isLatin1() ? StringLatin1.equals(value, aString.value)
                                      : StringUTF16.equals(value, aString.value);
                }
            }
            return false;
        }
 *
 *  - The String.equals() performs a useful optimization first by checking if a String is being compared to itself
 *      - e.g.
 *       System.out.println("Compare String pluto to itself: "+ "pluto".equals("pluto"));
 *      - returns true if that's the case and skips any subsequent checking
 *  - It then checks that the obj being compared is a String
 *      - it's using instanceof which is a way of checking that a particular obj is a String
 *      - because as we can see the parameter that was passed to the equals() is of type Object
 *      - it's determining now whether the object is a String that we're comparing it with because at this point, there's no point of going
 *         any further if it's not a String , and will drop out and ultimately return false, if it wasn't a String
 *      - The code within the blocks will only be executed if it is a String
 *  - But in our example , when we're dealing with our own classes , we really need to be careful here
 *      - If our HeavenlyBody class is subclassed, instanceof will return true, when an object of the subclass is used here
 *      - Therefore , we need to look at an alternative check to perform when we implement equals() for our HeavenlyBody class
 *
 *  - It then checks that the lengths are equal
 *  - It then checks each character with the corresponding character in the other string for equality and the method returns false if any don't
 *    match and returns true if everything matches
 *
 * //////// Object.hashCode()
 *  - Unfortunately, overriding equals() is not really that straightforward ,and if 2 objects compare equal, then their hash codes must also be the
 *    same which is why you MUST also override the hashCode() if you override equals
 *
 * // Explain how a Hash collection stores objects
 *  - Refer to the diagram on the resource section
 *  - The essence of this is that when storing objects in a hashed collection, such as HashSet or HashMap , think of a collection as having a number
 *    of buckets to store the objects in,
 *  - The hashcode determines which bucket an object is going to go into,
 *  - There's a requirement that any objects that are equal should always have the same hashcode and ultimately they will then end up in the same
 *    bucket
 *  - But the opposite is not required
 *      - 2 objects that are equal, do not need to have different hashcodes
 *
 *  - In the diagram
 *      - Object 1 goes into Bucket 1
 *      - Object 2 goes into Bucket 2
 *
 *  - Now
 *      - Object 5 has the same hashcode as Object 4 and goes into the same bucket which is Bucket 4
 *      - Object 6 generates the same hashcode as Object 2 and goes into the same Bucket 2 as Object 2
 *
 *  - So, when we add an object, its hashcode tells the collection which bucket it should go into
 *  - There may already be objects in that bucket , so each is compared to the new object to make sure that it's not already in there
 *      - Because the comparison is performed using the equals() , the collection only know if it's already there if it's looking into the right
 *        bucket
 *      - So the hashcodes must be the same and equals return true
 *      - It's of no use for equals returning true, if the collection is checking the wrong bucket that is if the hashcode for the new object is not
 *        the same as an object that is equal to
 *
 *
 *  - So if we try to add another object that's equal to object, say object 6 , then the hashcode will indicate that bucket 2 is the one to check
 *      - The Collection class will then check all the objects in Bucket 2
 *      - When it finds Object 6, this will compare equal to the new object , and the new object is then not added to the collection
 *  - But on the other hand, if our new object breaks the rules and has a different hashcode to Object 6 , even though it compares equal to it, then
 *    the collection will then be looking in the wrong bucket and ultimately will not find it
 *      - The new object will then be added to the collection and at that point we'll get a duplicate but it can even get worse than that
 *
 *  - If we then iterate over the collection trying to find that new object, so that we can remove it, we'll get to the old Object 6 first and not
 *    the new one
 *  - So our code will then check if the object we've just pulled from the collection is the same that's the one we want to remove and of course
 *    equals() will return true
 *  - So we remove the object from the collection, but we've actually removed the original and not the duplicate
 *  - So, probably we wouldn't iterate over the collection if we had already had a reference to the object we wanted to remove, but hopeful we see
 *    the potential for error in this scenario
 *
 * //////
 *  - So , ultimately, there has to be strict relationship between hashcode and equals
 *      - If 2 objects compare equal, then they MUST have the same hashcode
 *  - IF 2 OBJECTS COMPARE EQUAL , THEN THEY MUST HAVE THE SAME HASHCODE
 *
 *
 *
 * //////
 *  - Let's now override the equals() and hashCode() methods in our HeavenlyBody class
 *  - We'll start by defining what we mean by equals
 *      - This is pretty easy to decide because astronomical objects are named well, 2 heavenly bodies are the same if they have the same name
 *  - If we're dealing with people, we may have to consider additional fields because many people can share the same name
 *      - In that case, we may also have a check of their DOB, SSN or other info that can be used to uniquely identify a person
 *  - So here's a first candidate for the equals() and the HeavenlyBody
 *
 *  - Override equals() and hashCode()
 *
 *     @Override
 *     public boolean equals(HeavenlyBody obj){
        if (this == obj)
            return true;
        System.out.println("obj.getClass() is "+obj.getClass());
        System.out.println("this.getClass() is "+this.getClass());

        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

         String objName = ((HeavenlyBody) obj).getName();
         return this.name.equals(objName);

        return false;
    }
 *
 *  - Use HeavenlyBody as the object and pass it to the equals()
 *
 *  - Test to see if we're dealing with the same object , we used (==) which we saw in both the String and Object class
 *      - If that's the case, we don't really need to do more processing, and we can just return true
 *      - Because we've established that this is the same object, so does the object equal itself, and at that point return true
 *
 *  - Then print out class names for debugging purposes
 *      System.out.println("obj.getClass() is "+obj.getClass());
        System.out.println("this.getClass() is "+this.getClass());
 *
 *  - Test if object passed is equal to null, and obviously if we're testing the obj that was passed to us against this, well, this can't be null
 *      because this is a current object, and so we know that it's been initialized
 *      - So if the obj is passed as null, we're going to say they aren't equal , or say, if the class from HeavenlyBody or the HeavenlyBody class
 *          is not equal to this.getClass() , if they don't match, basically , we're going to return false
 *
 *  - Then cast the obj passed to a string
 *      String objName = ((HeavenlyBody) obj).getName();
 *
 *  - And compare converted string objName with the current name
 *      return this.name.equals(objName);
 *
 * //////
 *
 *  - Notice that the @Override has got an error , and it's there by design
 *  - It is a very common and easy mistake to overload the equals() rather than override it
 *      - It's easy to think that because we're going to be comparing HeavenlyBody objects, the parameters to equals() would be HeavenlyBody
 *  - But to guard against this, always use @Override notation when overriding methods
 *      - This way, if we get the method signature wrong, the code is not going to compile and hence the error there
 *  - Without the @Override, our equals() overloads the one from the Base class and ultimately, it's never going to be used by the Collections
 *    class
 *  - IntelliJ IDEA will automatically get a stub for you with the annotation - with alt+Insert
 *
 * /// Solution
 *  - Change parameter of the equals() to Object rather than HeavenlyBody which is going to overload instead of overriding
 *
 *
 * /////
 *  - @Override , helps the compiler to check whether you've got the right signature for the method
 *
 * Summary of the equals()
 *  - Checks to see if the object is compared to itself first
 *      - returns true if the object you're comparing is the same object in memory
 *  - Then does a similar test to the instanceof , a test we saw with the String class , but inorder to make sure that subclasses do not compare
 *    equal, we check the actual class of the object being compared against the class of the object the method is in
 *      - IF we try to call getClass() on null , we'll get a NullPointerException and that's why we need to test for null first
 *      - null will fail the instanceof check and so an explicit test for null is required when using instanceof
 *      - we're printing obje.getClass() and this.getClass for debugging purposes and we won't leave that in the final code
 *      - It's always useful to see when our equals() is called as well as explaining what the test is doing
 *
 *  - Then since we've established that we'll be comparing names, then that becomes our criteria for an equals match in the HeavenlyBody
 *      - And given that the String class, already has an equals(), we can cast the obj we're comparing to a HeavenlyBody and then use
 *          String.equals() to compare its name against the name of the current object
 *      - We know this cast is safe and won't throw an exception because we've already checked for null and we've also ensured that object
 *        is of HeavenlyBody (both objects must actually be of the same Class type)
 *
 * ////
 *  - You might think that we're ready tu run this, but we're not really ready to run this yet because we need to override equals() and hashCode()
 *    both at the same time
 *  - Technically, it's not going to be correct, but because it's informative, let's just run it anyway but beraing in mind that it's not going
 *    to be valid completely until we implement hashCode()
 *
 *
 * // Running this
 *  - There's 2 things we can actually see from this output
 *      1. equals() wasn't called - print lines weren't printed
 *      2. Pluto is still showing twice
 *
 *  - This is the reason why you would want to implement hashCode() , and because we haven't done that, the Second Pluto was put into a different
 *      bucket
 *  - And because there wasn't something in the bucket, there's no need to call equals, so both observations come down to the same chords
 *      - So effectively, Pluto is being repeated twice
 *
 *
 * //// hashCode()
 *  - Override hashCode()
 *  - In terms of the implementation, it actually appears extremely easy
 *  - And the reason is because the String class has a hashCode() and because we're comparing only Strings , because we decided in the equals()
 *    that our comparison is just going to be the name of the heavenly body
 *
 *  - It would seem that we just generate the hashcode for the body's name and in other words, we can do something like this
 *      return this.name.hashCode();
 *  - So, the hashCode() in this case that we're calling , is the hashCode() from the String class
 *
 *  - This would actually work here and that's because we've typed the sets and the map using Generics , so there is a little danger in comparing
 *    say a HeavenlyBody to a String , but not a good practice to do this
 *      - is because HeavenlyBody called Pluto would probably have a different hashcode to the String Pluto
 *  - So how you generate hashcodes is pretty much upto you as long as the same obj will always generate the same hashcode during any execution of
 *    your program and also as long as objects that compare equal have the same hashcode, once that's done then the requirement is satisfied
 *
 *  /// Research on overriding hashcodes on internet
 *  - You'll find some recommendations that you can just return 0
 *  - Every instance of your obj will then have the same hashcode, which we'll certainly meets the requirement that 2 objects that compare equal have
 *    the same hashcode and it also means that no matter how many times you generate a hashcode for an object, it would always be the same in that
 *    scenario
 *      - therefore returning 0, in that scenario meets the requirements for a hashCode() , but if you think about it, it then sort of misses the
 *         purpose of hashing in the first place
 *  - If every object of a hashed collection ends up in the same bucket, then you totally lose the benefits of using a Hashed Collection class
 *  - The performance in that case will drop to that of a LinkedList or a similar structure , because once the single bucket has been located which is
 *    Bucket 0, the items will each need to be compared to see if the required one is there
 *      - very much less efficient
 *  - Adding a number to the names hash code will make it different
 *      - Any number that doesn't risk overflowing an integer will do
 *      - Use 57 and print out a temporary message so that we can see the hashCode() being used
 *
 *           return this.name.hashCode();
 *
 *  - This also guarantees that we've got a non-zero number that's being returned for each of our Hashes
 *
 *  - Again we're using a code that comes back from a string and adding 57 to that as well
 *
 * /////////////////// Running this
 *  - From the output printed, 2 things are happening
 *      - we can now se that the hashCode() is being called from the print out
 *      - Pluto is now printed only once
 *          - first Pluto with an orbiting period of 248 was added
 *          - duplicate Pluto with an orbiting period of 842 was ignored
 *
 *  - Ultimately, this now fits the behavior of a Set<E> that an object will not be added if it already exist
 *
 *
 * //// Rules For Equals
 *  - Check for below link in the resource section
 *      https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
 *
 *
 *
 */
public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();
    public static void main(String[] args) {

        // Create Heavenly Bodies
        HeavenlyBody mercury = new HeavenlyBody("Mercury",88);
        HeavenlyBody venus = new HeavenlyBody("Venus",225);
        HeavenlyBody earth = new HeavenlyBody("Earth",365);
        HeavenlyBody mars = new HeavenlyBody("Mars",687);
        HeavenlyBody jupiter = new HeavenlyBody("Jupiter",4332);
        HeavenlyBody saturn = new HeavenlyBody("Saturn",10759);
        HeavenlyBody uranus = new HeavenlyBody("Uranus",30660);
        HeavenlyBody neptune = new HeavenlyBody("Neptune",165);
        HeavenlyBody pluto = new HeavenlyBody("Pluto",248);

        // Add 9 planets to Set<HeavenlyBody> planets HashSet
        planets.add(mercury);
        planets.add(venus);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(saturn);
        planets.add(uranus);
        planets.add(neptune);
        planets.add(pluto);

        // Add HeavenlyBodies(Planets) to the solarSystem Map
        solarSystem.put(mercury.getName(), mercury);
        solarSystem.put(venus.getName(), venus);
        solarSystem.put(earth.getName(), earth);
        solarSystem.put(mars.getName(), mars);
        solarSystem.put(jupiter.getName(), jupiter);
        solarSystem.put(saturn.getName(), saturn);
        solarSystem.put(uranus.getName(), uranus);
        solarSystem.put(neptune.getName(), neptune);
        solarSystem.put(pluto.getName(), pluto);

        // Add HeavenlyBodies - Moons
        HeavenlyBody earthMoon = new HeavenlyBody("Moon",27);
        HeavenlyBody deimosMoon = new HeavenlyBody("Deimos",1.3);
        HeavenlyBody callistoMoon = new HeavenlyBody("Callisto",16.7);
        HeavenlyBody ganymedeMoon = new HeavenlyBody("Ganymede",7.1);
        HeavenlyBody europaMoon = new HeavenlyBody("Europa",3.5);
        HeavenlyBody iOMoon = new HeavenlyBody("Io",1.8);
        HeavenlyBody phobosMoon = new HeavenlyBody("Phobos",0.3);



        // Add Moons to the Solar System
        solarSystem.put(earthMoon.getName(), earthMoon);
        solarSystem.put(deimosMoon.getName(), deimosMoon);
        solarSystem.put(callistoMoon.getName(), callistoMoon);
        solarSystem.put(ganymedeMoon.getName(), ganymedeMoon);
        solarSystem.put(europaMoon.getName(), europaMoon);
        solarSystem.put(iOMoon.getName(), iOMoon);
        solarSystem.put(phobosMoon.getName(), phobosMoon);

        // Add moons specific to Mars
        mars.addMoon(deimosMoon);
        mars.addMoon(phobosMoon);

        // Add moons specific to Jupiter
        jupiter.addMoon(callistoMoon);
        jupiter.addMoon(ganymedeMoon);
        jupiter.addMoon(europaMoon);
        jupiter.addMoon(iOMoon);

        // Print planets
        System.out.println("Planets \n"+
                "=========");
        for (HeavenlyBody planet : planets) {
            System.out.println("\t" +planet.getName());
        }

        // print moons orbiting Jupiter/Mars
        // HeavenlyBody body = solarSystem.get("Jupiter");
        HeavenlyBody body = solarSystem.get("Mars");
        System.out.println("Moons orbiting "+body.getName());
        for (HeavenlyBody jupiterMoon : body.getSatellites()) {
            System.out.println("\t"+ jupiterMoon.getName());
        }

        // Create an empty HashSet of Set<HeavenlyBody> moons
        Set<HeavenlyBody> moons = new HashSet<>();

        // loop through all the planets and get moons added to each
        // populate to Set<HeavenlyBody> moons HashSet
        for (HeavenlyBody planets : planets) {
            moons.addAll(planets.getSatellites());
        }

        // print out all the moons from Set<HeavenlyBody> moons
        System.out.println("All Moons");
        for (HeavenlyBody moon: moons ) {
            System.out.println("\t" +moon.getName());
        }


        // Create another Pluto object
        HeavenlyBody plutoDuplicate = new HeavenlyBody("Pluto",842);
        planets.add(plutoDuplicate);


        //Print out all the planets and their orbitalPeriod
        System.out.println("///// All Planets - With pluto added as a duplicate ///////");
        for (HeavenlyBody planet: planets) {
            System.out.println(planet.getName() +" : "+ planet.getOrbitalPeriod());
        }

        //check if references are same - referential equality
        //HeavenlyBody temp = new HeavenlyBody("Pluto",248);
       // System.out.println(temp.equals(temp)); // true


        // create an instance of the base Object - all Java classes inherit from this class
        Object o = new Object();
        o.equals(o);
        "pluto".equals("");

       // System.out.println("Compare String pluto to empty string: "+ "pluto".equals(""));
       // System.out.println("Compare String pluto to itself: "+ "pluto".equals("pluto"));

    }
}
