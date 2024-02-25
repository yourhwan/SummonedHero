package dungeon;

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
