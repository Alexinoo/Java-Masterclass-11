package com.example.game;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Example Package - Create a new project - PackageDemo
 * .....................................................
 *
 * - Naming conventions with Java is a com.example and org.example are actually reserved are reserved and can be used for packages that you're
 *   never going to distribute and
 *      - you can actually substitute them with your own domain, as the instructor is using com.timbuchalka
 *
 * - Notice that we can't also create package from the root folder
 *      - Has to be from the src folder
 * - Also note that we can't update the name of the package above "com.example.mypackage" to let's say "org.example.mypackage", they need to
 *   match with the parent and sub-dir folders to that file
 *      - IntelliJ will flag that as an error
 * - Renaming a package through refactoring actually creates a new package
 *
 *      - Rename to "com.example.game"
 *
 * - Creating our custom package "com.example.game"
 *      - Copy ISaveable interface, Player and Monster classes form "interfaces_inner_and_abstract_classes > part3_interface_challenge"
 *      - IntelliJ automatically updates the new package to point to the package that the classes were moved into
 *
 * - So let's create a package for these 3 files : Player.java, Monster.java and ISaveable.java and actually use them in another project
 *      - Add some code to test in the Main.java to make sure it's still working before we export it to a library
 *      - Copy contents from Main.java
 *
 * - Run and make sure everything is working as expected
 *      - Next, delete the Main.java class so that when we create a new project, it will have its own Main.java file
 *      - Here , I will just rename it to something else
 *

 *
 * - Create PackageDemo
 *      - Add the files to this project : Player.java, Monster.java , ISaveable.java , Main.java
 *          - Test everything is working and then delete the Main.java
 *
 * - Next
 *      File > Project Structure > Artifacts > Click Add > Select Jar  > From modules with dependency >  Click OK/Apply
 *
 *
 *
 * N/B
 *  - where a jar is a  (Java archive - zip file for java code)
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        Player alex = new Player("Alex",10,15);

        System.out.println(alex);
        saveObject(alex);

        alex.setHitPoints(8);
        System.out.println(alex);
        alex.setWeapon("Stormbringer");
        saveObject(alex);


        //loadObject(alex);
        System.out.println(alex);

        System.out.println("===========================================================");


        //Monster
        ISaveable werewolf = new Monster("Werewolf",20,40);
        System.out.println(werewolf);
        saveObject(werewolf);

        // calling getter - use intelliJ to cast
        // we can't call werewolf.getStrength()
        System.out.println("Strength "+((Monster) werewolf).getStrength());
    }

    public static void saveObject(ISaveable objectToSave){
        List<String> myList = objectToSave.write();
        for (int i = 0; i < myList.size() ; i++) {
            System.out.println("Saving "+myList.get(i) +" to storage device ");
        }
    }

    public static void loadObject(ISaveable objectToLoad){
        ArrayList<String> values = readValues();
        objectToLoad.read(values);
    }

    public static ArrayList<String> readValues() {
        ArrayList<String> values = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");

        while (!quit) {
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.print("Enter a string: ");
                    String stringInput = scanner.nextLine();
                    values.add(index, stringInput);
                    index++;
                    break;
            }
        }
        return values;
    }
}
