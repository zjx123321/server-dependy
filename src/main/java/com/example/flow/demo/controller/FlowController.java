package com.example.flow.demo.controller;

import com.example.flow.demo.service.LogCollectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
@Controller
public class FlowController {

//    @RequestMapping("/{path}")
    public String index(Model model, @PathVariable("path") String path) {

        model.addAttribute("value", "st=>start: Start|past\n" +
                "e=>end: End|future\n" +
                "op1=>operation: Service 1|past\n" +
                "op2=>operation: Service 2|current\n" +
                "sub1=>subroutine: My Subroutine|invalid\n" +
                "cond=>condition: Yes\n" +
                "or No?|approved:>http://www.google.com\n" +
                "c2=>condition: Good idea|rejected\n" +
                "io=>inputoutput: catch something...|future\n" +
                "st->op1(right)->cond\n" +
                "cond(yes, right)->c2\n" +
                "cond(no)->sub1(left)->op1\n" +
                "c2(yes)->io->e\n" +
                "c2(no)->op2->e");
        return "/" + path;
    }

    @Resource
    private LogCollectService logCollectService;

    @RequestMapping("/")
    @ResponseBody
    public void execute() {
        logCollectService.collect("d6c09b9b-8538-4643-a9f5-4860a6a533a5");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/zjx")
    @ResponseBody
    public String zjx() {
        return "zjx";
    }

}
