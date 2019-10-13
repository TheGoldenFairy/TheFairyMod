package TheFairyMod;


import TheFairyMod.card.*;
import TheFairyMod.character.TheGunner;
import TheFairyMod.relic.SwitchBlade;
import TheFairyMod.util.TextureLoader;
import TheFairyMod.variable.TheFairySecondMagicNumber;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class TheFairyMod implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditCharactersSubscriber,
        EditKeywordsSubscriber,
        EditRelicsSubscriber,
        PostInitializeSubscriber {

    //~~~~~~~~~~~~~~~~~~Variables~~~~~~~~~~~~~~~~~~\\
    private static final Logger logger = LogManager.getLogger(TheFairyMod.class.getName());
    private static String modID;

    //~~~~~~~~~~~~~~~~~~Description of the Mod~~~~~~~~~~~~~~~~~~\\
    private static final String MOD_NAME = "The Fairy Mod";
    private static final String AUTHOR = "TheGoldenFairy";
    private static final String DESCRIPTION = "A Mod!";


    //~~~~~~~~~~~~~~~~~~Character Color~~~~~~~~~~~~~~~~~~\\
    public static final Color GUNNER_BROWN = CardHelper.getColor(139,69,19);


    //~~~~~~~~~~~~~~~~~~Images~~~~~~~~~~~~~~~~~~\\
    // card backgrounds - The actual rectangular card.
    private static final String ATTACK_GUNNER_BROWN = "TheFairyModResources/images/512/bg_attack_gunner_brown.png";
    private static final String SKILL_GUNNER_BROWN = "TheFairyModResources/images/512/bg_skill_gunner_brown.png";
    private static final String POWER_GUNNER_BROWN = "TheFairyModResources/images/512/bg_power_gunner_brown.png";

    private static final String ENERGY_ORB_GUNNER_BROWN = "TheFairyModResources/images/512/card_gunner_brown_orb.png";
    private static final String CARD_ENERGY_ORB = "TheFairyModResources/images/512/card_small_orb.png";

    private static final String ATTACK_GUNNER_BROWN_PORTRAIT = "TheFairyModResources/images/1024/bg_attack_gunner_brown.png";
    private static final String SKILL_GUNNER_BROWN_PORTRAIT = "TheFairyModResources/images/1024/bg_skill_gunner_brown.png";
    private static final String POWER_GUNNER_BROWN_PORTRAIT = "TheFairyModResources/images/1024/bg_power_gunner_brown.png";
    private static final String ENERGY_ORB_GUNNER_GREEN_PORTRAIT = "TheFairyModResources/images/1024/card_gunner_brown_orb.png";

    // Character assets
    private static final String THE_GUNNER_BUTTON = "TheFairyModResources/images/charSelect/GunnerCharacterButton.png";
    private static final String THE_GUNNER_PORTRAIT = "TheFairyModResources/images/charSelect/GunnerCharacterPortraitBG.png";
    public static final String THE_GUNNER_SHOULDER_1 = "TheFairyModResources/images/char/TheGunner/shoulder.png";
    public static final String THE_GUNNER_SHOULDER_2 = "TheFairyModResources/images/char/TheGunner/shoulder2.png";
    public static final String THE_GUNNER_CORPSE = "TheFairyModResources/images/char/TheGunner/corpse.png";

    // Atlas and JSON files for the Animations
    public static final String THE_GUNNER_SKELETON_ATLAS = "TheFairyModResources/images/char/TheGunner/skeleton.atlas";
    public static final String THE_GUNNER_SKELETON_JSON = "TheFairyModResources/images/char/TheGunner/skeleton.json";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    private static final String BADGE_IMAGE = "TheFairyModResources/images/Badge.png";


    //~~~~~~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~~~~~\\
    public TheFairyMod() {

        //Subscribing to the mod
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("TheFairyMod");
        logger.info("Done subscribing");

        //Adding in the Color
        logger.info("Creating the color " + TheGunner.Enums.COLOR_BROWN.toString());
        BaseMod.addColor(TheGunner.Enums.COLOR_BROWN, GUNNER_BROWN, GUNNER_BROWN, GUNNER_BROWN,
                GUNNER_BROWN, GUNNER_BROWN, GUNNER_BROWN, GUNNER_BROWN,
                ATTACK_GUNNER_BROWN, SKILL_GUNNER_BROWN, POWER_GUNNER_BROWN, ENERGY_ORB_GUNNER_BROWN,
                ATTACK_GUNNER_BROWN_PORTRAIT, SKILL_GUNNER_BROWN_PORTRAIT, POWER_GUNNER_BROWN_PORTRAIT,
                ENERGY_ORB_GUNNER_GREEN_PORTRAIT, CARD_ENERGY_ORB);
        logger.info("Done creating the color");

    }

    //~~~~~~~~~~~~~~~~~~Set and Get Mod ID~~~~~~~~~~~~~~~~~~\\
    private static void setModID(String ID) {
        modID = ID;
    }

    private static String getModID() {
        return modID;
    }


    //~~~~~~~~~~~~~~~~~~Make Paths~~~~~~~~~~~~~~~~~~\\
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    //~~~~~~~~~~~~~~~~~~Initialize~~~~~~~~~~~~~~~~~~\\
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing The Fairy Mod. =========================");
        TheFairyMod thefairymod = new TheFairyMod();
        logger.info("========================= The Fairy Mod Initialized. =========================");
    }


    //~~~~~~~~~~~~~~~~~~Receive Paths~~~~~~~~~~~~~~~~~~\\
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheGunner.Enums.THE_GUNNER.toString());

        BaseMod.addCharacter(new TheGunner("The Gunner", TheGunner.Enums.THE_GUNNER),
                THE_GUNNER_BUTTON, THE_GUNNER_PORTRAIT, TheGunner.Enums.THE_GUNNER);
    }

    @Override
    public void receiveEditCards() {

        //Adding Variables
        logger.info("Adding variables");
        BaseMod.addDynamicVariable(new TheFairySecondMagicNumber());
        logger.info("Done Adding variables");


        //~~~~~~~~~~~~~~~~~ADDING CARDS~~~~~~~~~~~~~~~~~\\
        logger.info("Adding cards");

        //TEST CARD
        //BaseMod.addCard(new AATestCard());
        //UnlockTracker.unlockCard(AATestCard.ID);

        //STARTER CARDS 4
        BaseMod.addCard(new FairyStrike());         //1
        BaseMod.addCard(new FairyDefend());         //2
        BaseMod.addCard(new Ammo());                //3
        BaseMod.addCard(new SplitShot());          //4


        //COMMON CARDS 21
        BaseMod.addCard(new QuickShot());           //1
        BaseMod.addCard(new BoneShattering());      //2
        BaseMod.addCard(new BreakingThrough());     //3
        BaseMod.addCard(new LastingRecoil());       //4
        BaseMod.addCard(new MoralLesson());         //5
        BaseMod.addCard(new OpenFire());            //6
        BaseMod.addCard(new TwentyTwoPistal());     //7
        BaseMod.addCard(new Grenade());             //8
        BaseMod.addCard(new Silencer());            //9
        BaseMod.addCard(new GoForTheHands());       //10
        BaseMod.addCard(new Bunker());              //11
        BaseMod.addCard(new Reassess());            //12
        BaseMod.addCard(new HideInCover());         //13
        BaseMod.addCard(new OptimalDecision());     //14
        BaseMod.addCard(new HiddenStash());         //15
        BaseMod.addCard(new HardenHelmet());        //16
        BaseMod.addCard(new SpeedyReload());        //17
        BaseMod.addCard(new QuickThinking());       //18
        BaseMod.addCard(new BarbedWire());          //19
        BaseMod.addCard(new HastyRetreat());        //20
        BaseMod.addCard(new DoD());                 //21


        //UNCOMMON CARDS
        BaseMod.addCard(new PistolShot());          //1
        BaseMod.addCard(new FastArtillery());       //2
        BaseMod.addCard(new DownButNotOut());       //3
        BaseMod.addCard(new GetBackUp());           //4
        BaseMod.addCard(new Jab());                 //5
        BaseMod.addCard(new PiercingShot());        //6
        BaseMod.addCard(new FlashGrenade());        //7
        BaseMod.addCard(new MagnaPistal());         //8
        BaseMod.addCard(new KA74());                //9
        BaseMod.addCard(new PewPewMachine());       //10
        BaseMod.addCard(new ScarredL());            //11
        BaseMod.addCard(new FALT());                //12
        BaseMod.addCard(new RGP());                 //13
        BaseMod.addCard(new Bombardment());         //14
        BaseMod.addCard(new ShieldOfProtection());  //15
        BaseMod.addCard(new SelfSacrifice());       //16
        BaseMod.addCard(new SneakyPlan());          //17
        BaseMod.addCard(new CombatArmor());          //18
        BaseMod.addCard(new TrueAdvantage());       //19
        BaseMod.addCard(new LightWeightArmor());    //20
        BaseMod.addCard(new ShieldingTactics());    //21
        BaseMod.addCard(new StraightPlan());        //22
        BaseMod.addCard(new Ambush());              //23
        BaseMod.addCard(new TeamWork());            //24
        BaseMod.addCard(new LastResort());          //25
        BaseMod.addCard(new RestAssured());         //26
        BaseMod.addCard(new LivingShells());        //27
        BaseMod.addCard(new NoMoreOptions());       //28
        BaseMod.addCard(new PowerMove());           //29
        BaseMod.addCard(new MasteredOffense());     //30
        BaseMod.addCard(new MasteredDefense());     //31
        BaseMod.addCard(new ShieldingRounds());     //32
        BaseMod.addCard(new BurningDesire());       //33
        BaseMod.addCard(new Fortification());       //34


        //RARE
        BaseMod.addCard(new ShottyReload());        //1
        BaseMod.addCard(new MachineGun());          //2
        BaseMod.addCard(new MaximumEffort());       //3
        BaseMod.addCard(new SnipperRifle());        //4
        BaseMod.addCard(new PeaceOfMind());         //5
        BaseMod.addCard(new CalmingMoment());       //6
        BaseMod.addCard(new BrutalSuccess());       //7
        BaseMod.addCard(new OpenArmory());          //8
        BaseMod.addCard(new SilentOps());           //9
        BaseMod.addCard(new Juggernaut());          //10
        BaseMod.addCard(new JaggedGround());        //11
        BaseMod.addCard(new APR());                 //12
        BaseMod.addCard(new Shrapnel());          //13
        BaseMod.addCard(new EfficientWork());       //14
        BaseMod.addCard(new ExtraAmmo());           //15
        BaseMod.addCard(new BlazingForm());       //16



        //~~~~~~~~~~~~~~~~~UNLOCKING CARDS~~~~~~~~~~~~~~~~~\\
        logger.info("Making sure the cards are unlocked.");

        //START CARDS 4
        UnlockTracker.unlockCard(FairyStrike.ID);           //1
        UnlockTracker.unlockCard(FairyDefend.ID);           //2
        UnlockTracker.unlockCard(Ammo.ID);                  //3
        UnlockTracker.unlockCard(SplitShot.ID);            //4


        //COMMON CARDS 21
        UnlockTracker.unlockCard(QuickShot.ID);             //1
        UnlockTracker.unlockCard(BoneShattering.ID);        //2
        UnlockTracker.unlockCard(BreakingThrough.ID);       //3
        UnlockTracker.unlockCard(LastingRecoil.ID);         //4
        UnlockTracker.unlockCard(MoralLesson.ID);           //5
        UnlockTracker.unlockCard(OpenFire.ID);              //6
        UnlockTracker.unlockCard(TwentyTwoPistal.ID);       //7
        UnlockTracker.unlockCard(Grenade.ID);               //8
        UnlockTracker.unlockCard(Silencer.ID);              //9
        UnlockTracker.unlockCard(GoForTheHands.ID);         //10
        UnlockTracker.unlockCard(Bunker.ID);                //11
        UnlockTracker.unlockCard(Reassess.ID);              //12
        UnlockTracker.unlockCard(HideInCover.ID);           //13
        UnlockTracker.unlockCard(OptimalDecision.ID);       //14
        UnlockTracker.unlockCard(HiddenStash.ID);           //15
        UnlockTracker.unlockCard(HardenHelmet.ID);          //16
        UnlockTracker.unlockCard(SpeedyReload.ID);          //17
        UnlockTracker.unlockCard(QuickThinking.ID);         //18
        UnlockTracker.unlockCard(BarbedWire.ID);            //19
        UnlockTracker.unlockCard(HastyRetreat.ID);          //20
        UnlockTracker.unlockCard(DoD.ID);                   //21


        //UNCOMMON CARDS
        UnlockTracker.unlockCard(PistolShot.ID);            //1
        UnlockTracker.unlockCard(FastArtillery.ID);         //2
        UnlockTracker.unlockCard(DownButNotOut.ID);         //3
        UnlockTracker.unlockCard(GetBackUp.ID);             //4
        UnlockTracker.unlockCard(Jab.ID);                   //5
        UnlockTracker.unlockCard(PiercingShot.ID);          //6
        UnlockTracker.unlockCard(FlashGrenade.ID);          //7
        UnlockTracker.unlockCard(MagnaPistal.ID);           //8
        UnlockTracker.unlockCard(KA74.ID);                  //9
        UnlockTracker.unlockCard(PewPewMachine.ID);         //10
        UnlockTracker.unlockCard(ScarredL.ID);              //11
        UnlockTracker.unlockCard(FALT.ID);                  //12
        UnlockTracker.unlockCard(RGP.ID);                   //13
        UnlockTracker.unlockCard(Bombardment.ID);           //14
        UnlockTracker.unlockCard(ShieldOfProtection.ID);    //15
        UnlockTracker.unlockCard(SelfSacrifice.ID);         //16
        UnlockTracker.unlockCard(SneakyPlan.ID);            //17
        UnlockTracker.unlockCard(CombatArmor.ID);            //18
        UnlockTracker.unlockCard(TrueAdvantage.ID);         //19
        UnlockTracker.unlockCard(LightWeightArmor.ID);      //20
        UnlockTracker.unlockCard(ShieldingTactics.ID);      //21
        UnlockTracker.unlockCard(StraightPlan.ID);          //22
        UnlockTracker.unlockCard(Ambush.ID);                //23
        UnlockTracker.unlockCard(TeamWork.ID);              //24
        UnlockTracker.unlockCard(LastResort.ID);            //25
        UnlockTracker.unlockCard(RestAssured.ID);           //26
        UnlockTracker.unlockCard(LivingShells.ID);          //27
        UnlockTracker.unlockCard(NoMoreOptions.ID);         //28
        UnlockTracker.unlockCard(PowerMove.ID);             //29
        UnlockTracker.unlockCard(MasteredOffense.ID);       //30
        UnlockTracker.unlockCard(MasteredDefense.ID);       //31
        UnlockTracker.unlockCard(ShieldingRounds.ID);       //32
        UnlockTracker.unlockCard(BurningDesire.ID);         //33
        UnlockTracker.unlockCard(Fortification.ID);         //34


        //RARE
        UnlockTracker.unlockCard(ShottyReload.ID);      //1
        UnlockTracker.unlockCard(MachineGun.ID);        //2
        UnlockTracker.unlockCard(MaximumEffort.ID);     //3
        UnlockTracker.unlockCard(SnipperRifle.ID);      //4
        UnlockTracker.unlockCard(PeaceOfMind.ID);       //5
        UnlockTracker.unlockCard(CalmingMoment.ID);     //6
        UnlockTracker.unlockCard(BrutalSuccess.ID);     //7
        UnlockTracker.unlockCard(OpenArmory.ID);        //8
        UnlockTracker.unlockCard(SilentOps.ID);         //9
        UnlockTracker.unlockCard(Juggernaut.ID);        //10
        UnlockTracker.unlockCard(JaggedGround.ID);      //11
        UnlockTracker.unlockCard(APR.ID);               //12
        UnlockTracker.unlockCard(Shrapnel.ID);        //13
        UnlockTracker.unlockCard(EfficientWork.ID);     //14
        UnlockTracker.unlockCard(ExtraAmmo.ID);         //15
        UnlockTracker.unlockCard(BlazingForm.ID);     //16


        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/FairyMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }

    }

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        BaseMod.registerModBadge(badgeTexture, MOD_NAME, AUTHOR, DESCRIPTION, null);
        logger.info("Finish Loading badge image");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/FairyMod-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/FairyMod-Power-Strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/FairyMod-Character-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/FairyMod-Relic-Strings.json");

        logger.info("Done editing strings");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new SwitchBlade(), TheGunner.Enums.COLOR_BROWN);

        // Mark relics as seen (the others are all starters so they're marked as seen in the character file
        UnlockTracker.markRelicAsSeen(SwitchBlade.ID);
        logger.info("Done adding relics!");
    }

    //~~~~~~~~~~~~~~~~~~Make the ID~~~~~~~~~~~~~~~~~~\\
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}