package vn.Nhom6.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.Nhom6.qlsv.dao.UserDao;
import vn.Nhom6.qlsv.entity.User;
import vn.Nhom6.qlsv.view.CourseView;
import vn.Nhom6.qlsv.view.LoginView;
import vn.Nhom6.qlsv.view.MainView;
import vn.Nhom6.qlsv.view.StudentView;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private StudentView studentView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     *
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
//                studentView = new StudentView();
//                StudentController studentController = new StudentController(studentView);
//                studentController.showStudentView();
//                loginView.setVisible(false);
                
                MainView view = new MainView();
                MainController controller = new MainController(view);
                controller.showMainView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
