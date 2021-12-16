package com.mutuelle.application.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OfficerDAOTesting.class, ClientDAOTesting.class, ClientImplTesting.class })
public class AllTests {

}
