package com.my.controller;

import com.my.beans.PostUser;
import com.my.beans.Relations;
import com.my.service.RelationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/22.
 */
@Controller
@RequestMapping("/relation")
public class RelationContriller {
    @Resource
    RelationService relationService;


    @PostMapping("/cancle")
    public String cancleFriends(  int firend_id, HttpSession session){
        PostUser postUser = (PostUser) session.getAttribute("user");
        Relations relations = relationService.getRelations(postUser.getUserid(), firend_id);
        if( relations!=null){
            relations.setState((long) 1);
            relationService.del(relations);

        }
        return "redirect:/user/"+firend_id+"/findUser";

    }
    @PostMapping
    public String addFriends(  int firend_id, HttpSession session){
        PostUser postUser = (PostUser) session.getAttribute("user");
        Relations relations = relationService.getRelations(postUser.getUserid(), firend_id);
        if(relations == null) {
            boolean b = relationService.addFriends(postUser.getUserid(), firend_id);
            if (b) {
                session.setAttribute("fmsg", "success");
            } else {
                session.setAttribute("fmsg", "flag");
            }
        }else if( relations!=null){
            relations.setState((long) 0);
            relationService.del(relations);

        }
        return "redirect:/user/"+firend_id+"/findUser";

    }

}
