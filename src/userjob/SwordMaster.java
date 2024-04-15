package userjob;

import dungeon.Monster;
import skills.*;

public class SwordMaster extends Hero implements SwordMasterAttackSkill, SwordMasterBuffSkill, WarriorAttackSkill, BerserkerAttackSkill, DualBladeAttackSkill {

    public SwordMaster() {

        setMaxHp(100);                  // 최대 체력
        setMaxMp(250);                  // 최대 마력
        setHp(100);                     // 체력
        setMp(250);                     // 마력
        setExp(getExp());               // 경험치
        setLevel(getLevel());           // 레벨
        setMoney(getMoney());           // 돈
        setJob("소드마스터");              // 직업
        setBasicAttackName("베기");      // 기본 공격 이름
        setBasicAttackDamage(30);       // 기본 공격 데미지
        setInitialMaxHp(100);           // 버프 스킬 사용 전 최대 체력
        setInitialHp(100);              // 버프 스킬 사용 전 체력
        setInitialMaxMp(250);           // 버프 스킬 사용 전 최대 마력
        setInitialMp(250);              // 버프 스킬 사용 전 마력
        setInitialDamage(30);           // 버프 스킬 사용 전 기본 공격력
    }

    // 소드마스터 기본 공격
    @Override
    public int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 소드마스터에게 부여한 기본 데미지
        boolean isRandom = Math.random() <= 0.3; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 8.0) + 1 : 0; // 랜덤 데미지 부여
        boolean isCritical = Math.random() <= 0.3; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 50 : 0; // 크리티컬일 경우 50의 데미지, 아닐 경우 0
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        System.out.println(getBasicAttackName() + "!" + totalDamage + " 만큼의 피해를 가했습니다.");

        return totalDamage;
    }

    // 소드마스터 기본 패시브 스킬
    @Override
    void usePassiveSkill() {

        int damage = getBasicAttackDamage() + 20;
        int maxHp = getInitialMaxHp() + 50;
        int hp = getHp() + 50; // HP 50 증가

        System.out.println("'검신의 의지' 발동! -> 최대 및 현재 HP 50, 현재 공격력이 20  증가 합니다. " +
                "\n최대 HP : " + maxHp +
                "\n현재 HP : " + hp +
                "\n현재 공격력 : " + damage);
    }

    // 소드마스터 인터페이스 공격 스킬
    @Override
    public int fastSlash() {

        int mpCost = 20;

        if (getMp() >= mpCost) {

            int totalDamage = getBasicAttackDamage() * 4;

            // MP 감소
            setMp(getMp() - mpCost);

            System.out.println("MP가 20 만큼 감소했습니다. 현재 MP: " + getMp() +
                    "\n벽력일섬!" + totalDamage + " 만큼의 피해를 가했습니다.");

            return totalDamage;
        }
        else {
            System.out.println("MP가 부족하여 기본 공격을 사용합니다." +
                    "\n현재 MP: " + getMp() +
                    "\n" + getBasicAttackName() + "! " + getBasicAttackDamage() + " 만큼의 피해를 가했습니다.");

            return useBasicAttack();
        }

    }

    // 소드마스터 인터페이스 버프 스킬
    @Override
    public void swordMastery() {

        int skillCost = 20;

        if (getMp() >= skillCost) {

            setBasicAttackDamage(getBasicAttackDamage() + 15); // 기본 공격력 15 증가

            System.out.println("공격력이 15 증가 했습니다." +
                    "\n현재 공격력: " + getBasicAttackDamage());
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////
    // 다른 직업의 공격 스킬

    // 워리어 스킬
    @Override
    public int powerStrike() {

        int mpCost = 25;
        int baseDamage = getBasicAttackDamage() * 2;
        boolean isRandom = Math.random() <= 0.4;
        int randomDamage = isRandom ? (int) (Math.random() * 3) + 5 : 0;
        boolean isCritical = Math.random() <= 0.3;
        int criticalDamage = isCritical ? 40 : 10;

        int totalDamage = baseDamage + randomDamage + criticalDamage;; // 소드마스터에게 부여한 기본 데미지

        System.out.println("파워스트라이크! " + totalDamage +" 만큼의 피해를 가했습니다.");

        if (getMp() >= mpCost) {

            // MP 감소
            setMp(getMp() - mpCost);

            System.out.println("MP가 25 만큼 감소했습니다. 현재 MP: " + getMp() +
                    "\n파워스트라이크! " + totalDamage + " 만큼의 피해를 가했습니다.");

            return totalDamage;
        }
        else {
            System.out.println("MP가 부족하여 기본 공격을 사용합니다." +
                    "\n현재 MP: " + getMp() +
                    "\n" + getBasicAttackName() + "! " + getBasicAttackDamage() + " 만큼의 피해를 가했습니다.");

            return useBasicAttack();
        }
    }

    // 버서커 공격 스킬
    @Override
    public int bloodStrike() {

        int hpCost = 10;
        int mpCost = 20;
        int baseDamage = getBasicAttackDamage(); // 소드마스터에게 부여한 기본 데미지
        boolean isRandom = Math.random() <= 0.35; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 2) + 1 : 0; // 랜덤 데미지 부여
        boolean isCritical = Math.random() <= 0.3; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 40 : 10; // 크리티컬일 경우 40의 데미지, 아닐 경우 10
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        if (getMp() >= mpCost && getHp() >= hpCost) {
            setHp(getHp() - hpCost); // hp 감소
            setMp(getMp() - mpCost); // mp 감소

            System.out.println("블러드 스트라이크의 사용으로 HP가 10, MP가 20 만큼 감소했습니다." +
                    "\n현재 HP: " + getHp() + ", 현재 MP: " + getMp() +
                    "\n블러드 스트라이크! " + totalDamage + " 만큼의 피해를 가했습니다.");

            return totalDamage;
        }
        else {
            System.out.println("HP 또는 MP가 부족하여 기본 공격을 사용합니다. " +
                    "\n현재 HP: " + getHp() + ", 현재 MP: " + getMp() +
                    "\n" + getBasicAttackName() + "! " + getBasicAttackDamage() + " 만큼의 피해를 가했습니다.");

            return useBasicAttack();
        }
    }

    // 듀얼블레이드 공격 스킬
    @Override
    public int savageBlow() {

        int mpCost = 15;
        int baseDamage = getBasicAttackDamage(); // 듀얼블레이드에게 부여한 기본 데미지
        boolean isRandom = Math.random() <= 0.5; // 랜덤 데미지 추가 확률
        int randomDamage = isRandom ? (int) (Math.random() * 3) + 1 : 1; // 랜덤 데미지 부여
        boolean isCritical = Math.random() <= 0.5; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 40 : 10; // 크리티컬일 경우 40의 데미지, 아닐 경우 0
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        if (getMp() >= mpCost) {
            setMp(getMp() - mpCost); // mp 감소

            System.out.println("세비지 블로우의 사용으로 HP가 15 만큼 감소했습니다." +
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



}
