package dungeon;

import userjob.Hero;

public abstract class Monster {

    // 몬스터 기본 정보 필드
    private String name; // 몬스터 이름
    private int hp; // 체력
    private String basicAttackName; // 기본 공격 이름
    private int basicDamage; // 기본 공격 데미지
    private int money; // 떨굴 돈
    private int exp; // 떨굴 경험치



    // 하위 클래스에서 구현할 추상 메서드 생성
    abstract int dropMoney(); // 떨굴 돈 추상 메서드
    abstract int dropExp(); // 떨굴 경험치 추상 메서드
    abstract int useBasicAttack(Hero hero); // 일반 공격

    // 공격 받았을 경우 피해를 처리하는 메서드
    public void takeDamage(int damage) {

        hp -= damage; // 몬스터의 체력을 사용자로 부터의 피해량 만큼 감소 시킨다

        if (hp < 0) {
            hp = 0; // 체력이 0 미만이 되지 않도록
        }
    }

    // 생존여부 확인 메서드
    public boolean isAlive() {

        return hp > 0;
    }

    // 몬스터 사망 처리 및 사용자에게 경험치 및 돈 제공 메서드
    private void defeat(Hero hero) {

        if (!isAlive()) {
            // 몬스터에게 획득한 경험치
            int exp = dropExp();

            // 몬스터에게 획득한 돈
            int money = dropMoney();

            // 히어로 클래스의 경험치 획득 메서드 호출
            hero.gainExp(exp);

            // 히어로 클래스의 돈 획득 메서드 호출
            hero.gainMoney(money);

            System.out.println("몬스터를 처치했습니다. 경험치 " + exp+ ", 돈 " + money+ "을 획득 합니다." +
                    "\n현재 경험치: " + getExp() + " 현재 소지금: " + getMoney());
        }
    }


    // getter, setter 생성
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getBasicAttackName() {
        return basicAttackName;
    }

    public void setBasicAttackName(String basicAttackName) {
        this.basicAttackName = basicAttackName;
    }

    public int getBasicDamage() {
        return basicDamage;
    }

    public void setBasicDamage(int basicDamage) {
        this.basicDamage = basicDamage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
