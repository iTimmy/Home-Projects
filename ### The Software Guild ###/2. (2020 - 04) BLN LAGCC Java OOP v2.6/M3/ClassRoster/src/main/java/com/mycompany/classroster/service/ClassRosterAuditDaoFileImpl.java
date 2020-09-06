/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import java.io.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Music Account
 */
public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao {
 
    public static final String AUDIT_FILE = "audit.txt";
   
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter out;
        try {
            new FileWriter(AUDIT_FILE, true);
        } catch (IOException ex) {
            Logger.getLogger(ClassRosterAuditDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
 
}