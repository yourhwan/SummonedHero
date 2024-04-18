package userjob;

import dungeon.Monster;
import skills.DualBladeAttackSkill;
import skills.DualBladeBuffSkill;

public class DualBlade extends Hero implements DualBladeAttackSkill, DualBladeBuffSkill {

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
    public int useBasicAttack() {

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
    public void usePassiveSkill() {

        int damage = (int) (getBasicAttackDamage() * 1.5); // 데미지 1.5배 증가
        int maxHp = (int) (getMaxHp() * 1.5); // 최대 체력이 1.5배 증가
        int hp = (int) (getHp() * 1.5); // 현재 체력이 1.5배 증가

        System.out.println("'어둠의 발자국' 발동! -> 최대 HP와 공격력이 1.5배 증가 합니다. " +
                "\n최대 HP : " + maxHp +
                "\n현재 HP : " + hp +
                "\n현재 공격력 : " + damage);
    }



    // 듀얼블레이드 인터페이스 공격 스킬
    @Override
    public int savageBlow() {

        int mpCost = 10;
        int baseDamage = getBasicAttackDamage(); // 듀얼블레이드에게 부여한 기본 데미지
        boolean isRandom = Math.random() <= 0.7; // 랜덤 데미지 추가 확률
        int randomDamage = isRandom ? (int) (Math.random() * 3) + 3 : 1; // 랜덤 데미지 부여
        boolean isCritical = Math.random() <= 0.7; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 70 : 20; // 크리티컬일 경우 70의 데미지, 아닐 경우 20
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        if (getMp() >= mpCost) {
            setMp(getMp() - mpCost); // mp 감소

            System.out.println("세비지 블로우의 사용으로 HP가 10 만큼 감소했습니다." +
                    "\n현재 MP: " + getMp() +
                    "\n세비지 블로우! " + totalDamage + " 만큼의 피해를 가했습니다.");

            return totalDamage;
        }
        else {
            System.out.println("MP가 부족하여 기본 공격을 사용합니다. " +
                    "\n현재 MP: " + getMp() +
                    "\n" + getBasicAttackName() + "! " + getBasicAttackDamage() + " 만큼의 피해를 가했습니다.");

            return useBasicAttack();
        }
    }

    // 듀얼블레이드 인터페이스 버프 스킬
    @Override
    public void indurance() { // mp를 30만큼 소모하고 hp를 완전히 회복

        int mpCost = 30;

        if (getMp() >= mpCost) {
            setHp(getMaxHp()); // HP를 완전히 회복
            setMp(getMp() - mpCost); // mp를 30만큼 차감
            System.out.println("'인듀어런스' 발동! -> MP를 30만큼 소모하고, HP를 완전히 회복합니다." +
                    "\n인듀어런스! 현재 HP: " + getHp() + " 현재 MP: " + getMp());
        }
        else {
            System.out.println("MP가 충분하지 않습니다. 현재 MP: " + getMp());
        }

    }



}
