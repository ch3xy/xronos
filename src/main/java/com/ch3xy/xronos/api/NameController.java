package com.ch3xy.xronos.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/names")
public class NameController {

    @RequestMapping(method = RequestMethod.GET)
    public List<String> names() {
        List<String> names = new ArrayList<String>();
        names.add("stefan");
        names.add("igor");
        return names;
    }
}
