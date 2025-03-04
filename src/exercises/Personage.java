package exercises;

// 定义一个代表人物的类 Personage，用于描述人物的各种属性和行为
//这个类定义了10个私有属性，每个属性都对应人物的一个特定特征。私有属性意味着它们不能直接从类的外部访问，只能通过类内部的方法（如构造器和getter方法）来访问和修改
public class Personage {
    // 私有成员变量，表示人物的各项基本属性和能力值
	private String name;              // 姓名
    private int leadership;           // 统率
    private int strength;             // 武力
    private int intelligence;         // 智力
    private int politics;             // 政治
    private int charisma;             // 魅力
    private String specialSkill;      // 特技
    private String pikeSoldier;       // 枪兵适性
    private String spearSoldier;      // 戟兵适性
    private String crossbowSoldier;   // 弩兵适性
    private String cavalrySoldier;    // 骑兵适性

    //构造方法允许在创建 Personage 类的实例时初始化所有属性。this 关键字用于区分构造方法参数和类的属性
    public Personage(String name, int leadership, int strength, int intelligence, int politics, int charisma,
                     String specialSkill, String pikeSoldier, String spearSoldier, String crossbowSoldier, String cavalrySoldier) {
        this.name = name;                     // 初始化姓名
        this.leadership = leadership;         // 初始化统率能力
        this.strength = strength;             // 初始化武力值
        this.intelligence = intelligence;     // 初始化智力值
        this.politics = politics;             // 初始化政治能力
        this.charisma = charisma;             // 初始化魅力值
        this.specialSkill = specialSkill;     // 初始化特技
        this.pikeSoldier = pikeSoldier;       // 初始化枪兵适性
        this.spearSoldier = spearSoldier;     // 初始化戟兵适性
        this.crossbowSoldier = crossbowSoldier; // 初始化弩兵适性
        this.cavalrySoldier = cavalrySoldier; // 初始化骑兵适性
    }

    // 各属性的 getter 方法，用于获取私有属性的值
    //这些公共方法允许外部代码读取私有属性的值。每个方法对应一个属性，返回该属性的值
    public String getName() {
        return name; //姓名
    }

    
    public int getLeadership() {
        return leadership; //统率能力值
    }

    
    public int getStrength() {
        return strength; //武力值
    }

    
    public int getIntelligence() {
        return intelligence; //智力值
    }

    
    public int getPolitics() {
        return politics; //政治能力值
    }

    
    public int getCharisma() {
        return charisma; //魅力值
    }

    
    public String getPikeSoldier() {
        return pikeSoldier; //枪兵适性
    }

    
    public String getSpearSoldier() {
        return spearSoldier; //戟兵适性
    }

    
    public String getCrossbowSoldier() {
        return crossbowSoldier; //弩兵适性
    }

    
    public String getCavalrySoldier() {
        return cavalrySoldier; //骑兵适性
    } 

    
    //显示人物的所有属性信息
    //整齐排列所有属性
    public void display() {
        System.out.println(
            String.format(
                "姓名: %-6s\t  统率: %-4d 武力: %-4d 智力: %-4d 政治: %-4d 魅力: %-4d 特技: %-4s 枪兵适性: %-2s 戟兵适性: %-2s 弩兵适性: %-2s 骑兵适性: %-2s",
                name, leadership, strength, intelligence, politics, charisma, specialSkill, pikeSoldier, spearSoldier, crossbowSoldier, cavalrySoldier
            )
        );
    }
}
