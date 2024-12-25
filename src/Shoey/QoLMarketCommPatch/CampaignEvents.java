package Shoey.QoLMarketCommPatch;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.AbilityPlugin;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;

public class CampaignEvents implements CampaignEventListener {

    public static void generateMarketAdmin(MarketAPI m)
    {
        CommDirectoryAPI commDirectoryAPI = m.getCommDirectory();
        PersonAPI p = m.getFaction().createRandomPerson();
        p.setPostId("administrator");
        p.setRankId("citizen");
        p.setMarket(m);
        commDirectoryAPI.addPerson(p, 0);
        m.addPerson(p);
    }

    public static void generateMarketPortmaster(MarketAPI m)
    {
        CommDirectoryAPI commDirectoryAPI = m.getCommDirectory();
        PersonAPI p = m.getFaction().createRandomPerson();
        p.setPostId("portmaster");
        p.setRankId("spaceCommander");
        p.setMarket(m);
        commDirectoryAPI.addPerson(p, 1);
        m.addPerson(p);
    }

    public static void generateMarketSupplyOfficer(MarketAPI m)
    {
        CommDirectoryAPI commDirectoryAPI = m.getCommDirectory();
        PersonAPI p = m.getFaction().createRandomPerson();
        p.setPostId("supplyOfficer");
        p.setRankId("spaceCommander");
        p.setMarket(m);
        commDirectoryAPI.addPerson(p, 2);
        m.addPerson(p);
    }

    public static void checkMarketPersons(MarketAPI m)
    {
        boolean needAdministrator = false;
        boolean needPortmaster = false;
        boolean needSupplyOfficer = false;

        PersonAPI administrator = null;
        PersonAPI portmaster = null;
        PersonAPI supplyOfficer = null;

        for (PersonAPI p : m.getPeopleCopy())
        {
            if (p.getFaction() == Global.getSector().getPlayerFaction() || p.getFaction() != m.getFaction())
                continue;

            boolean desiredIfNull = false;
            if (p.getPostId().equals("administrator")) {
                administrator = p;
                desiredIfNull = true;
            } else if (p.getPostId().equals("portmaster")) {
                portmaster = p;
                desiredIfNull = true;
            } else if (p.getPostId().equals("supplyOfficer")) {
                supplyOfficer = p;
                desiredIfNull = true;
            }
            if (desiredIfNull) {
                if (m.getCommDirectory().getEntryForPerson(p) == null)
                {
                    MainPlugin.log.info(m.getName()+ " has "+p.getPostId()+" but needed comm entry.");
                    m.getCommDirectory().addPerson(p);
                }
            }
        }

        if (administrator == null) {
            MainPlugin.log.info(m.getName() + " needs admin");
            generateMarketAdmin(m);
        }
        if (portmaster == null) {
            MainPlugin.log.info(m.getName() + " needs portmaster");
            generateMarketPortmaster(m);
        }
        if (supplyOfficer == null) {
            MainPlugin.log.info(m.getName() + " needs supplyOfficer");
            generateMarketSupplyOfficer(m);
        }


    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {
        if (market.isPlayerOwned() && market.getFaction() != Global.getSector().getPlayerFaction())
        {
            checkMarketPersons(market);
        }
    }

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {

    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {

    }

    @Override
    public void reportEncounterLootGenerated(FleetEncounterContextPlugin plugin, CargoAPI loot) {

    }

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {

    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    @Override
    public void reportBattleFinished(CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    @Override
    public void reportPlayerEngagement(EngagementResultAPI result) {

    }

    @Override
    public void reportFleetDespawned(CampaignFleetAPI fleet, FleetDespawnReason reason, Object param) {

    }

    @Override
    public void reportFleetSpawned(CampaignFleetAPI fleet) {

    }

    @Override
    public void reportFleetReachedEntity(CampaignFleetAPI fleet, SectorEntityToken entity) {

    }

    @Override
    public void reportFleetJumped(CampaignFleetAPI fleet, SectorEntityToken from, JumpPointAPI.JumpDestination to) {

    }

    @Override
    public void reportShownInteractionDialog(InteractionDialogAPI dialog) {

    }

    @Override
    public void reportPlayerReputationChange(String faction, float delta) {

    }

    @Override
    public void reportPlayerReputationChange(PersonAPI person, float delta) {

    }

    @Override
    public void reportPlayerActivatedAbility(AbilityPlugin ability, Object param) {

    }

    @Override
    public void reportPlayerDeactivatedAbility(AbilityPlugin ability, Object param) {

    }

    @Override
    public void reportPlayerDumpedCargo(CargoAPI cargo) {

    }

    @Override
    public void reportPlayerDidNotTakeCargo(CargoAPI cargo) {

    }

    @Override
    public void reportEconomyTick(int iterIndex) {

    }

    @Override
    public void reportEconomyMonthEnd() {

    }
}
