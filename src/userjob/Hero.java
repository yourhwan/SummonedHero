package userjob;


import java.util.Scanner;

public abstract class Hero {

    // 캐릭터 기본 정보 필드
    private int hp; // 체력
    private int mp; // 마나
    private int exp = 0; // 현재 경험치
    private static final int maxExp = 100; // 최대 경험치
    private int level = 1; // 레벨
    private int money = 0; // 돈
    private String job; // 직업
    private String nickname; // 이름
    private String basicAttackName; // 기본 공격 이름
    private String passiveSkillName; // 기본 패시브스킬 이름
    private int basicAttackDamage; // 기본 공격 데미지



    // 하위 클래스에서 구현할 추상 메서드 생성
    abstract int useBasicAttack(); // 일반 공격 사용, 하위 클래스 특성에 맞게 작성할 예정

    abstract void usePassiveSkill(); // 패시브 스킬 사용, 하위 클래스 특성에 맞게 작성할 예정


    // 닉네임 설정 메서드
    protected void setNicknameFromUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("영웅의 이름을 입력해주세요 : " );
        setNickname(sc.nextLine());
        System.out.println("영웅의 이름이 " + getNickname() + "로 설정 되었습니다.");
    }

    // 레벨업 메서드
    protected void checkLevelUp() {

        int currentExp = getExp();

        if (currentExp >= maxExp) {
            int remainExp = currentExp - maxExp;
            setLevel(getLevel() + 1); // 레벨은 1씩 증가
            setExp(remainExp); // 레벨업 후 남은 경험치를 현재 경험치로 설정
            setHp(getHp() + 10); // hp 10 증가
            setMp(getMp() + 10); // mp 10 증가
            setBasicAttackDamage(getBasicAttackDamage() + 5);
        }

        System.out.println("레벨업을 축하합니다! 현재 레벨은 " + getLevel() +" 입니다. 더욱 강해진 힘을 느끼는 "+getNickname());
    }

    // 캐릭터 정보 불러오는 메서드
    @Override
    public String toString(){

        return "==========" + getNickname() + "의 정보 ==========\n" +
                "CLASS: " + getJob() + "\n" +
                "LEVEL: " + getLevel() + "\n" +
                "Damage: " + getBasicAttackDamage() + "\n"+
                "EXP: " + getExp() + "\n" +
                "HP: " + getHp() + "\n" +
                "MP: " + getMp() + "\n" +
                "MONEY: " + getMoney() + "\n" +
                "==============================";
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public int getBasicAttackDamage() {
        return basicAttackDamage;
    }

    public void setBasicAttackDamage(int basicAttackDamage) {
        this.basicAttackDamage = basicAttackDamage;
    }

}
