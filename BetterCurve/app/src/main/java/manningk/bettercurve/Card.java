package manningk.bettercurve;

/**
 * Created by Kevin on 1/16/2016.
 */
public class Card
{
    private String m_strGame;
    private String m_strSet;
    private int m_intSetNumber;
    private String m_strName;
    private int m_intCost;
    private String m_strAbility;
    private String m_strFlavor;
    private int[] m_intStats;

    public Card(String m_strGame, String m_strSet, int m_intSetNumberSetNumber, String m_strName,
                int m_intCost, String m_strAbility, String m_strFlavor, int[] m_intStats)
    {
        this.m_strGame = m_strGame;
        this.m_strSet = m_strSet;
        this.m_intSetNumber = m_intSetNumberSetNumber;
        this.m_strName = m_strName;
        this.m_intCost = m_intCost;
        this.m_strAbility = m_strAbility;
        this.m_strFlavor = m_strFlavor;
        this.m_intStats = m_intStats;
    }

    public String getM_strGame() {
        return m_strGame;
    }

    public void setM_strGame(String m_strGame) {
        this.m_strGame = m_strGame;
    }

    public String getM_strSet() {
        return m_strSet;
    }

    public void setM_strSet(String m_strSet) {
        this.m_strSet = m_strSet;
    }

    public int getM_intSetNumber() {
        return m_intSetNumber;
    }

    public void setM_intSetNumber(short m_intSetNumber) {
        this.m_intSetNumber = m_intSetNumber;
    }

    public String getM_strName() {
        return m_strName;
    }

    public void setM_strName(String m_strName) {
        this.m_strName = m_strName;
    }

    public int getM_intCost() {
        return m_intCost;
    }

    public void setM_intCost(short m_intCost) {
        this.m_intCost = m_intCost;
    }

    public String getM_strAbility() {
        return m_strAbility;
    }

    public void setM_strAbility(String m_strAbility) {
        this.m_strAbility = m_strAbility;
    }

    public String getM_strFlavor() {
        return m_strFlavor;
    }

    public void setM_strFlavor(String m_strFlavor) {
        this.m_strFlavor = m_strFlavor;
    }

    public int[] getM_shtStats() {
        return m_intStats;
    }

    public void setM_shtStats(short[] m_shtStats) {
        this.m_intStats = m_intStats;
    }

    public static Card getTestCard()
    {
        int intStats[] = {7,7};
        Card testCard = new Card("Make Believe", "Fh", 001, "Goobity Gook", 4, "Be Awesome", "Just BE.", intStats);
        return testCard;
    }
}

