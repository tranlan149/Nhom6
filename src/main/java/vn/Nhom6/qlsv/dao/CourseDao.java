package vn.Nhom6.qlsv.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.Nhom6.qlsv.entity.Course;
import vn.Nhom6.qlsv.entity.CourseXML;
import vn.Nhom6.qlsv.utils.FileUtils;


public class CourseDao {
    private static final String COURSE_FILE_NAME = "course.xml";
    private List<Course> listCourses;

    public CourseDao() {
        this.listCourses = readListCourses();
        if (listCourses == null) {
            listCourses = new ArrayList<Course>();
        }
    }
    
    public void writeListCourses(List<Course> courses) {
        CourseXML xml = new CourseXML();
        xml.setCourses(courses);
        FileUtils.writeXMLtoFile(COURSE_FILE_NAME, xml);
    }

    
    public List<Course> readListCourses() {
        List<Course> list = new ArrayList<Course>();
        if(!FileUtils.isExistFile(COURSE_FILE_NAME)) {
            return null;
        }
        CourseXML xml = (CourseXML) FileUtils.readXMLFile(
                COURSE_FILE_NAME, CourseXML.class);
        if (xml != null) {
            list = xml.getCourses();
        }
        return list;
    }
    
    public int getMaxId() {
        int maxId = Integer.MIN_VALUE;
        for (Course c : listCourses) {
            if (c.getId() > maxId) {
                maxId = c.getId();
            }
        }
        return maxId;
    }

    
    public void add(Course course) {
        int id = 1;
        if (listCourses != null && listCourses.size() > 0) {
            id = getMaxId() + 1;
        }
        course.setId(id);
        listCourses.add(course);
        writeListCourses(listCourses);
    }

   
    public void edit(Course course) {
        int size = listCourses.size();
        for (int i = 0; i < size; i++) {
            if (listCourses.get(i).getId() == course.getId()) {
                listCourses.get(i).setCourseName(course.getCourseName());
                listCourses.get(i).setCourseType(course.getCourseType());
                writeListCourses(listCourses);
                break;
            }
        }
    }
    
    public boolean delete(Course course) {
        boolean isFound = false;
        int size = listCourses.size();
        for (int i = 0; i < size; i++) {
            if (listCourses.get(i).getId() == course.getId()) {
                course = listCourses.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listCourses.remove(course);
            writeListCourses(listCourses);
            return true;
        }
        return false;
    }
    
    public List<Course> getListCourses() {
        return listCourses;
    }

    public void setListCourses(List<Course> listCourses) {
        this.listCourses = listCourses;
    }
}