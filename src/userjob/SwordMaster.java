package userjob;

import skills.SwordMasterAttackSkill;

public class SwordMaster extends Hero implements SwordMasterAttackSkill {

    public SwordMaster() {

        setHp(100);               // 체력
        setMp(200);               // 마력
        setExp(getExp());         // 경험치
        setLevel(getLevel());     // 레벨
        setMoney(getMoney());     // 돈
        setJob("소드마스터");        // 직업
        setBasicAttackName("베기"); // 기본 공격 이름
        setBasicAttackDamage(30); // 기본 공격 데미지

    }

    // 듀얼블레이드 기본 공격
    @Override
    int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 소드마스터에게 부여한 기본 데미지

        boolean isRandom = Math.random() <= 0.3; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 8.0) + 1 : 0; // 랜덤 데미지 부여

        boolean isCritical = Math.random() <= 0.3; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 50 : 0; // 크리티컬일 경우 50의 데미지, 아닐 경우 0

        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        return totalDamage;
    }

    // 소드마스터 기본 패시브 스킬
    @Override
    void usePassiveSkill() {

    }

    // 스킬 인터페이스 구현

}
