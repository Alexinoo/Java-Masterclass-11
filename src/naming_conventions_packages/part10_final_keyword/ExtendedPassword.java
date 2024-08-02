package naming_conventions_packages.part10_final_keyword;

public class ExtendedPassword extends Password{
    private final int decryptedPassword;

    public ExtendedPassword(int password) {
        super(password);
        this.decryptedPassword = password;
    }

//    @Override
//    public void storePassword() {
//        //super.storePassword();
//        System.out.println("Saving password as "+this.decryptedPassword);
//    }
}
