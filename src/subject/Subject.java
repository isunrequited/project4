package subject;

public class Subject {

    // 필드 선언부
    private int hp;
    private int dmg;
    private int skillDmg;
    private int fullMana;
    private String name;

    // 생성자
    public Subject() {
    }

    public Subject(int hp, int dmg, int skillDmg, int fullMana) {
        this.hp = hp;
        this.dmg = dmg;
        this.skillDmg = skillDmg;
        this.fullMana = fullMana;
    }

    // get, set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getSkillDmg() {
        return skillDmg;
    }

    public void setSkillDmg(int skillDmg) {
        this.skillDmg = skillDmg;
    }

    public int getFullMana() {
        return fullMana;
    }

    public void setFullMana(int fullMana) {
        this.fullMana = fullMana;
    }
}
