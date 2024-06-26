package userjob;
import dungeon.Monster;
import skills.WarriorAttackSkill;
import skills.WarriorBuffSkill;

public class Warrior extends Hero implements WarriorAttackSkill, WarriorBuffSkill {

    // 워리어 생성자
    public Warrior() {

        setMaxHp(150);                  // 최대 체력
        setMaxMp(150);                  // 최대 마력
        setHp(150);                     // 체력
        setMp(150);                     // 마력
        setExp(getExp());               // 경험치
        setLevel(getLevel());           // 레벨
        setMoney(5000);           // 돈
        setJob("워리어");                 // 직업
        setBasicAttackName("베기");      // 기본 공격 이름
        setBasicAttackDamage(60);       // 기본 공격력
        setInitialMaxHp(150);           // 버프 스킬 사용 전 최대 체력
        setInitialHp(150);              // 버프 스킬 사용 전 체력
        setInitialMaxMp(150);           // 버프 스킬 사용 전 최대 마력
        setInitialMp(150);              // 버프 스킬 사용 전 마력
        setInitialDamage(60);           // 버프 스킬 사용 전 기본 공격력
    }

    // 워리어 기본 공격
    @Override
    public int useBasicAttack() {

        int baseDamage = getBasicAttackDamage(); // 워리어에게 부여한 기본 데미지
        boolean isRandom = Math.random() <= 0.5; // 랜덤 데미지 추가 확률 설정
        int randomDamage = isRandom ? (int) (Math.random() * 3) + 1 : 0; // 랜덤 데미지 부여
        boolean isCritical = Math.random() <= 0.3; // 크리티컬 추가 데미지 확률 설정
        int criticalDamage = isCritical ? 30 : 5; // 크리티컬일 경우 30의 데미지, 아닐 경우 0
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 기본 데미지와 랜덤 데미지 합치기

        System.out.println("‣" + getBasicAttackName() + "!" + totalDamage + " 만큼의 피해를 가했습니다.\n");

        return totalDamage;
    }

    // 워리어 기본 패시브 스킬
    @Override
    public void usePassiveSkill() {

        setMaxHp((int) (getMaxHp() * 1.5)); // 최대 체력이 1.5배 증가
        setHp((int) (getHp()*1.5)); // 현재 체력이 1.5배 증가
        System.out.println("‣'아머 마스터리' 발동! -> 현재 HP와 최대 HP가 1.5배 증가 합니다." +
                "\n‣현재 HP : " + getHp() +
                "\n‣최대 HP : " + getMaxHp() + "\n");
    }

    // 워리어 인터페이스 공격 스킬
    @Override
    public int powerStrike() {

        int mpCost = 15;
        int baseDamage = getBasicAttackDamage() * 3;
        boolean isRandom = Math.random() <= 0.5;
        int randomDamage = isRandom ? (int) (Math.random() * 5) + 5 : 0;
        boolean isCritical = Math.random() <= 0.3;
        int criticalDamage = isCritical ? 50 : 20;
        int totalDamage = baseDamage + randomDamage + criticalDamage; // 워리어에게 부여한 기본 데미지

        if (getMp() >= mpCost) {

            // MP 감소
            setMp(getMp() - mpCost);

            System.out.println("‣MP가 15 만큼 감소했습니다. 현재 MP: " + getMp() +
                    "\n‣파워스트라이크! " + totalDamage + " 만큼의 피해를 가했습니다.\n");

            return totalDamage;
        }
        else {
            System.out.println("‣MP가 부족하여 기본 공격을 사용합니다." +
                    "\n‣현재 MP: " + getMp() + "\n");

            return useBasicAttack();
        }

    }

    // 워리어 인터페이스 버프 스킬
    @Override
    public void guardMaster() {

        int mpCost = 15;

        if(getMp() >= mpCost) {

            setMp(getMp() - mpCost);
            setMaxHp(getMaxHp() * 2);
            setHp(getHp() * 2);
            System.out.println("‣'가드마스터' 발동! -> MP를 15만큼 소모하고, 최대 HP와 현재 HP가 2배 증가 합니다." +
                    "\n‣최대 HP : " + getMaxHp() + " 현재 HP : " + getHp() + "\n");
        }
        else {
            System.out.println("‣MP가 충분하지 않습니다. 현재 MP: " + getMp() + "\n");
        }

    }



}