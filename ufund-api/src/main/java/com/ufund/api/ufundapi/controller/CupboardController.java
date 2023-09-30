package com.ufund.api.ufundapi.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufund.api.ufundapi.persistence.CupboardDAO;

@Controller
@RequestMapping("/cupboard")
public class CupboardController {
    
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private CupboardDAO cupboardDAO;

    public CupboardController(CupboardDAO cupboardDAO){
        this.cupboardDAO = cupboardDAO;
    }
}
