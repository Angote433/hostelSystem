import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.dao.BedDao;
import com.ejaisoft.dao.HostelDao;
import com.ejaisoft.dao.RoomDao;
import com.ejaisoft.dao.StudentDao;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Hostel;
import com.ejaisoft.model.Room;
import com.ejaisoft.model.Student;
import com.ejaisoft.service.HostelService;
import com.ejaisoft.service.RoomService;
import com.ejaisoft.service.StudentService;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DbMapTest {
    static void main() {
        HostelService hs = new HostelService();
        HostelDao hd = new HostelDao();
        Hostel hostel = hd.getHostelByID(2);
        RoomService rs = new RoomService();
        StudentService ss = new StudentService();





    }
}

