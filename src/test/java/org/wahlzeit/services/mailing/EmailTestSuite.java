package org.wahlzeit.services.mailing;

import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.mailing.EmailServiceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        EmailAddressTest.class,
        EmailServiceTest.class
})


public class EmailTestSuite {

}
