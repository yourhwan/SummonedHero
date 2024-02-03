public class Main {
    public static void main(String[] args) {

        // 모든 캐릭터를 나타내는 기본 클래스
        class Character {
            String name; // 이름
            int level; // 레벨
            int experiencePoints; // 경험치

            // 생성자
            public Character(String name, int level) {
                this.name = name;
                this.level = level;
                this.experiencePoints = 0;
            }

            void attack() {
                System.out.println(name + "이(가) 공격합니다!");
            }

            void defend() {
                System.out.println(name + "이(가) 방어합니다!");
            }

            void gainExperience(int points) {
                experiencePoints += points;
                if (experiencePoints > 100) {
                    experiencePoints = 100;
                }
                System.out.println(name + "이(가) " + points + " 경험치를 획득했습니다.");
            }
        }

// 인간 종족을 나타내는 하위 클래스
        class Human extends Character {
            String gender; // 성별

            // 생성자
            public Human(String name, int level, String gender) {
                super(name, level);
                this.gender = gender;
            }
        }

// 직업을 나타내는 하위 클래스
        class Swordsman extends Human {
            // 생성자
            public Swordsman(String name, int level, String gender) {
                super(name, level, gender);
            }
            // Swordsman에 특화된 추가 메서드 또는 속성을 정의할 수 있습니다.
        }

        class Berserker extends Swordsman {
            // 생성자
            public Berserker(String name, int level, String gender) {
                super(name, level, gender);
            }
            // Berserker에 특화된 추가 메서드 또는 속성을 정의할 수 있습니다.
        }

        class SwordMaster extends Swordsman {
            // 생성자
            public SwordMaster(String name, int level, String gender) {
                super(name, level, gender);
            }
            // SwordMaster에 특화된 추가 메서드 또는 속성을 정의할 수 있습니다.
        }

        class DualBlade extends Human {
            // 생성자
            public DualBlade(String name, int level, String gender) {
                super(name, level, gender);
            }
            // DualBlade에 특화된 추가 메서드 또는 속성을 정의할 수 있습니다.
        }

// 무기를 나타내는 하위 클래스
        class GreatSword {
            // GreatSword에 특화된 메서드 또는 속성을 정의할 수 있습니다.
        }

        class ShortSword {
            // ShortSword에 특화된 메서드 또는 속성을 정의할 수 있습니다.
        }

        class Sword {
            // Sword에 특화된 메서드 또는 속성을 정의할 수 있습니다.
        }

        class Dagger {
            // Dagger에 특화된 메서드 또는 속성을 정의할 수 있습니다.
        }

// 모든 몬스터를 나타내는 기본 클래스
        class Monster extends Character {
            // 생성자
            public Monster(String name, int level) {
                super(name, level);
            }
        }

// 오우거 몬스터를 나타내는 하위 클래스
        class Ogre extends Monster {
            // 생성자
            public Ogre(String name, int level) {
                super(name, level);
            }
        }

        class Kijin extends Ogre {
            // 생성자
            public Kijin(String name, int level) {
                super(name, level);
            }
        }

        class Oni extends Ogre {
            // 생성자
            public Oni(String name, int level) {
                super(name, level);
            }
        }

        class Kishin extends Ogre {
            // 생성자
            public Kishin(String name, int level) {
                super(name, level);
            }
        }

// 고블린 몬스터를 나타내는 하위 클래스
        class Goblin extends Monster {
            // 생성자
            public Goblin(String name, int level) {
                super(name, level);
            }
        }

        class Hobgoblin extends Goblin {
            // 생성자
            public Hobgoblin(String name, int level) {
                super(name, level);
            }
        }

        class Goblina extends Goblin {
            // 생성자
            public Goblina(String name, int level) {
                super(name, level);
            }
        }

// 데빌 몬스터를 나타내는 하위 클래스
        class Devil extends Monster {
            // 생성자
            public Devil(String name, int level) {
                super(name, level);
            }
        }

        class ModernDevil extends Devil {
            // 생성자
            public ModernDevil(String name, int level) {
                super(name, level);
            }
        }

        class MedievalDevil extends Devil {
            // 생성자
            public MedievalDevil(String name, int level) {
                super(name, level);
            }
        }

        class AncientDevil extends Devil {
            // 생성자
            public AncientDevil(String name, int level) {
                super(name, level);
            }
        }

        class PrimordialDevil extends Devil {
            // 생성자
            public PrimordialDevil(String name, int level) {
                super(name, level);
            }
        }

// 엔젤 몬스터를 나타내는 하위 클래스
        class Angel extends Monster {
            // 생성자
            public Angel(String name, int level) {
                super(name, level);
            }
        }

        class ArchangelOfCourage extends Angel {
            // 생성자
            public ArchangelOfCourage(String name, int level) {
                super(name, level);
            }
        }

        class ArchangelOfFate extends Angel {
            // 생성자
            public ArchangelOfFate(String name, int level) {
                super(name, level);
            }
        }

        class ArchangelOfHope extends Angel {
            // 생성자
            public ArchangelOfHope(String name, int level) {
                super(name, level);
            }
        }

        class ArchangelOfJustice extends Angel {
            // 생성자
            public ArchangelOfJustice(String name, int level) {
                super(name, level);
            }
        }

        class ArchangelOfWisdom extends Angel {
            // 생성자
            public ArchangelOfWisdom(String name, int level) {
                super(name, level);
            }
        }

    }
}