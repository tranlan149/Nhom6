/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.dao;

import java.util.ArrayList;
import java.util.List;

import vn.Nhom6.qlsv.entity.SectionClass;
import vn.Nhom6.qlsv.entity.SectionClassXML;
import vn.Nhom6.qlsv.utils.FileUtils;


public class SectionClassDao {
    private static final String SECTION_CLASS_FILE_NAME = "sectionclass.xml";
    private List<SectionClass> listSectionClasses;

    public SectionClassDao() {
        this.listSectionClasses = readListSectionClasses();
        if (listSectionClasses == null) {
            listSectionClasses = new ArrayList<SectionClass>();
        }
    }

    public void writeListSectionClasses(List<SectionClass> sectionClasses) {
        SectionClassXML xml = new SectionClassXML();
        xml.setClasses(sectionClasses);
        FileUtils.writeXMLtoFile(SECTION_CLASS_FILE_NAME, xml);
    }

    public List<SectionClass> readListSectionClasses() {
        List<SectionClass> list = new ArrayList<SectionClass>();
        if (!FileUtils.isExistFile(SECTION_CLASS_FILE_NAME)) {
            return null;
        }
        SectionClassXML xml = (SectionClassXML) FileUtils.readXMLFile(
                SECTION_CLASS_FILE_NAME, SectionClassXML.class);
        if (xml != null) {
            list = xml.getClasses();
        }
        return list;
    }

    public int getMaxId() {
        int maxId = Integer.MIN_VALUE;
        for (SectionClass sc : listSectionClasses) {
            if (sc.getId() > maxId) {
                maxId = sc.getId();
            }
        }
        return maxId;
    }

    public void add(SectionClass sectionClass) {
        int id = 1;
        if (listSectionClasses != null && listSectionClasses.size() > 0) {
            id = getMaxId() + 1;
        }
        sectionClass.setId(id);
        listSectionClasses.add(sectionClass);
        writeListSectionClasses(listSectionClasses);
    }

    public void edit(SectionClass sectionClass) {
        int size = listSectionClasses.size();
        for (int i = 0; i < size; i++) {
            if (listSectionClasses.get(i).getId() == sectionClass.getId()) {
                listSectionClasses.get(i).setClassType(sectionClass.getClassType());
                listSectionClasses.get(i).setMaxStudents(sectionClass.getMaxStudents());
                listSectionClasses.get(i).setCourse(sectionClass.getCourse());
                listSectionClasses.get(i).setStudents(sectionClass.getStudents());
                writeListSectionClasses(listSectionClasses);
                break;
            }
        }
    }
    
    public void editNoStudent(SectionClass sectionClass) {
        int size = listSectionClasses.size();
        for (int i = 0; i < size; i++) {
            if (listSectionClasses.get(i).getId() == sectionClass.getId()) {
                listSectionClasses.get(i).setClassType(sectionClass.getClassType());
                listSectionClasses.get(i).setMaxStudents(sectionClass.getMaxStudents());
                listSectionClasses.get(i).setCourse(sectionClass.getCourse());
                writeListSectionClasses(listSectionClasses);
                break;
            }
        }
    }

    public boolean delete(SectionClass sectionClass) {
        boolean isFound = false;
        int size = listSectionClasses.size();
        for (int i = 0; i < size; i++) {
            if (listSectionClasses.get(i).getId() == sectionClass.getId()) {
                sectionClass = listSectionClasses.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listSectionClasses.remove(sectionClass);
            writeListSectionClasses(listSectionClasses);
            return true;
        }
        return false;
    }
    
    public SectionClass getDataById(int id) {
        for(SectionClass s : listSectionClasses) {
            if(s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public List<SectionClass> getListSectionClasses() {
        return listSectionClasses;
    }

    public void setListSectionClasses(List<SectionClass> listSectionClasses) {
        this.listSectionClasses = listSectionClasses;
    }
}
