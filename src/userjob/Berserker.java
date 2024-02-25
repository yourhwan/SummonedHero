package userjob;

import skills.BersekerAttackSkill;

public class Berserker extends Hero implements BersekerAttackSkill {

    public Berserker() {

        setHp(200);               // 체력
        setMp(100);               // 마력
              // 경험치
        setLevel(getLevel());     // 레벨
         // 돈
        setJob("버서커");           // 직업
        setBasicAttackName("베기"); // 기본 공격 이름
        setBasicAttackDamage(40); // 기본 공격 데미지
    }

    // 버서커 기본 공
    @Override
    int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 버서프에게 부여한 기본 데미지

        boolean isRandom = Math.random() <= 0.5; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 9.0) + 1 : 0; // 랜덤 데미지 부여

        boolean isCritical = Math.random() <= 0.6; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 50 : 0; // 크리티컬일 경우 50의 데미지, 아닐 경우 0

        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        return totalDamage;
    }

    // 버서커 기본 패시브 스킬
    @Override
    void usePassiveSkill() {

    }

    // 버서커 인터페이스 공격 스킬

    // 버서커 인터페이스 버프 스킬

}
