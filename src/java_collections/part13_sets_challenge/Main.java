package java_collections.part13_sets_challenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* Sets Challenge - HeavenlyBody
*
*
*  //////////////// PART 1 - of the video ///////////////////
*
* Modify the previous HeavenlyBody example so that the HeavenlyBody class also has a "bodyType" field.
* This field will store the type of HeavenlyBody (such as STAR, PLANET, MOON, etc).

* You can include as many types as you want, but must support at least PLANET and MOON.

* For each of the types that you support, subclass the HeavenlyBody class so that your program can create objects of the appropriate type.

* Although astronomers may shudder at this, our solar systems will allow two bodies to have the same name as long as they are not the
   same type of body: so you could have a star called "BetaMinor" and an asteroid also called "BetaMinor", for example.

* Hint: This is much easier to implement for the Set than it is for the Map, because the Map will need a key that uses both fields.

* There is a restriction that the only satellites that planets can have must be moons.
* Even if you don't implement a STAR type, though, your program should not prevent one being added in the future
*   (and a STAR's satellites can be almost every kind of HeavenlyBody).

Test cases:
1. The planets and moons that we added in the previous video should appear in the solarSystem collection and in the sets of moons for the
   appropriate planets.

2. a.equals(b) must return the same result as b.equals(a) - equals is symmetric.

3. Attempting to add a duplicate to a Set must result in no change to the set (so the original value is not replaced by the new one).

4. Attempting to add a duplicate to a Map results in the original being replaced by the new object.

5. Two bodies with the same name but different designations can be added to the same set.

6. Two bodies with the same name but different designations can be added to the same map, and can be retrieved from the map.
*
*
* //////////////////////////////////
* Set Challenge Solution
* /////////////////////////////////
*
* GoTo HeavenlyBody class
*   - Add bodyType fields - can be done in several ways
*
*       - define the types first that the class knows about
*           private final int bodyType;
*
*           - grouping CONSTANTS together using an enum
*
*       - or specifying each bodyType explicitly
*           private static final int STAR = 1;
*           private static final int PLANET = 2;
*           private static final int DWARF_PLANET = 3;
*           private static final int MOON = 4;
*           private static final int COMET = 5;
*           private static final int ASTEROID = 6;
*
*           - have a unique number for each of the various body types
*
*   - So instead of having multiple declaration for each, we can define a Type, an enum , BodyTypes , and the valid types for this BodyType
*   - One advantage of using an enum here is that enum are Types
*       - That means, when we change the constructor, we can make sure that it only accepts a valid body type
*       - It also means we can change int here
*           private final int bodyType;
*
*       - to use BodyTypes which is our enum
*           private final BodyTypes bodyType;
*
*       - And now our bodyType variable is of type BodyTypes
*
*       - And then what we can do at that point is we can with our constructor, change the constructor to also accept the parameter bodyType
*       - For example
*
*             public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
                this.name = name;
                this.orbitalPeriod = orbitalPeriod;
                this.satellites = new HashSet<>();
                this.bodyType = bodyType;
            }
*
*       - And initialize the bodyType field as we would with any other fields
*
*       - The great thing about an enum is that it's only going to accept a variable/ or a parameter that's got one of these types specified
*
*       - Add a getter for the bodyType as well
*
* //////// Benefits of enum //////
* - With the integer constants we added earlier, before we changed bodyType t use Enum type, there wouldn't be Compile-time checking that the integer
*   body type was one of the values that the Class knows about
*       - We'll have to include in the constructor to check that
*       - the best we can get is a runtime error
* - The advantage of using an enum here is that we get a Compile time if the body type isn't valid
*       - If someone tries to sort of pass an invalid type in the constructor, we're going to get an error
* - We'll see another advantage when it comes to printing out the heavenly bodies
*
* - A nested enum is automatically static
*       - It's possible to refer to the enum values without creating an instance of a HeavenlyBody object
*
*
* - There's also a requirement that a Planet satellite can only be moons
*   - IF we look at how we're adding moons to our satellites
*
*         public boolean addMoon(HeavenlyBody moon){
                return this.satellites.add(moon);
            }
*   - We might be tempted to do something like this
*
*         public boolean addMoon(HeavenlyBody moon){
*            if (moon.getBodyType() == BodyTypes.MOON){
                return this.satellites.add(moon);
             }
            return false;
         }
*   - To make sure that the planet satellites that are passed are only moons
*   - This actually isn't a good idea
*       - Thinking about it, it really the name itself or the method is badly chosen
*   - HeavenlyBodies other than planets can have satellites, and they don't have to be moons
*   - Most of the objects in our solar system orbit the sun, and if we restrict addMoon(HeavenlyBody moon) like this, it makes it impossible to
*     give a star satellites
*   - We'll put it back the way it was and we'll also refactor it and name it addSatellite(HeavenlyBody moon), which really better reflects
*       what is happening
*       - now any type of heavenly body can now be added as a satellite or of any other
*   - If we want to restrict planets to only have moons orbiting them , we can override the addSatellite() in the Planet subclass which is a much
*     better design
*
* //////
*
* The specification of the HeavenlyBody class has changed to require 2 fields to be used to identify a body and not just the name as previously
*   - our solar systems will allow two bodies to have the same name as long as they are not the same type of body:
*   - For example
*       -  you could have a star called "BetaMinor" and an asteroid also called "BetaMinor"
*   - Now we've added the body type field and as a result, we don't really need to change the equals() and hashCode() methods to reflect this change
*      because it wouldn't be correct now without that
*   - The change to equals() isn't complicated, we really need to add another test to see if the bodyTypes are the same if the objects have the same
*      name
*       - use instanceof
*       - cast the obj passed to HeavenlyBody obj
*       - check if names are equals
*           - if so, compare bodyType
*
*        if (obj instanceof HeavenlyBody){
            HeavenlyBody theObject = (HeavenlyBody) obj;
            if (this.name.equals(theObject.getName())){
                return this.bodyType == theObject.getBodyType();
            }
        }
        return false;
*
*   - We've used instanceOf because we intend to subclass the HeavenlyBody to create the PLANET, MOON and other objects
*   - And another reason we're doing this is to avoid problems with equals not being symmetric, we're going to mark the equals() as final and at the
*      same time mark hashCode() final too as well
*
*   - Update hashCode()
*      call bodyType.hashCode() and add to the return statement
*   - and this now gives us a unique hashcode
*
*
* ////////////
* - The final method we'd like to add is optional but will make printing the Heavenly Bodies a bit easier
*   - We'll override the toString() so that it also displays the body type and orbital period as well as the name
*       - print name , bodyType and orbitalPeriod
*
* ///////// last benefit of using enum -
* this.bodyType
*   - when you go to print the enums out, in this case, we're going to print out the toString() that comes with the enum returns the name of the
*     enum
*       - In other words, we'll get the names that we've defined, e.g. STAR, PLANET, MOON etc and that can be very useful with something like this
*           where we want to print out the body type and not have to sort of convert a number back to a description or something along those lines
*
* /////
* - And just in case it's not obvious, when we use an object in a String concatenation like we've done below
*       return this.name + ": "+ this.bodyType + ", "+ this.orbitalPeriod;
*
* - Java implicitly calls the toString() to obtain the string representation of the object
*
*
* /////
* - That's pretty much it for our HeavenlyBody class now, we're almost ready to create the Subclasses
* - We've made the class final though which means that it can't be subclassed, and we need to change the declaration to remove the final keyword
*    so that it can be subclassed
*
*
* //////////////// PART 2 - of the video ///////////////////
* Create subclasses
*   - One for Planet
*       - Override addSatellite() to only accept bodyTypes of type Moon
*
*   - One for DwarfPlanet
*
*   - One for Moon
*
* //////////// main() //
* - Instead of now creating heavenly body like below
*       HeavenlyBody mercury = new HeavenlyBody("Mercury",88);
*
*   - we can now do it like this, and be more specific that we're creating planet
*
*       HeavenlyBody mercury = new Planet("Mercury",88);
*       planets.add(mercury);
*       solarSystem.add(mercury.getName(),mercury);
*
*   - by calling Planet constructor as shown above which is now correctly using the sub class remembering the 3rd constructor is in the Planet
*     constructor which ultimately calls the HeavenlyBody with the 3rd parameter being the body type
*
*   - we do similar thing with moon creating planet
*       HeavenlyBody earthMoon = new Moon("Moon",27);
*       solarSystem.add(earthMoon.getName(),earthMoon);
*
*   - then add moons orbiting specific planets
*       jupiter.addSatellite(ganymedeMoon);
*
*
* //////
*  - We can also create below
*        HeavenlyBody plutoDuplicate = new HeavenlyBody("Pluto",842);*
*  - As
*        HeavenlyBody plutoDuplicate = new HeavenlyBody("Pluto",842, HeavenlyBody.BodyTypes.PLANET);
*
*  - But for the sake of being consistent, we'll just do it like below
*
*        HeavenlyBody plutoDuplicate = new Planet("Pluto",842);
*
*
* /////////
* Now we've got all of them added and we have printouts
*   - We can now use toString that we added in the HeavenlyBody class and start using it in some of the print outs
*
* After we've added the duplicatePluto
*   - We can change
*       for (HeavenlyBody planet: planets) {
            System.out.println(planet.getName() +" : "+ planet.getOrbitalPeriod());
        }
*
*   - to
*       for (HeavenlyBody planet: planets) {
            System.out.println(planet);
        }
*   - which will automatically invoke our toString() that we overrode in the HeavenlyBody class
*
*
*
*
* /////////// Running this :- Test case 1 : passes
* - Notice how enum is printing, and it prints the actual description which is quite handy
* - And we've added Planets, Moons and then a List of all moons and everything seems to be working fine
*
* /// Test case 2 : passes
* - And because we made the second Pluto planet and correctly overridden equals() and hashCode(), we can see that it hasn't been added to our
*   Set<HeavenlyBody> planets again and not appearing twice
*       - We can confirm that the second duplicatePluto wasn't added
*
*           HeavenlyBody plutoDuplicate = new Planet("Pluto",842);
            planets.add(plutoDuplicate);
*
*
* /////////
* - Although an argument can be made for allowing objects of type HeavenlyBody to be created, perhaps to cater for new bodies that haven't been
*   discovered yet, it's probably better to make HeavenlyBody abstract
* - One problem is that our Second Pluto can have planets added to its set of planets : Set<HeavenlyBody> satellites
*       - It could even have the sun as a satellite, which is going to lead to a very strange solar system
* - Before we check that Pluto can be added again to the Set<HeavenlyBody> , if it's DwarfPlanet rather than Planet, we're going to make HeavenlyBody
*  abstract
*
* - So let's update HeavenlyBody class to abstract
*   - This means we can't instantiate our HeavenlyBody class now and would get an error as a result , even if we pass the expected parameters to
*     the Pluto instance
*        HeavenlyBody plutoDuplicate = new HeavenlyBody("Pluto",842, HeavenlyBody.BodyTypes.PLANET);
*   - And this is because HeavenlyBody class is abstract & it can't be instantiated
*
* /// Solution - Test case 5 - passes
*   - Update 2nd duplicatePluto to DwarfPlanet instead
*
*   - And if we run this, Pluto will appear twice because we've changed the bodyType to be a DwarfPlanet and by default, we've added it as a planet,
*     which should be a different body type
*
*   - And if we run this, we now have Pluto appearing twice as a PLANET and DWARF_PLANET and with a different orbital periods as we'd expect
*   - And now we can say the test case 5 has passed
*
*
*
* ///////  Test case 2 : a.equals(b) must equal b.equals(a)
*   - We need to test this and make sure that equals is symmetric
*   - It's actually pretty easy to check and what we really need is to just create 2 objects  and compare them both ways around
*
        HeavenlyBody earth1 = new Planet("Earth",365);
        HeavenlyBody earth2 = new Planet("Earth",365);

        System.out.println(earth1.equals(earth2)); // true
        System.out.println(earth2.equals(earth1)); // true
*
*       - returns true in both cases which means they're both symmetric
*
*   - However, below should return false
*
*       System.out.println(earth1.equals(pluto)); // false
        System.out.println(pluto.equals(earth1)); // false
*
*
*
*  //////////////// PART 3 - of the video ///////////////////
*
*  - Let's look at a potential problem with our Solar System map :  Map<String , HeavenlyBody> solarSystem
*  - If we actually look at how we're defining our map is what we're doing is we're defining it purely with the name of the body string and there's
*     currently no way to cater for the body type
*  - And obviously because we're using a map, we've only got the concept of a Key and a Value
*  - So how do we get around that ?
*
*  ////
*   - This is actually a common problem when attempting ton store and retrieve more complex objects , whether using one of the collection classes
*     or a database and there are many solutions to this problem
*   - Databases allow records to be selected on several fields, and it's possible to make a primary key that consists of all the fields that uniquely
*     define each record
*   - Another approach is creating unique serial numbers for each record, such as SSN , national insurance numbers or tax file numbers
*
*   - 1 drawback of this approach is that you can't retrieve someone's record from a map unless you actually know their serial number
*   - Most objects that have been discovered in the universe interestingly enough are given a serial number by the international astronomical union
*       - check link for more info on this
*           https://en.wikipedia.org/wiki/International_Astronomical_Union
*
*   - So what we could do is we could replace our string with that, though that also has got disadvantages , because the number of course must be known
*     in advance, in order to retrieve the appropriate entity or entry from the map
*   - So in this specific example, that would probably be the best option, but these examples and challenges are designed to demonstrate techniques
*      that can be used in different applications
*   - So, if you have added IAU number field to the class , the International Astronomical Union, that's a perfectly valid solution, and you'd instead
*     reference that rather than the name and use that in your look-ups and have that somewhere in the HeavenlyBody for linking that
*   - Or also concatenate name and body type as well, such as Pluto-Planet , or Pluto-DwarfPlanet or things along those lines
*
* /////
*   - In Tim's case,we'll create a class, a Key class and give the HeavenlyBody class a field of type Key
*   - Because this key is going to be closely tied to the HeavenlyBody class, we're going to make it a static inner class of the HeavenlyBody class
*
* Key class
*
* Fields
*   name : String
*   bodyType : BodyTypes
*
* Constructor
*   Key(String name , BodyTypes bodyType)
*
* Getters
*   getName() : String
*   getBodyType() : BodyTypes
*
* Override equals() and hashCode()
*
*   equals()
*       - cast the obj passed to Key object
*       - check if names are equal
*           - check the types as well and return true/false
*
*   hashCode()
*       - do something similar
*           - return this.name.hashCode() + 57 + this.bodyType.hashCode()
*
* /////
*   - The Key class is very simple & it gives us a way to deal with a combination of name and bodyType as a single object
*
*   - Because we'll be using the Key in a map, that's why we needed to override the equals() and hashCode() and looks pretty much identical to
*     the methods we've previously overroded in HeavenlyBody itself
*
*   - Generally speaking, it's a bad idea to have duplicated code like this , but we can fix that by making the HeavenlyBody methods just call the methods
*     in our new Key class
*   - Now the equals() still needs to check that the obj being compared is a HeavenlyBody and they must cast it to a HeavenlyBody type in order to
*     access its key
*   - So let's go ahead and do that
*
*   - change HeavenlyBody.equals()
*       if (this.name.equals(theObject.getName())){
                return this.bodyType == theObject.getBodyType();
         }
*
*   - to
*        return this.key.equals(theObject.getKey());
*
*   - But then we need to define/ add the key field to the HeavenlyBody class
*
*   - then change HeavenlyBody.hashCode()
*       return this.name.hashCode() + 57 + this.bodyType.hashCode();
*
*   - to
*       return this.key.hashCode();
*
*   - then we can replace the 2 key fields, name and body type , with a Key key
*       - remove String name and bodyType
*       - add
*           private final Key key
*
*   - then , change our constructor
*       - comment out
*           this.name = name;
*           this.bodyType = bodyType;
*       - add
*           this.key = new Key(name,bodyType);
*
*       - and even though we've defined Key constructor as private, that will still work because it's an inner class and that's how we're
*         able to instantiate it in the HeavenlyBody constructor
*
*       - next, remove getter for name and bodyTypes as they're no longer attributes anymore
*
*       - next add the getter for the key key attribute
*           getKey() : Key
*
*       - then update toString()
*           - from
*              public String toString() {
                    return this.name + ": "+ this.bodyType + ", "+ this.orbitalPeriod;
                }
*
*           - to
*
*               public String toString() {
                    return this.key.name + ": "+ this.key.bodyType + ", "+ this.orbitalPeriod;
                }
*   - The next think we need to do is we need to add a method to make a Key
*       - The actual implementation is not something the users of the class, need to know about, but in order to retrieve a HeavenlyBody from the
*         map , they will need a key, when all they would really have is name and body type
*       - What we want to do is pass a name and a body type and have a method return the valid key which is going to be an object that they can then
*          use to look up that entry in our map effectively
*
*   - Add the method in the HeavenlyBody class and make it static so that the key can be obtained without really having an instance of the HeavenlyBody
*      to call the method on
*
*        public static Key makeKey(String name, BodyTypes bodyTypes) {
             return new Key(name,bodyTypes);
          }
*
*   - And now we'll return a new Key object that they can use to do look ups in the Map object
*
*   - And this now works and we don't have any errors
*
* /////////////
*   - We're now done making the changes and we now need to go back to Main and we need to change our Map
*
*        private static Map<String,HeavenlyBody> solarSystem = new HashMap<>();
*
*   - to use Heavenly.Key object instead of the String obj
*
*        private static Map<Key,HeavenlyBody> solarSystem = new HashMap<>();
*
*   - Then we need to replace all instances of getName() with getKey()
*
*
*
*
* //////////////
*  - The next thing is because, we're referencing key, we haven't yet added a toString() to the Key class and we only have toString in the
*    HeavenlyBody , but we really need one in the key as well if we're going to be referencing that
*
*  - Override toString()
*       - print name and body type
*
*  - And of course in the HeavenlyBody
*       - toString() returns name, body type and the orbitalPeriod but they're different for that reason because they're different objects
*
*
*
* /////////////
*  - Next look at the warnings that we're getting
*
*    HeavenlyBody body = solarSystem.get("Mars");
*
*  - We've clearly got a problem and that's because we're trying to access the object using the name , and we're now obviously using keys and we
*    need to change that so that we can actually pass that parameter and get back a key
*       - And this is because clearly that's not going to work anymore now that we've actually replace the keys which was originally a String now
*           that we've replaced that with a key object
*  - So, what we need to do is use our makeKey() for that reason and pass "Mars" and the type
*
*    HeavenlyBody body = solarSystem.get(makeKey("Mars",HeavenlyBody.BodyTypes.PLANET));
*
* ////////////
*   - Got an error from Planet.java with addSatellite() and we need to change
*
*       heavenlyBody.getBodyType()
*
*   - To
*
*       heavenlyBody.getKey().getBodyType()
*
*
* /// Running this
*
*   - Now we have everything working like it was before
*       - Pluto is now appearing twice as we change the type, bofy type from Planet to DwarfPlanet
*       - We've got all
*           - our Planets,
*           - moons of Mars
*           - and all the moons in our solar system - that we have defined in our code anyway
*
* ////// Test cases 4 and 6
*
* 4- Attempting to add a duplicate to a Map results in the original being replaced by new object.
*       - We can change pluto back to a Planet
*       - Remember we added Pluto again as a DwarfPlanet
*
*       - But let's change
*
*           HeavenlyBody plutoDuplicate = new DwarfPlanet("Pluto",842);
            planets.add(plutoDuplicate);
*
*       - To a Planet instead
*
*           HeavenlyBody plutoDuplicate = new Planet("Pluto",842);
            planets.add(plutoDuplicate);
*
*       - Sort of add a duplicate to the existing Pluto & we should get the value of the olf pluto overwritten
*
*       - If, we run this, we can see now , Pluto planet, 842 nad was updated
*           - The 2nd entry is now null , because we no longer have a DwarfPlanet
*
*
* 6- Two bodies with the same name but different designations can be added to the same map,and can be retrieved from the map.
*      - Add Pluto
*           - DwarfPlanet to : Map<HeavenlyBody.Key,HeavenlyBody> solarSystem
*           - Has the same name with Pluto-Planet
*
*           solarSystem.put(plutoDuplicate.getKey(),plutoDuplicate);
*
*      - Retrieve both from the Map
*            System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto",HeavenlyBody.BodyTypes.PLANET)));
*            System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto",HeavenlyBody.BodyTypes.DWARF_PLANET)));
*
*
* /////// Print the Entire SolarSystem map
*   - Use a foreach and loop through the solarSystem.values()
*       - Return type HeavenlyBody with a toString() that prints : name,bodyType and orbitalPeriod
*
*   - change plutoDuplicate to a DwarfPlanet
*
*
*/
public class Main {
    private static Map<HeavenlyBody.Key,HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();
    public static void main(String[] args) {
        // Create Planets
        HeavenlyBody mercury = new Planet("Mercury",88);
        HeavenlyBody venus = new Planet("Venus",225);
        HeavenlyBody earth = new Planet("Earth",365);
        HeavenlyBody mars = new Planet("Mars",687);
        HeavenlyBody jupiter = new Planet("Jupiter",4332);
        HeavenlyBody saturn = new Planet("Saturn",10759);
        HeavenlyBody uranus = new Planet("Uranus",30660);
        HeavenlyBody neptune = new Planet("Neptune",165);
        HeavenlyBody pluto = new Planet("Pluto",248);

        // Add to Set<HeavenlyBody> planets HashSet
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
        solarSystem.put(mercury.getKey(), mercury);
        solarSystem.put(venus.getKey(), venus);
        solarSystem.put(earth.getKey(), earth);
        solarSystem.put(mars.getKey(), mars);
        solarSystem.put(jupiter.getKey(), jupiter);
        solarSystem.put(saturn.getKey(), saturn);
        solarSystem.put(uranus.getKey(), uranus);
        solarSystem.put(neptune.getKey(), neptune);
        solarSystem.put(pluto.getKey(), pluto);

        // Create HeavenlyBodies - Moons
        HeavenlyBody earthMoon = new Moon("Moon",27);
        HeavenlyBody deimosMoon = new Moon("Deimos",1.3);
        HeavenlyBody callistoMoon = new Moon("Callisto",16.7);
        HeavenlyBody ganymedeMoon = new Moon("Ganymede",7.1);
        HeavenlyBody europaMoon = new Moon("Europa",3.5);
        HeavenlyBody iOMoon = new Moon("Io",1.8);
        HeavenlyBody phobosMoon = new Moon("Phobos",0.3);

        // Add HeavenlyBodies(Moons) to the solarSystem Map
        solarSystem.put(earthMoon.getKey(), earthMoon);
        solarSystem.put(deimosMoon.getKey(), deimosMoon);
        solarSystem.put(callistoMoon.getKey(), callistoMoon);
        solarSystem.put(ganymedeMoon.getKey(), ganymedeMoon);
        solarSystem.put(europaMoon.getKey(), europaMoon);
        solarSystem.put(iOMoon.getKey(), iOMoon);
        solarSystem.put(phobosMoon.getKey(), phobosMoon);


        // Add moons specific to Mars Planet
        mars.addSatellite(deimosMoon);
        mars.addSatellite(phobosMoon);

        // Add moons specific to Jupiter Planet
        jupiter.addSatellite(callistoMoon);
        jupiter.addSatellite(ganymedeMoon);
        jupiter.addSatellite(europaMoon);
        jupiter.addSatellite(iOMoon);


        ///////////////////////////////////////////////////////////////////////////////////////

        // Print planets
        System.out.println("Planets \n"+
                "=========");
        for (HeavenlyBody planet : planets) {
            System.out.println("\t" +planet.getKey());
        }

        // print moons orbiting Jupiter/Mars
        // HeavenlyBody body = solarSystem.get("Jupiter");
        HeavenlyBody body = solarSystem.get(HeavenlyBody.makeKey("Mars",HeavenlyBody.BodyTypes.PLANET));
        System.out.println("Moons orbiting "+body.getKey());
        for (HeavenlyBody jupiterMoon : body.getSatellites()) {
            System.out.println("\t"+ jupiterMoon.getKey());
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
            System.out.println("\t" +moon.getKey());
        }


        // Create another Pluto object
        HeavenlyBody plutoDuplicate = new DwarfPlanet("Pluto",842);
        planets.add(plutoDuplicate);


        //Print out all the planets and their orbitalPeriod
        System.out.println("///// All Planets - With pluto added as a duplicate ///////");
        for (HeavenlyBody planet: planets) {
            System.out.println(planet);
        }


        //a.equals(b) and b.equals(a)
        System.out.println("Symmetric Test : \n"+
                "==================");
        HeavenlyBody earth1 = new Planet("Earth",365);
        HeavenlyBody earth2 = new Planet("Earth",365);
        System.out.println(earth1.equals(earth2)); // true
        System.out.println(earth2.equals(earth1)); // true


        System.out.println(earth.equals(pluto)); // false
        System.out.println(pluto.equals(earth)); // false



        /// Test case 6 - Add duplicate
        solarSystem.put(plutoDuplicate.getKey(),plutoDuplicate);
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto",HeavenlyBody.BodyTypes.PLANET)));
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto",HeavenlyBody.BodyTypes.DWARF_PLANET)));


        //// Print entire solar system
        System.out.println("===================================");
        System.out.println("The solar system keys contains: ");
        for (HeavenlyBody.Key heavenlyBody : solarSystem.keySet()) {
            System.out.println(heavenlyBody);
        }

        //// Print entire solar system - orbital period
        System.out.println("===================================");
        System.out.println("The entire solar system contains: ");
        for (HeavenlyBody heavenlyBody : solarSystem.values()) {
            System.out.println(heavenlyBody);
        }

    }
}
