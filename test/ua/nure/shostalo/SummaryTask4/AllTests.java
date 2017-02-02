package ua.nure.shostalo.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.shostalo.SummaryTask4.entity.EntitiesTest;
import ua.nure.shostalo.SummaryTask4.repository.RepositoriesTests;
import ua.nure.shostalo.SummaryTask4.util.SortTests;

/**
 * The junit test suite.
 * 
 * @author Mikhail Shostalo
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ EntitiesTest.class, SortTests.class, RepositoriesTests.class })
public class AllTests {

}
