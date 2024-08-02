package naming_conventions_packages.part10_final_keyword;

public class Password {
    private static final int key = 748576362;
    private final int encryptedPassword;

    public Password(int password) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password) {
        return key ^ password;
    }

    public final void storePassword(){
        System.out.println("Saving password as "+ this.encryptedPassword);
    }

    public boolean letMeIn(int password){
        if ( this.encryptedPassword == encryptDecrypt(password)) {
            System.out.println("Welcome");
            return true;
        }
        else {
            System.out.println("Incorrect Password");
            return true;
        }
    }
}
