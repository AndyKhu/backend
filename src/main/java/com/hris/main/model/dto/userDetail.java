package com.hris.main.model.dto;

import com.hris.main.message.response.JwtResponse;
import com.hris.main.model.system.view.vwUserMenuAccess;
import lombok.Data;

import java.util.List;

@Data
public class userDetail {
    private JwtResponse jwt;
    private List<vwUserMenuAccess> accessMenu;
    private Integer maxlevel;

    public userDetail(JwtResponse jwt, List<vwUserMenuAccess> accessMenu,Integer maxlevel) {
        this.jwt = jwt;
        this.accessMenu = accessMenu;
        this.maxlevel = maxlevel;
    }
}
