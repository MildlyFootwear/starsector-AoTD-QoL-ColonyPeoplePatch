package Shoey.QoLMarketCommPatch.RuleScripts;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.util.Misc;

import java.util.List;
import java.util.Map;

public class PrintPerson extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        PersonAPI p = dialog.getInteractionTarget().getActivePerson();
        if (p != null)
        {
            dialog.getTextPanel().addPara(p.getId());
            dialog.getTextPanel().addPara(p.getVoice());
            dialog.getTextPanel().addPara(p.getRankId());
            dialog.getTextPanel().addPara(p.getPostId());
        }
        return false;
    }
}
