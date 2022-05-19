package ru.roach.operasales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.roach.operasales.model.opera.Opera;
import ru.roach.operasales.services.OperaServices;

import java.util.List;

@Controller
public class EventController {

    private OperaServices operaServices;

    @Autowired
    public EventController(OperaServices operaServices) {
        this.operaServices = operaServices;
    }

    @RequestMapping(value = "/operas", method = RequestMethod.GET)
    public String showAll(Model model) {
        final List<Opera> operas = operaServices.getAll();
        System.out.println(operas.toString());
        model.addAttribute("operas", operas);
        return "table";
    }

}
