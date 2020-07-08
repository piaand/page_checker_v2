package com.piaandersin.pagecheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a template for the Class of Rules which give the base for checking
 * content against its requirements
 *
 * An example of a rule could be that a given character array must appear in the
 * rule. Therefore the category of a rule could be called "exists" and body od a
 * rule would have the word to be matched.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    private String body;
    private String category;

    public void mapRules() {
        //decide which rules to check according to category.
    }
}
