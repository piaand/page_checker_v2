/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

/**
 *
 * @author piaandersin
 */
import java.util.*;
import java.util.logging.Logger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Data @NoArgsConstructor @AllArgsConstructor
public class Page {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    private String url;
    private boolean checked = false;
    private ArrayList<Rule> rules = new ArrayList<Rule>();
    
}
