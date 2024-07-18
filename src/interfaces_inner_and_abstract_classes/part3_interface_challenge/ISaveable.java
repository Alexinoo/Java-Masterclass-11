package interfaces_inner_and_abstract_classes.part3_interface_challenge;

import java.util.List;

public interface ISaveable {

    List<String> write();
    void read(List<String> savedValues);
}
