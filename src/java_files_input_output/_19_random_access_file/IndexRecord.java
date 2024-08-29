package java_files_input_output._19_random_access_file;

/*
 * IndexRecord Class
 *
 * - Fields
 *      int : startByte
 *      int : length
 *
 * - Constructor
 *      IndexRecord(int startByte, int length)
 *
 * - Getters
 *      getStartByte() : int
 *      getLength() : int
 *
 * - Setters
 *      setStartByte(int startByte) : void
 *      setLength(int length) : void
 */

public class IndexRecord {
    private int startByte;
    private int length;

    public IndexRecord(int startByte, int length) {
        this.startByte = startByte;
        this.length = length;
    }

    public int getStartByte() {
        return startByte;
    }

    public void setStartByte(int startByte) {
        this.startByte = startByte;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
