package subject;

// 직업정보 클래스
// 직업의 정보를 담고있을 뿐 이 class 가 움직이는 것은 아님
// ex) 변호사, 판사, 선생님 등등 그저 직업정보만 가짐
// 결국 사람이 위의 직업을 가지고 그에 맡는 행위를 하듯이
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

    // 메서드
    // 직업 정보
    public String info() {
        return null;
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
