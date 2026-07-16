import com.ejaisoft.Utils.PasswordUtil;
import com.ejaisoft.dao.AdminDao;
import com.ejaisoft.model.Admin;

/*
One-off utility to create the first admin account.
Run once (after creating the admin table via src/main/resources/sql/admin_table.sql),
then log in through the normal app menu with the username/password used here.
*/
public class SeedAdmin {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setFullName("System Administrator");
        admin.setUserName("admin");
        admin.setEmail("admin@hostel.local");
        admin.setPhoneNumber("0000000000");
        admin.setPasswordHash(PasswordUtil.hash("ChangeMe123!"));

        new AdminDao().createAdmin(admin);
        System.out.println("Seeded admin user 'admin' with password 'ChangeMe123!' - change it once you can.");
    }
}
