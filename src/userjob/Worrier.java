package userjob;
import skills.WorrierAttackSkill;
import skills.WorrierBuffSkill;

public class Worrier extends Hero implements WorrierAttackSkill, WorrierBuffSkill {

    // 워리어 생성자
    public Worrier() {

        setHp(100);                     // 체력
        setMp(100);                     // 마력
        setExp(getExp());               // 경험치
        setLevel(getLevel());           // 레벨
        setMoney(getMoney());           // 돈
        setJob("워리어");                // 직업
        setBasicAttackName("베기");      // 기본 공격 이름
        setBasicAttackDamage(25);      // 기본 공격 데미지
    }

    // 워리어 기본 공격
    @Override
    int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 워리어에게 부여한 기본 데미지

        boolean isRandom = Math.random() <= 0.5; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 3.0) + 1 : 0; // 랜덤 데미지 부여

        boolean isCritical = Math.random() <= 0.5; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 30 : 0; // 크리티컬일 경우 30의 데미지, 아닐 경우 0

        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        return totalDamage;
    }

    // 워리어 기본 패시브 스킬
    @Override
    public void usePassiveSkill() {

        setHp(getHp() * 2); // 체력이 2배 증가
        System.out.println("'아머 마스터리' 발동! -> HP가 2배 증가 합니다. \n현재 HP : " + getHp());
    }

    // 워리어 인터페이스 공격 스킬
    @Override
    public int powerStrike() {
        int baseDamage =

    }

    // 워리어 인터페이스 버프 스킬
    @Override
    public void guardMaster() {

    }



}
