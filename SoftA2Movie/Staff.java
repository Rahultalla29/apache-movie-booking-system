package SoftA2Movie;

public class Staff extends Person {

    public int staffid;
    public boolean isManager;

    public Staff(String username, String name, String password, boolean isManager) {
        super(username, name, password);
        //this.staffid = staffid; - do we need this?
        this.isManager = isManager;
    }
/*
    public void addMovie() {
        //System.out.println();
    }

    public void deleteMovie() {
        //System.out.println();
    }

    public void modifyMovie() {
        //System.out.println();
    }

    public void addShowing() {
        //System.out.println();
    }

    public void addGiftCard() {
        //System.out.println();
    }

    public void hireStaff() {
        //if this.isManager
        //System.out.println();
    }

    public void fireStaff() {
        //System.out.println();
    }
*/
    public void setManager(boolean value){
        this.isManager = value;
    }
    // public void setStaffid(int id){
    //     this.staffid = id;
    // }


}
