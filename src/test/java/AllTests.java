
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.wahlzeit.handlers.*;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.*;
import org.wahlzeit.services.*;
import org.wahlzeit.services.mailing.*;
import org.wahlzeit.utils.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        // handlers
        TellFriendTest.class,

        // model
        AccessRightsTest.class,
        CoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,

        // model.persistence
        DatastoreAdapterTest.class,

        // services
        EmailAddressTest.class,
        LogBuilderTest.class,

        // services.mailing
        EmailServiceTest.class,

        // utils
        StringUtilTest.class,
        VersionTest.class

})
public class AllTests {
}
