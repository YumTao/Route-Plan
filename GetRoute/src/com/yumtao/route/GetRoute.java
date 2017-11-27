package com.yumtao.route;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 获取路线Main Class
 * @author yumTao
 *
 */
public class GetRoute {
	public static void main(String[] args) {
		/**
		 * 思路:
		 * 1.将各个点按距离基点的距由小到大进行排序
		 * 2.遍历排序后的链表,对于同一个人的,保证他的起点在终点之前.出现终点在起点前情况,把终点移动至起点后一位
		 * 3.更改基点位置,变为链表的第一个元素,对链表的第二个元素起再次进行1,2步骤,递归. 直至基点位置是链表的最后一个元素时跳出递归.
		 */
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		// 初始化person A B C D 起始点位置
		System.out.println("输入A用户起点和终点位置,用','隔开");
		String sourceA = scanner.nextLine();
		String[] sourceAString = sourceA.split(",");
		Person personA = new Person();
		Point pointSrcA = new Point();
		Point pointDestA = new Point();
		pointSrcA.setLocation(Integer.valueOf(sourceAString[0]));
		pointDestA.setLocation(Integer.valueOf(sourceAString[1]));
		personA.setSrouce(pointSrcA);
		personA.setDestination(pointDestA);

		System.out.println("输入B用户起点和终点位置,用','隔开");
		String sourceB = scanner.nextLine();
		String[] sourceBString = sourceB.split(",");
		Person personB = new Person();
		Point pointSrcB = new Point();
		Point pointDestB = new Point();
		pointSrcB.setLocation(Integer.valueOf(sourceBString[0]));
		pointDestB.setLocation(Integer.valueOf(sourceBString[1]));
		personB.setSrouce(pointSrcB);
		personB.setDestination(pointDestB);

		System.out.println("输入C用户起点和终点位置,用','隔开");
		String sourceC = scanner.nextLine();
		String[] sourceCString = sourceC.split(",");
		Person personC = new Person();
		Point pointSrcC = new Point();
		Point pointDestC = new Point();
		pointSrcC.setLocation(Integer.valueOf(sourceCString[0]));
		pointDestC.setLocation(Integer.valueOf(sourceCString[1]));
		personC.setSrouce(pointSrcC);
		personC.setDestination(pointDestC);

		System.out.println("输入D用户起点和终点位置,用','隔开");
		String sourceD = scanner.nextLine();
		String[] sourceDString = sourceD.split(",");
		Person personD = new Person();
		Point pointSrcD = new Point();
		Point pointDestD = new Point();
		pointSrcD.setLocation(Integer.valueOf(sourceDString[0]));
		pointDestD.setLocation(Integer.valueOf(sourceDString[1]));
		personD.setSrouce(pointSrcD);
		personD.setDestination(pointDestD);

		// 将A,B,C,D放进链表
		LinkedList<Point> arr = new LinkedList<>();
		arr.add(pointSrcA);
		arr.add(pointDestA);
		arr.add(pointSrcB);
		arr.add(pointDestB);
		arr.add(pointSrcC);
		arr.add(pointDestC);
		arr.add(pointSrcD);
		arr.add(pointDestD);

		Person[] persons = { personA, personB, personC, personD };
		LinkedList<Point> cycleOrder = cycleOrder(arr, persons, -1);

		String result = "形式路径: ";
		for (Point point : cycleOrder) {
			result += point.getLocation() + "--->";
		}
		
		System.out.println(result.substring(0, result.length() - 4));

	}

	private static LinkedList<Point> cycleOrder(LinkedList<Point> arr, Person[] persons, int originalIndex) {
		// 1.按距离排序
		getOrderPoint(arr, originalIndex);

		// 2.遍历判断保证起点必须在终点前
		for (int i = 0; i < persons.length; i++) {
			int src = -1;
			int dest = 0;

			// 取第一个person
			for (int j = 0; j < arr.size(); j++) {
				if (persons[i].getSrouce() == arr.get(j)) {
					src = j; // 起点所在的索引
				}
				if (persons[i].getDestination() == arr.get(j)) {
					dest = j; // 终点所在的索引
				}

			}

			if (src > dest) {
				// 存在person的终点在起点后面,将终点移动到起点后面一位
				Point destPoint = arr.get(dest);
				arr.add(src + 1, destPoint);
				arr.remove(dest);

			}
		}

		if (originalIndex < arr.size() - 1) {
			// 递归
			arr = cycleOrder(arr, persons, ++originalIndex);
		}
		return arr;

	}

	// 按照点距离基点的距离从小到大排序
	private static LinkedList<Point> getOrderPoint(LinkedList<Point> arr, int originalIndex) {

		int originalPosition = 0;
		try {
			originalPosition = arr.get(originalIndex).getLocation();
		} catch (Exception e) {
		}
		
		if (originalIndex < 0) {
			originalIndex = 0;
			originalPosition = 0;		// 假设车辆基点位置为0
		}
		
		for (int i = 0 + originalIndex; i < arr.size() - 1; i++) { // 外层循环控制排序趟数

			for (int j = 0 + originalIndex; j < arr.size() - 1; j++) { // 内层循环控制每一趟排序多少次
				if (arr.get(j).getDistance(originalPosition) > arr.get(j + 1).getDistance(originalPosition)) {
					// 交换j 和 j+1的元素
					j = swap(arr, j);

				}
			}
		}

		return arr;
	}

	// 交换两元素位置  A B ==> B A
	private static int swap(LinkedList<Point> arr, int j) {
		int beginIndex = j;
		// 交换j 和 j+1的元素
		Point begin = arr.get(j);
		Point end = arr.get(j + 1);

		int tempIndex = j--;
		arr.remove(tempIndex < 0 ? 0 : tempIndex);

		int tempnextIndex = j-- + 1;
		arr.remove(tempnextIndex < 0 ? 0 : tempnextIndex);

		if (beginIndex < arr.size()) {
			arr.add(beginIndex, end);
		} else {
			arr.add(end);
		}
		if (beginIndex + 1 < arr.size()) {
			arr.add(beginIndex + 1, begin);
		} else {
			arr.add(begin);
		}

		j = j < 0 ? 0 : j;
		return j;
	}

}
