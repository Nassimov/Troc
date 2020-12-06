package troc.project.troc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* ___GENERATETESTDATA___ */
@Component // This makes spring consider this class at application startup
public class AutoPopulate {

    @Autowired
    AutoPopulate(AddTestData adder) {
        adder.generateTestData();
    }
}
