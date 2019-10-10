package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class BrutalSuccess extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("BrutalSuccess");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int BLOCK_REQ = 5;
    private static final int STRENGTH_AMT = 2;
    private static final int STRENGTH_PLUS_AMT = 1;

    //card Initialize
    public BrutalSuccess() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        tags.add(CustomTags.REQUIRES);
        magicNumber = baseMagicNumber = BLOCK_REQ;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = STRENGTH_AMT;
    }


    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if(AbstractDungeon.player.currentBlock < magicNumber) {
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.currentBlock >= magicNumber) {
            p.loseBlock(magicNumber);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, fairySecondMagicNumber)));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeFairySecondMagicNumber(STRENGTH_PLUS_AMT);
            initializeDescription();
        }
    }
}
