package subject;

public class Subject {

    // 필드 선언부
    private int hp;
    private int dmg;
    private int skillDmg;

    // 생성자

    public Subject() {
    }

    public Subject(int hp, int dmg, int skillDmg) {
        this.hp = hp;
        this.dmg = dmg;
        this.skillDmg = skillDmg;
    }

    // get, set
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
}
