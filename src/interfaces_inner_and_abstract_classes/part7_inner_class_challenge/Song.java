package interfaces_inner_and_abstract_classes.part7_inner_class_challenge;

/*
 * Song class
 *
 * Fields
 *  title : String
 *  duration : double
 *
 * Constructor
 *  initializes both title and duration
 *
 * Getter
 *  getTitle() : returns title
 *
 * toString() : String
 *  - a quick way to output contents of an actual object
 *  - is part of the Java infrastructure for all objects
 *      - all classes are inherited from the Object class
 *  - print out the actual contents of the various fields in a class
 *
 */
public class Song {
    private String title;
    private double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.title + ": " + this.duration;
    }
}
