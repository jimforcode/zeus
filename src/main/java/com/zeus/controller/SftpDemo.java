package com.zeus.controller;

import com.zeus.sftp.SFTPProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
@Controller
@RequestMapping("readFile")
public class SftpDemo extends BaseController {

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public ModelAndView getNetInfo(@RequestParam("destServer") String destServer, @RequestParam("path") String path, @RequestParam("fileName") String fileName) {
        ModelAndView mv = new ModelAndView();
        String text = null;
        try {
            text = SFTPProcessor.sshSftp(destServer, "root", "Zabbix123", 22, path, fileName);
        } catch (Exception e) {
            text = e.getLocalizedMessage();
        }
        mv.setViewName("file");
        mv.addObject("text", text);
        return mv;
    }
}
