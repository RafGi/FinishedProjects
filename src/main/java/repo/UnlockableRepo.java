package repo;

import data.Player;
import data.Unlockable;
import java.util.List;

public interface UnlockableRepo {

    List<Unlockable> getUnlockables();

    List<Unlockable> getUnlockableByType(int type);

    Unlockable getUnlockableById(int id);

    List<Unlockable> getUnlockableByPlayer(Player player);

    void addUnlockable(Unlockable unlockable);

    void deleteUnlockable(Unlockable unlockable);

}

