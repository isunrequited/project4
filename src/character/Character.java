package character;

import subject.Subject;

public class Character {

    // 필드 선언부
    private final Subject sub; // 직업 정보
    protected int currentHp; // 현재 체력
    protected int manaCount; // 마나 게이지
    private String name; // 플레이어 또는 적 확인용
    private boolean dieFlag; // 생존 확인
    private boolean defenseMode; // 방어 상태

    // 생성자 선언부
    public Character(Subject sub) {
        this.sub = sub;
        this.currentHp = sub.getHp();
    }

    // 메서드 선언부
    // 플레이어 설정
    protected void setPlayer() {
        name = "플레이어";
    }

    // 적 설정
    protected void setEnemy() {
        name = "적";
    }

    // 현재 정보 표시
    public String info() {
        return String.format("%-4s | 직업 : %s | 남은 HP : %d | 공격력 : %d | 스킬 공격력 : %d | 마나 게이지 : %s", name, sub.getName(), currentHp, sub.getDmg(), sub.getSkillDmg(), manaCount);
    }

    // 공격
    public String attack(Character target) {
        if(target.isDefenseMode()) {
            return String.format("%s(이)가 %s의 공격을 방어했습니다.", target.name, this.name);
        } else {
            target.hit(sub.getDmg());
            return String.format("%s(이)가 %s을 공격하여 %d의 데미지를 주었습니다.", this.name, target.name, sub.getDmg());
        }
    }

    // 스킬 사용
    // 데미지 주고 마나 게이지 0, 스킬 사용 가능 상태 불가로 변경
    public String skill(Character target) {
        target.skillHit(sub.getSkillDmg());
        manaCount -= sub.getFullMana();
        return String.format("%s(이)가 %s에게 스킬을 사용하여 %d의 데미지를 주었습니다.", this.name, target.name, sub.getSkillDmg());
    }

    // 마나 회복
    public String addMana() {
        manaCount++;
        return String.format("%s(이)가 마나를 회복하였습니다. %s회 충천", name, manaCount);
    }

    // 스킬 사용 가능 확인
    // 스킬 사용 가능한 상태까지의 남은 마나 반환
    // ex) 스킬 사용 가능 시 0반환
    //     1회 남았을 시 1 반환
    public int checkPossibleUseSkill() {
        int lackMana = sub.getFullMana() - manaCount;
        if (lackMana <= 0) { // 0보다 작아질 시 0반환
            lackMana = 0;
        }
        return lackMana;
    }

    // 방어
    public String defense() {
        defenseMode = true;
        return String.format("%s(이)가 방어 태세를 취합니다.", this.name);
    }

    // 빙어 태세 해제
    public void resetDefenseMode() {
        defenseMode = false;
    }

    // 공격 받음
    public void hit(int dmg) {
        currentHp -= dmg;
        isDie();
    }

    // 스킬공격을 받음
    public void skillHit(int dmg) {
        currentHp -= dmg;
        isDie();
    }

    // 플레이어의 사망 확인
    private void isDie() {
        if (currentHp <= 0) {
            dieFlag = true;
        } else {
            dieFlag = false;
        }
    }

    // getter, setter
    public boolean isDieFlag() {
        return dieFlag;
    }

    public String getName() {
        return name;
    }

    public boolean isDefenseMode() {
        return defenseMode;
    }
}
