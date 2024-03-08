package userjob;

import skills.BerserkerAttackSkill;
import skills.BerserkerBuffSkill;

public class Berserker extends Hero implements BerserkerAttackSkill, BerserkerBuffSkill {

    public Berserker() {

        setMaxHp(200);            // 최대 체력
        setMaxMp(100);            // 최대 마력
        setHp(200);               // 체력
        setMp(100);               // 마력
        setExp(getExp());         // 경험치
        setLevel(getLevel());     // 레벨
        setMoney(getMoney());     // 돈
        setJob("버서커");           // 직업
        setBasicAttackName("크게 베기"); // 기본 공격 이름
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

        System.out.println(getBasicAttackName() + "!" + totalDamage + " 만큼의 피해를 가했습니다.");

        return totalDamage;
    }

    // 버서커 기본 패시브 스킬
    @Override
    void usePassiveSkill() {

        int damage = (int) (getBasicAttackDamage() * 1.5);

        int maxHp = (int) (getMaxHp()*1.5); // 최대 체력이 1.5배 증가
        int hp = (int) (getHp()*1.5); // 현재 체력이 1.5배 증가
        System.out.println("'광폭화' 발동! -> HP와 공격력이 1.5배 증가 합니다. " +
                "\n최대 HP : " + maxHp +
                "\n현재 HP : " + hp +
                "\n현재 공격력 : " + damage);
    }

    // 버서커 인터페이스 공격 스킬
    @Override
    public int bloodStrike() {

        int mpCost = 20;
        int baseDamage = getBasicAttackDamage(); // 버서커에게 부여한 기본 데미지
        boolean isRandom = Math.random() <= 0.5; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 2) + 1 : 0; // 랜덤 데미지 부여
        boolean isCritical = Math.random() <= 0.5; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 50 : 10; // 크리티컬일 경우 50의 데미지, 아닐 경우 10
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        if (getMp() >= mpCost && getHp() >= 15) {
            setHp(getHp() - 15); // hp 감소
            setMp(getMp() - mpCost); // mp 감소

            System.out.println("HP가 15, MP가 20 만큼 감소했습니다." +
                    "\n현재 HP: " + getHp() + ", 현재 MP: " + getMp() +
                    "\n피의 일격! " + totalDamage + " 만큼의 피해를 가했습니다.");

            return totalDamage;
        }
        else {
            System.out.println("HP 또는 MP가 부족하여 기본 공격을 사용합니다. " +
                    "\n현재 HP: " + getHp() + ", 현재 MP: " + getMp() +
                    "\n" + getBasicAttackName() + "! " + getBasicAttackDamage() + " 만큼의 피해를 가했습니다.");

            return useBasicAttack();
        }

    }

    // 버서커 인터페이스 버프 스킬
    @Override
    public int bloodLust() {
        return 0;
    }



}
