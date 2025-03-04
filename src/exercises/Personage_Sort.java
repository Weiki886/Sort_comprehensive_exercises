package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Personage_Sort {

    // 加载数据文件并创建Personage对象
	public static ArrayList<Personage> loadPersonages(String filename) {
	    // 创建一个 ArrayList，用于存储加载的人物对象
	    ArrayList<Personage> personages = new ArrayList<>();

	    // 使用 try-with-resources 自动关闭资源
	    try (Scanner scanner = new Scanner(new File(filename), "UTF-8")) {
	        // 跳过文件中的第一行表头
	        if (scanner.hasNextLine()) {
	            scanner.nextLine();
	        }

	        // 逐行读取文件内容
	        while (scanner.hasNextLine()) {
	            // 获取当前行的文本数据
	            String line = scanner.nextLine();
	            // 使用split方法来分割字符串分割当前行的数据字段
	            String[] data = line.split("\\s+");
	            
	            // 检查当前行是否有预期的 11 个字段
	            if (data.length == 11) {
	                // 提取每个字段的数据，并解析为相应的类型
	                String name = data[0]; // 角色名称
	                int leadership = Integer.parseInt(data[1]); // 统率
	                int strength = Integer.parseInt(data[2]); // 武力
	                int intelligence = Integer.parseInt(data[3]); // 智力
	                int politics = Integer.parseInt(data[4]); // 政治
	                int charisma = Integer.parseInt(data[5]); // 魅力
	                String specialSkill = data[6]; // 特技
	                String pikeSoldier = data[7]; // 枪兵适性
	                String spearSoldier = data[8]; // 戟兵适性
	                String crossbowSoldier = data[9]; // 弩兵适性
	                String cavalrySoldier = data[10]; // 骑兵适性

	                // 创建一个 Personage 对象，使用提取的数据
	                Personage personage = new Personage(
	                    name, leadership, strength, intelligence, politics, charisma,
	                    specialSkill, pikeSoldier, spearSoldier, crossbowSoldier, cavalrySoldier
	                );

	                // 将创建的对象添加到列表中
	                personages.add(personage);
	            }
	        }
	    } catch (FileNotFoundException e) {
	        // 如果文件不存在，则提示错误信息
	        System.out.println("文件未找到: " + filename);
	    } catch (NumberFormatException e) {
	        // 如果数据格式错误（如字段无法解析为数字），则提示错误信息
	        System.out.println("数据格式错误，请检查文件内容！");
	    }

	    // 返回加载的 Personage 对象列表
	    return personages;
	}


/*将问题划分为已排序部分和未排序部分。
 * 初始时假定第一个元素为已排序，其余部分为未排序。
 * 从未排序部分逐个取元素，将其插入到已排序部分的正确位置。
 * 通过从后向前比较，找到插入点，将比当前元素小的元素依次后移，为插入元素腾出位置。
 * 一旦找到合适位置，将当前元素插入。
 * 每次插入操作后，已排序部分保持有序。
 * 重复此过程，直到未排序部分为空，最终完成排序，保证稳定性。*/
    // 定义一个方法，根据Leadership属性从大到小对Personage对象列表进行插入排序
    public static void insertionSortByLeadership(ArrayList<Personage> personages) {
        // 从第二个元素开始遍历，逐步构建排序好的部分
        for (int i = 1; i < personages.size(); i++) {
            // 保存当前要插入排序的对象
            Personage key = personages.get(i);
            // 定义一个索引j，用来指向当前key前面的位置
            int j = i - 1;

            // 在已经排序好的部分中寻找合适的插入位置
            // 条件是：还未到最左边，且当前比较的元素的Leadership属性小于key的Leadership
            while (j >= 0 && personages.get(j).getLeadership() < key.getLeadership()) {
                // 将元素后移一位，为key腾出插入位置
                personages.set(j + 1, personages.get(j));
                // 向前继续查找插入位置
                j--;
            }

            // 将key插入到正确的位置
            personages.set(j + 1, key);
        }

        // 输出排序结果
        System.out.println("根据统率从大到小排序结果：");
        // 遍历排序后的列表，调用每个Personage对象的display方法输出信息
        for (Personage p : personages) {
            p.display();
        }
    }


/*将问题分解为多轮比较与交换，每一轮从未排序部分中比较相邻元素，将较大的元素逐步向后移动，直至未排序部分的末尾。
 * 每完成一轮，未排序部分的最大值就被放置在正确位置，因此内层循环的范围逐渐缩小。
 * 通过反复比较和交换，最终将列表按指定顺序排列。
 * 设计的核心是依次缩小范围并通过相邻交换实现排序，同时确保算法简单直观，适合小规模数据，且保持稳定性。*/
    // 定义一个方法，根据Strength属性从大到小对Personage对象列表进行冒泡排序
    public static void bubbleSortByStrength(ArrayList<Personage> personages) {
        // 获取列表的大小
        int n = personages.size();
        // 外层循环控制排序的轮数，总共需要n-1轮
        for (int i = 0; i < n - 1; i++) {
            // 内层循环控制每一轮中的比较次数，每轮比较的次数逐渐减少
            // (n-i-1)是因为每完成一轮排序后，最后一个元素已经是最大值（对于这一轮来说）
            for (int j = 0; j < n - i - 1; j++) {
                // 比较相邻的两个元素，如果前一个元素的Strength小于后一个元素，则交换它们的位置
                if (personages.get(j).getStrength() < personages.get(j + 1).getStrength()) {
                    // 使用临时变量temp保存前一个元素
                    Personage temp = personages.get(j);
                    // 将后一个元素放到前一个位置
                    personages.set(j, personages.get(j + 1));
                    // 将保存的前一个元素放到后一个位置
                    personages.set(j + 1, temp);
                }
            }
        }

        // 输出排序结果
        System.out.println("根据武力从大到小排序结果：");
        // 遍历排序后的列表，调用每个Personage对象的display方法输出信息
        for (Personage p : personages) {
            p.display();
        }
    }


/*将问题分为已排序部分和未排序部分。
 * 通过逐轮选择，确定未排序部分中最大的元素并将其放置到当前未排序部分的起始位置。
 * 每轮操作中，从未排序部分中遍历元素，找到当前最大值的索引，并与未排序部分的第一个元素交换，从而缩小未排序范围，扩大已排序范围。
 * 通过这种方式，逐步将最大的元素移至正确位置。
 * 设计的核心在于找到当前最大值并将其移动到目标位置，算法简单直观，适合小规模数据排序，且不依赖额外空间。*/  
    // 定义一个方法，根据Intelligence属性从大到小对Personage对象列表进行选择排序
    public static void selectionSortByIntelligence(ArrayList<Personage> personages) {
        // 获取列表的大小
        int n = personages.size();
        // 外层循环控制排序的轮数，每次找到当前范围内的最大值
        for (int i = 0; i < n - 1; i++) {
            // 定义当前轮次中最大值的索引，初始为未排序部分的第一个元素索引
            int maxIdx = i;

            // 内层循环从未排序部分中寻找Intelligence属性最大的元素
            for (int j = i + 1; j < n; j++) {
                // 如果发现当前元素的Intelligence大于已知的最大值，则更新最大值索引
                if (personages.get(j).getIntelligence() > personages.get(maxIdx).getIntelligence()) {
                    maxIdx = j;
                }
            }

            // 将未排序部分的最大值与当前轮次的起始元素交换位置
            // 通过交换，将最大值放到排序后的正确位置
            Personage temp = personages.get(maxIdx);
            personages.set(maxIdx, personages.get(i));
            personages.set(i, temp);
        }

        // 输出排序结果
        System.out.println("根据智力从大到小排序结果：");
        // 遍历排序后的列表，调用每个Personage对象的display方法输出信息
        for (Personage p : personages) {
            p.display();
        }
    }


/*将列表分为左右两个部分，分别对每部分进行递归排序，直到每个部分仅剩一个元素或为空。
 * 随后通过一个合并操作将两个已排序的部分合并为一个完整的有序部分。
 * 合并时，通过辅助列表分别存储左、右部分的元素，依次比较它们的属性值，将较大的元素放入结果列表中，直到某一部分耗尽。
 * 最后将剩余部分直接加入结果列表中。
 * 整个设计基于分治思想，通过递归分割和逐步合并，完成排序过程，保证高效性和稳定性，适合处理大规模数据。*/
    // 定义一个方法，根据Politics属性从大到小对Personage对象列表进行归并排序
    public static void mergeSortByPolitics(ArrayList<Personage> personages, int left, int right) {
        // 递归终止条件：当左索引大于或等于右索引时，说明该部分只有一个元素或无元素
        if (left < right) {
            // 计算中间索引，将当前区间分为两部分
            int mid = (left + right) / 2;

            // 对左半部分递归进行归并排序
            mergeSortByPolitics(personages, left, mid);
            // 对右半部分递归进行归并排序
            mergeSortByPolitics(personages, mid + 1, right);

            // 合并两个已排序部分
            merge(personages, left, mid, right);
        }
    }

    // 辅助方法：将两个已排序的部分合并为一个有序部分
    private static void merge(ArrayList<Personage> personages, int left, int mid, int right) {
        // 计算左半部分和右半部分的大小
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 创建临时列表，用于存储左半部分和右半部分的元素
        ArrayList<Personage> leftList = new ArrayList<>();
        ArrayList<Personage> rightList = new ArrayList<>();

        // 将左半部分的元素添加到左临时列表
        for (int i = 0; i < n1; i++) {
            leftList.add(personages.get(left + i));
        }
        // 将右半部分的元素添加到右临时列表
        for (int j = 0; j < n2; j++) {
            rightList.add(personages.get(mid + 1 + j));
        }

        // 定义索引，用于遍历左、右临时列表及原列表
        int i = 0, j = 0, k = left;

        // 比较左、右临时列表的元素，按Politics属性从大到小顺序插入原列表
        while (i < n1 && j < n2) {
            if (leftList.get(i).getPolitics() >= rightList.get(j).getPolitics()) {
                personages.set(k++, leftList.get(i++));
            } else {
                personages.set(k++, rightList.get(j++));
            }
        }

        // 如果左临时列表还有剩余元素，依次插入原列表
        while (i < n1) {
            personages.set(k++, leftList.get(i++));
        }

        // 如果右临时列表还有剩余元素，依次插入原列表
        while (j < n2) {
            personages.set(k++, rightList.get(j++));
        }
    }

    // 定义一个方法，用于对列表进行排序并输出结果
    public static void sortAndDisplayByPolitics(ArrayList<Personage> personages) {
        // 调用归并排序方法，对整个列表从0到列表末尾进行排序
        mergeSortByPolitics(personages, 0, personages.size() - 1);

        // 输出排序结果
        System.out.println("根据政治从大到小排序结果：");
        // 遍历排序后的列表，调用每个Personage对象的display方法输出信息
        for (Personage p : personages) {
            p.display();
        }
    }


/* 通过分治思想，将列表分为小于和大于等于基准值（pivot）的两部分。
 * 选择当前区间最后一个元素作为基准值，遍历区间时，将大于等于基准值的元素移动到基准值左侧，并记录分区点位置。
 * 完成遍历后，将基准值放置到分区点，使其左右两侧分别满足排序要求。
 * 通过递归，对左右两部分重复上述操作，逐步缩小问题规模，最终完成排序。
 * 该算法利用分区操作进行原地排序，避免额外存储，同时通过比较与交换实现高效排序，适用于中大型数据集。*/
    // 定义一个方法，根据Charisma属性从大到小对Personage对象列表进行快速排序
    public static void quickSortByCharisma(ArrayList<Personage> personages, int low, int high) {
        // 递归终止条件：当low >= high时，区间中只有一个元素或没有元素，无需排序
        if (low < high) {
            // 对当前区间进行分区，并获取分区点（pivot）的索引
            int pivotIdx = partition(personages, low, high);

            // 对分区点左侧部分递归进行快速排序
            quickSortByCharisma(personages, low, pivotIdx - 1);
            // 对分区点右侧部分递归进行快速排序
            quickSortByCharisma(personages, pivotIdx + 1, high);
        }
    }

    // 辅助方法：对当前区间进行分区操作
    private static int partition(ArrayList<Personage> personages, int low, int high) {
        // 选择当前区间的最后一个元素作为基准值（pivot）
        int pivot = personages.get(high).getCharisma();
        // 定义一个指针i，用于记录当前小于或等于pivot部分的结束位置
        int i = low - 1;

        // 遍历当前区间中的所有元素（不包括pivot本身）
        for (int j = low; j < high; j++) {
            // 如果当前元素的Charisma大于或等于pivot，则将其交换到前面
            if (personages.get(j).getCharisma() >= pivot) {
                i++; // 移动指针i
                // 交换personages[i]和personages[j]，确保大的元素在前面
                Personage temp = personages.get(i);
                personages.set(i, personages.get(j));
                personages.set(j, temp);
            }
        }

        // 将pivot元素放置到分区点的正确位置（即i + 1）
        Personage temp = personages.get(i + 1);
        personages.set(i + 1, personages.get(high));
        personages.set(high, temp);

        // 返回分区点的索引
        return i + 1;
    }

    // 定义一个方法，用于对列表进行排序并输出结果
    public static void sortAndDisplayByCharisma(ArrayList<Personage> personages) {
        // 调用快速排序方法，对整个列表从0到列表末尾进行排序
        quickSortByCharisma(personages, 0, personages.size() - 1);

        // 输出排序结果
        System.out.println("根据魅力从大到小排序结果：");
        // 遍历排序后的列表，调用每个Personage对象的display方法输出信息
        for (Personage p : personages) {
            p.display();
        }
    }


    // 兵种适性优先级比较
    private static int getSoldierPriority(String soldierType) {
        switch (soldierType) {
            case "S":
                return 4;
            case "A":
                return 3;
            case "B":
                return 2;
            case "C":
                return 1;
            default:
                return 0;
        }
    }

/* 该算法对角色列表进行排序，依据是每个角色的枪兵适性优先级，从大到小排列。
 * 选择排序的基本思想是通过不断选择未排序部分中优先级最大的元素，将其放到已排序部分的末尾。
 * 初始状态下，整个列表都是未排序的。
 * 通过遍历未排序部分，找到优先级最高的角色，并将其与当前未排序部分的第一个角色交换位置，从而将其放入已排序部分。
 * 这个过程不断重复，直到未排序部分为空，整个列表排序完成。
 * 每次遍历都会比较剩余元素的优先级，找到最大值，并通过交换将其位置调整到正确的位置。*/   
    // 选择排序：根据枪兵适性从大到小排序
    public static void selectionSortByPikeSoldier(ArrayList<Personage> personages) {
        // 获取列表的长度
        int n = personages.size();

        // 外层循环：从头到尾遍历每个元素
        for (int i = 0; i < n - 1; i++) {
            // 假设当前位置 i 为最大值的索引
            int maxIdx = i;

            // 内层循环：从 i+1 到末尾寻找更大的元素
            for (int j = i + 1; j < n; j++) {
                // 如果当前元素的枪兵适性优先级大于已知最大值，则更新 maxIdx
                if (getSoldierPriority(personages.get(j).getPikeSoldier()) > 
                    getSoldierPriority(personages.get(maxIdx).getPikeSoldier())) {
                    maxIdx = j; // 更新最大值的索引
                }
            }

            // 交换最大值和当前位置的元素
            Personage temp = personages.get(maxIdx);
            personages.set(maxIdx, personages.get(i));
            personages.set(i, temp);
        }

        // 打印排序结果
        System.out.println("根据枪兵适性从大到小排序结果：");
        // 遍历排序后的列表，调用每个角色的 display 方法展示信息
        for (Personage p : personages) {
            p.display();
        }
    }


 // 选择排序：根据弩兵适性从大到小排序
    public static void selectionSortByCrossbowSoldier(ArrayList<Personage> personages) {
        int n = personages.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (getSoldierPriority(personages.get(j).getCrossbowSoldier()) > getSoldierPriority(personages.get(maxIdx).getCrossbowSoldier())) {
                    maxIdx = j;
                }
            }

            Personage temp = personages.get(maxIdx);
            personages.set(maxIdx, personages.get(i));
            personages.set(i, temp);
        }

        System.out.println("根据弩兵适性从大到小排序结果：");
        for (Personage p : personages) {
            p.display();
        }
    }

 // 选择排序：根据骑兵适性从大到小排序
    public static void selectionSortByCavalrySoldier(ArrayList<Personage> personages) {
        int n = personages.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (getSoldierPriority(personages.get(j).getCavalrySoldier()) > getSoldierPriority(personages.get(maxIdx).getCavalrySoldier())) {
                    maxIdx = j;
                }
            }

            Personage temp = personages.get(maxIdx);
            personages.set(maxIdx, personages.get(i));
            personages.set(i, temp);
        }

        System.out.println("根据骑兵适性从大到小排序结果：");
        for (Personage p : personages) {
            p.display();
        }
    }

 // 选择排序：根据戟兵适性从大到小排序
    public static void selectionSortBySpearSoldier(ArrayList<Personage> personages) {
        int n = personages.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (getSoldierPriority(personages.get(j).getSpearSoldier()) > getSoldierPriority(personages.get(maxIdx).getSpearSoldier())) {
                    maxIdx = j;
                }
            }

            Personage temp = personages.get(maxIdx);
            personages.set(maxIdx, personages.get(i));
            personages.set(i, temp);
        }

        System.out.println("根据戟兵适性从大到小排序结果：");
        for (Personage p : personages) {
            p.display();
        }
    }

    // 主函数
    public static void main(String[] args) {
    	// 创建一个 ArrayList，用于存储从文件中加载的角色信息
        ArrayList<Personage> personages = loadPersonages("D:\\eclipse\\eclipseSoftware\\Sort_comprehensive_exercises\\三国志11人物能力数值表.txt");
        // 调用 loadPersonages 方法，读取指定路径的文件内容，解析并加载为 Personage 对象列表
        
        // 创建一个 Scanner 对象，用于接收用户输入
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n====================  排序菜单  ====================");
            System.out.println("  请选择排序方式：");
            System.out.println("---------------------------------------------------");
            System.out.println("  1. 插入排序（统率，从大到小）");
            System.out.println("  2. 冒泡排序（武力，从大到小）");
            System.out.println("  3. 选择排序（智力，从大到小）");
            System.out.println("  4. 归并排序（政治，从大到小）");
            System.out.println("  5. 快速排序（魅力，从大到小）");
            System.out.println("  6. 选择排序（枪兵适性，从大到小）");
            System.out.println("  7. 选择排序（戟兵适性，从大到小）");
            System.out.println("  8. 选择排序（弩兵适性，从大到小）");
            System.out.println("  9. 选择排序（骑兵适性，从大到小）");
            System.out.println(" 10. 退出程序");
            System.out.println("===================================================\n");

            System.out.print("请输入您的选择（1-10）：");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertionSortByLeadership(personages);
                    break;
                case 2:
                    bubbleSortByStrength(personages);
                    break;
                case 3:
                    selectionSortByIntelligence(personages);
                    break;
                case 4:
                    sortAndDisplayByPolitics(personages);
                    break;
                case 5:
                    sortAndDisplayByCharisma(personages);
                    break;
                case 6:
                    selectionSortByPikeSoldier(personages);
                    break;
                case 7:
                    selectionSortBySpearSoldier(personages);
                    break;
                case 8:
                    selectionSortByCrossbowSoldier(personages);
                    break;
                case 9:
                    selectionSortByCavalrySoldier(personages);
                    break;
                case 10:
                    System.out.println("\n程序已退出！感谢使用！");
                    return;
                default:
                    System.out.println("\n无效选择，请重新输入！");
            }
            scanner.close();
        }
    }
 }
