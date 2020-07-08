
package com.piaandersin.pagecheck;

import java.util.*;
import java.util.logging.Logger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Page represents a single read url address and a group of rules that need to
 * be validated against the page content (one-to-many relationship).
 * 
 * Checked variable tells the status in checking process (true = all rules are reviewed),
 * not whether all rules were "successfull" or "passed".
 */

@Data @NoArgsConstructor @AllArgsConstructor
public class Page {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    private String url;
    private String content;
    private boolean checked;
    private ArrayList<Rule> rules = new ArrayList<Rule>();
    
}
