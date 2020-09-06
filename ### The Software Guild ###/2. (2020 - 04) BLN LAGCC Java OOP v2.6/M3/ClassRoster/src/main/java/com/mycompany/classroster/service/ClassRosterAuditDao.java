/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterPersistenceException;

/**
 *
 * @author Music Account
 */
public interface ClassRosterAuditDao {
    
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
