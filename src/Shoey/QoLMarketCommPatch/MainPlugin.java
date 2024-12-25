package Shoey.QoLMarketCommPatch;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.BaseModPlugin;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class MainPlugin extends BaseModPlugin {

    public static Logger log = Global.getLogger(MainPlugin.class);

    @Override
    public void onApplicationLoad() throws Exception {
        super.onApplicationLoad();
        log.setLevel(Level.INFO);
    }

    @Override
    public void onGameLoad(boolean b) {
        super.onGameLoad(b);
        Global.getSector().addTransientListener(new CampaignEvents());
    }

    @Override
    public void beforeGameSave()
    {
        super.beforeGameSave();
    }

    @Override
    public void afterGameSave()
    {
        super.afterGameSave();
    }
}
