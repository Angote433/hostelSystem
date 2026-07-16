package com.ejaisoft.service;

import com.ejaisoft.Utils.PasswordUtil;
import com.ejaisoft.dao.AdminDao;
import com.ejaisoft.model.Admin;

public class AdminService {
    AdminDao ad = new AdminDao();

    public Admin authenticate(String usernameOrEmail, String rawPassword) {
        Admin admin = ad.findByUsernameOrEmail(usernameOrEmail);
        if (admin == null) {
            return null;
        }
        return PasswordUtil.matches(rawPassword, admin.getPasswordHash()) ? admin : null;
    }
}
