package org.wahlzeit.tools;

import org.wahlzeit.main.ScriptMain;
import org.wahlzeit.model.GlobalsManager;
import org.wahlzeit.services.SysConfig;

import java.io.File;

/**
 * Sets up a fresh clean Wahlzeit Beer application database.
 */
public class SetUpBeers extends ScriptMain {

    /**
     *
     */
    public static void main(String[] argv) {
        new SetUpBeers().run();
    }

    /**
     *
     */
    public void startUp(String rootDir) throws Exception {
        super.startUp(rootDir);
        GlobalsManager.getInstance().loadGlobals();
    }

    /**
     *
     */
    public void execute() throws Exception {
        String photoDir = SysConfig.getRootDirAsString() + File.separator + "config" + File.separator + "flowers";
        createUser("1337", "testuser", "info@wahlzeit.org", photoDir);
    }

}