package net.devwurm.seatlots.gui.drawing;


import net.devwurm.seatlots.SeatLots;

import java.util.prefs.Preferences;

/**
 * Class for removing backups
 */
public class BackupRemover {
    public static void removeBackup(String name) {
        Preferences store = Preferences.userNodeForPackage(SeatLots.class);
        store.remove("backup_" + name);
    }
}
