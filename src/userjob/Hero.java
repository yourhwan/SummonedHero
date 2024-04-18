package userjob;


import dungeon.Monster;

public abstract class Hero {

    // 캐릭터 기본 정보 필드
    private int hp; // 현재 체력
    private int maxHp; // 최대 체력
    private int initialHp; // 버프스킬 사용 전 현재 hp값 저장용
    private int initialMaxHp; // 버프스킬 사용 전 최대 hp값 저장용
    private int mp; // 현재 마나
    private int maxMp; // 최대 마나
    private int initialMp; // 버프 스킬 사용 전 mp값 저장용
    private int initialMaxMp; // 버프 스킬 사용 전 최대 mp값 저장용
    private int exp = 0; // 현재 경험치
    private int maxExp = 100; // 최대 경험치
    private int level = 1; // 레벨
    private int money = 0; // 돈
    private String job; // 직업
    private String nickname; // 이름
    private String basicAttackName; // 기본 공격 이름
    private String passiveSkillName; // 기본 패시브스킬 이름
    private int basicAttackDamage; // 기본 공격 데미지
    private int initialDamage; // 버프 스킬 사용 전 데미지 저장용

    // 하위 클래스에서 구현할 추상 메서드 생성

    public abstract int useBasicAttack(); // 일반 공격 사용, 하위 클래스 특성에 맞게 작성할 예정
    public abstract void usePassiveSkill(); // 패시브 스킬 사용, 하위 클래스 특성에 맞게 작성할 예정


    // 생존여부 확인 메서드
    public boolean isAlive() {

        return hp > 0;
    }

    // 사망으로 인한 전투 패배 메서드
    protected void defeat() {

        if (!isAlive()) {

            System.out.println("HP가 0이 되어 사망 했습니다. 마을로 돌아갑니다.");
        }
    }

    // 공격 받았을 경우 피해를 처리하는 메서드
    public void takeDamage(int damage) {

        hp -= damage; // 사용자의 체력을 몬스터로 부터의 피해량 만큼 감소 시킨다
        System.out.println(damage + " 만큼의 피해를 입었습니다." +
                "\n현재 HP: " + getHp() + "/" + getMaxHp());

        if (hp < 0) {
            hp = 0; // 체력이 0 미만이 되지 않도록
        }

        if (!isAlive()) {
            defeat();
        }

    }

    // 경험치 획득 메서드
    public void gainExp(int exp) {

        int currentExp = getExp();
        int maxExp = getMaxExp();

        currentExp += exp;

        System.out.println(exp + "만큼의 경험치를 획득 했습니다.");

        while(currentExp >= maxExp) {
            checkLevelUp();
            currentExp -= maxExp;
            maxExp = getMaxExp();
        }

        setExp(currentExp);
    }

    // 돈 획득 메서드
    public void gainMoney(int money) {

        int currentMoney = getMoney();
        currentMoney += money;

        setMoney(currentMoney);

        System.out.println(money + "만큼의 돈을 획득 했습니다.");
    }

    // 레벨업 메서드
    protected void checkLevelUp() {

        revert(); // 레벨업을 위해 버프 해제

        setLevel(getLevel() + 1); // 레벨은 1씩 증가
        setMaxExp(getMaxExp() + 25); // 최대 경험치 25 증가
        setMaxHp(getMaxHp() + 20); // 최대 hp 20 증가
        setMaxMp(getMaxMp() + 20); // 최대 mp 20 증가
        setBasicAttackDamage(getBasicAttackDamage() + 20); // 기본 공격력 20 증가
        setInitialDamage(getBasicAttackDamage());
        setHp(getMaxHp()); // 체력 완전 회복
        setMp(getMaxMp()); // 마나 완전 회복
        setInitialHp(getHp());
        setInitialMp(getMp());
        setInitialMaxHp(getMaxHp()); // 버프 전 최대 체력 업데이트
        setInitialMaxMp(getMaxMp()); // 버프 전 최대 마나 업데이트

        System.out.println("레벨업을 축하합니다! 현재 레벨은 " + getLevel() +" 입니다. 더욱 강해진 힘을 느끼는 "+getNickname());

        usePassiveSkill(); // 버프 재적용
    }

    // 캐릭터 정보 불러오는 메서드
    @Override
    public String toString(){

        return "==========" + getNickname() + "의 정보 ==========\n" +
                "CLASS: " + getJob() + "\n" +
                "LEVEL: " + getLevel() + "\n" +
                "Damage: " + getBasicAttackDamage() + "\n"+
                "EXP: " + getExp() + "\n" +
                "HP: " + getMaxHp() + "\n" +
                "MP: " + getMaxMp() + "\n" +
                "MONEY: " + getMoney() + "\n" +
                "==============================";
    }

    // 버프 해제 메서드
    public void revert() {
        hp = initialHp;
        maxHp = initialMaxHp;
        mp = initialMaxMp;
        maxMp = initialMaxMp;
        basicAttackDamage = initialDamage;
    }


    // getter, setter 생성

    public int getInitialMaxHp() {
        return initialMaxHp;
    }

    public void setInitialMaxHp(int initialMaxHp) {
        this.initialMaxHp = initialMaxHp;
    }

    public int getInitialHp() {
        return initialHp;
    }

    public void setInitialHp(int initialHp) {
        this.initialHp = initialHp;
    }

    public int getInitialMaxMp() {
        return initialMaxMp;
    }

    public void setInitialMaxMp(int initialMaxMp) {
        this.initialMaxMp = initialMaxMp;
    }

    public int getInitialMp() {
        return initialMp;
    }

    public void setInitialMp(int initialMp) {
        this.initialMp = initialMp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) { this.mp = mp; }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxExp() { return maxExp; }

    public void setMaxExp(int maxExp) { this.maxExp = maxExp; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) { this.level = level; }

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

    public int getInitialDamage() {
        return initialDamage;
    }

    public void setInitialDamage(int initialDamage) {
        this.initialDamage = initialDamage;
    }

}
