package rpggame;

public class Worrier extends Hero{

    // 워리어 생성자
    public Worrier() {

        setHp(100);               // 체력
        setMp(100);               // 마력
        setExp(getExp());         // 경험치
        setLevel(getLevel());     // 레벨
        setMoney(getMoney());     // 돈
        setJob("워리어");           // 직업
        setBasicAttackDamage(25); // 기본 공격 데미지
    }

    // 일반 공격 사용
    @Override
    int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 워리어에게 부여한 기본 데미지

        boolean isRandom = Math.random() <= 0.5; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 5.0) + 1 : 0; // 랜덤 데미지 부여

        boolean isCritical = Math.random() <= 0.5; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 10 : 0; // 크리티컬일 경우 3의 데미지, 아닐 경우 0

        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        return totalDamage;
    }

    // 패시브 스킬 사용
    @Override
    void usePassiveSkill() {

    }
}
