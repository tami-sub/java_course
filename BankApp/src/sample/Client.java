package sample;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String surname;
    private String middle_name;

    public Client() {

    }

    public Client(String name, String surname, String middle_name){
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void AccountOpening(){
    }
}
