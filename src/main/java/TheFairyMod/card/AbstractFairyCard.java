package TheFairyMod.card;

import TheFairyMod.power.APRPower;
import TheFairyMod.util.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractFairyCard extends CustomCard {
    public int fairySecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int fairyBaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedFairySecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isFairySecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)

    public AbstractFairyCard(final String id,
                               final String name,
                               final String img,
                               final int cost,
                               final String rawDescription,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isFairySecondMagicNumberModified = false;
    }


    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedFairySecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            fairySecondMagicNumber = fairyBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isFairySecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeFairySecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        fairyBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        fairySecondMagicNumber = fairyBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedFairySecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }


    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }
}
