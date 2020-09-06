

import com.mycompany.classroster.controller.*;
import com.mycompany.classroster.dao.*;
import com.mycompany.classroster.dto.*;
import com.mycompany.classroster.service.ClassRosterServiceLayer;
import com.mycompany.classroster.service.ClassRosterServiceLayerImpl;
import com.mycompany.classroster.view.*;

public class App {
    public static void main(String[] args) {
    UserIO myIo = new UserIOConsoleImpl();
    ClassRosterView myView = new ClassRosterView(myIo);
    ClassRosterDao myDao = new ClassRosterDaoFileImpl();
    ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao);
    ClassRosterController controller = new ClassRosterController(myService, myView);
    controller.run();
}
}
