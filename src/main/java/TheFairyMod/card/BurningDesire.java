package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.power.BurningDesirePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class BurningDesire extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("BurningDesire");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Power.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int BLOCK_AMT = 1;
    private static final int BLOCK_PLUS_AMT = 1;

    //card Initialize
    public BurningDesire() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLOCK_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BurningDesirePower(p, magicNumber)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(BLOCK_PLUS_AMT);
            initializeDescription();
        }
    }
}
