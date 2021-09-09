package charactor;

import subject.Subject;

public class Charactor {

    // 필드 선언부
    private Subject sub; // 직업
    private int currentHp; // 현재 체력
    private int attackDmg; // 공격력
    private int skillDmg; // 스킬 데미지
    private int skillCount; // 스킬 발동 조건
    private String name; // 플레이어 또는 적
    private boolean dieFlag; // 생존 확인

    // 생성자 선언부
    public Charactor(Subject sub) {
        this.sub = sub;
        this.currentHp = sub.getHp();
        this.attackDmg =sub.getDmg();
        this.skillDmg = sub.getSkillDmg();
    }

    // 메서드 선언부
    // 플레이어 설정
    protected void setPlayer() {
        name = "플레이어";
    }

    protected void setEnemy() {
        name = "적";
    }

    // 현재 체력 표시
    public String info() {
        return String.format("%s의 상태 | 남은 HP : %d | 공격력 : %d | 스킬 공격력 : %d | 스킬 게이지 : %s", name, currentHp, attackDmg, skillDmg, skillCount);
    }

    // 공격
    public void attack(Charactor target) {
        target.hit(attackDmg);
    }

    // 방어
    public void defense() {

    }

    // 데미지 받음
    public void hit(int dmg) {
        currentHp -= dmg;
        isDie();
    }

    // 스킬 사용
    public void skill(Enemy enemy) {
        enemy.hit(skillDmg);
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
    public boolean getDieFlag() {
        return dieFlag;
    }
}