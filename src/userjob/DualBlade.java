package userjob;

import skills.DualBladeAttackSkill;

public class DualBlade extends Hero implements DualBladeAttackSkill {

    public DualBlade() {

        setMaxHp(150);                  // 최대 체력
        setMaxMp(150);                  // 최대 마력
        setHp(150);                     // 체력
        setMp(150);                     // 마력
        setExp(getExp());               // 경험치
        setLevel(getLevel());           // 레벨
        setMoney(getMoney());           // 돈
        setJob("듀얼블레이드");            // 직업
        setBasicAttackName("");         // 기본 공격 이름
        setBasicAttackDamage(30);       // 기본 공격 데미지
        setInitialMaxHp(150);           // 버프 스킬 사용 전 최대 체력
        setInitialHp(150);              // 버프 스킬 사용 전 체력
        setInitialMaxMp(150);           // 버프 스킬 사용 전 최대 마력
        setInitialMp(150);              // 버프 스킬 사용 전 마력
        setInitialDamage(30);           // 버프 스킬 사용 전 기본 공격력
    }

    // 듀얼블레이드 기본 공격
    @Override
    int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 듀얼블레이드에게 부여한 기본 데미지

        boolean isRandom = Math.random() <= 0.8; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 7) + 1 : 0; // 랜덤 데미지 부여

        boolean isCritical = Math.random() <= 0.8; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 50 : 10; // 크리티컬일 경우 50의 데미지, 아닐 경우 0

        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        return totalDamage;
    }

    // 듀얼블레이드 기본 패시브 스킬
    @Override
    void usePassiveSkill() {

    }



    // 듀얼블레이드 인터페이스 공격 스킬
    @Override
    public int savageBlow() {

        int mpCost = 15;
        return 0;
    }

    // 듀얼블레이드 인터페이스 버프 스킬
    @Override
    public void darkSight() {

        return;
    }



}
