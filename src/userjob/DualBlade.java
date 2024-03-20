package userjob;

import skills.DualBladeAttackSkill;

public class DualBlade extends Hero implements DualBladeAttackSkill {

    public DualBlade() {

        setMaxHp(150);            // 최대 체력
        setMaxMp(150);            // 최대 마력
        setHp(150);               // 체력
        setMp(150);               // 마력
        setExp(getExp());         // 경험치
        setLevel(getLevel());     // 레벨
        setMoney(getMoney());     // 돈
        setJob("듀얼블레이드");       // 직업쉬
        setBasicAttackName("베기"); // 기본 공격 이름
        setBasicAttackDamage(30); // 기본 공격 데미지
    }

    // 듀얼블레이본 기커 공격
    @Override
    int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 듀얼블레이드에게 부여한 기본 데미지

        boolean isRandom = Math.random() <= 0.8; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 7.0) + 1 : 0; // 랜덤 데미지 부여

        boolean isCritical = Math.random() <= 0.8; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 30 : 10; // 크리티컬일 경우 40의 데미지, 아닐 경우 0

        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        return totalDamage;
    }

    // 듀얼블레이드 기본 패시브 스킬
    @Override
    void usePassiveSkill() {

    }

    // 듀얼블레이드 인터페이스 공격 스킬



}
