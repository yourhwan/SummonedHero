package rpggame;

public abstract class Hero {

    // 캐릭터 기본 정보 필드
    private int hp;
    private int mp;
    private int exp = 0;
    private int level = 1;
    private int money = 0;
    private String job;
    private String basicAttackName;
    private String passiveSkillName;
    private int basicSkillDamage;


    // 하위 클래스에서 구현할 추상 메서드 생성
    abstract void useBasicAttack(); // 일반 공격 사용, 하위 클래스 특성에 맞게 작성할 예정

    abstract void usePassiveSkill(); // 패시브 스킬 사용, 하위 클래스 특성에 맞게 작성할 예정


    // 레벨업 메서드
    protected void levelUp() {
        if (getExp() >= 100) {
            setLevel(getLevel() + 1); // 레벨은 1씩 증가
            setExp(0); // 경험치 0으로 초기화
            setHp(getHp() + 10); // hp 10 증가
            setMp(getMp() + 10); // mp 10 증가
        }
    }





    // getter, setter 생성
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBasicAttackName() {
        return basicAttackName;
    }

    public void setBasicAttackName(String basicAttackName) {
        this.basicAttackName = basicAttackName;
    }

    public String getPassiveSkillName() {
        return passiveSkillName;
    }

    public void setPassiveSkillName(String passiveSkillName) {
        this.passiveSkillName = passiveSkillName;
    }

    public int getBasicSkillDamage() {
        return basicSkillDamage;
    }

    public void setBasicSkillDamage(int basicSkillDamage) {
        this.basicSkillDamage = basicSkillDamage;
    }
}
