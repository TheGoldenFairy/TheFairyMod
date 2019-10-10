package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static TheFairyMod.TheFairyMod.makeCardPath;

public class RestAssured extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("RestAssured");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;



    //card Stats
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int NEW_COST_REDUCE_AMT = 1;
    private int DRAW_PILE_SIZE = 0;
    private boolean CARDS_CHANGED = true;

    //card Initialize
    public RestAssured() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!AbstractDungeon.player.drawPile.isEmpty()) {
            DRAW_PILE_SIZE = AbstractDungeon.player.drawPile.size();
            AbstractCard card = AbstractDungeon.player.drawPile.getRandomCard(true);
            while (card.cost == 0) {
                if(DRAW_PILE_SIZE <= 0) {
                    CARDS_CHANGED = false;
                    return;
                }
                card = AbstractDungeon.player.drawPile.getRandomCard(true);
                DRAW_PILE_SIZE--;
            }
            if(CARDS_CHANGED) {
                AbstractDungeon.actionManager.addToBottom(new ReduceCostAction(card.uuid, NEW_COST_REDUCE_AMT));
            }
            DRAW_PILE_SIZE = 0;
            CARDS_CHANGED = true;
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
