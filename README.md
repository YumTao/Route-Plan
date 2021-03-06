# 模拟滴滴打车路径规划
## 问题:
有一坐标轴,和A,B,C,D四个人,每个人都有一个起点和一个终点,且都在坐标轴上. 
现在有一辆小汽车,从坐标轴原点出发,需要将A,B,C,D分别从各自的起点载到各自的终点. A,B,C,D的起点和终点随机,如何设计一个算法能保证司机走的路线最短呢?

## 思路:
- 1.将各个点按距离基点的距由小到大进行排序
- 2.遍历排序后的链表,对于同一个人的,保证他的起点在终点之前.出现终点在起点前情况,把终点移动至起点后一位
- 3.更改基点位置,变为链表的第一个元素,对链表的第二个元素起再次进行1,2步骤,递归. 直至基点位置是链表的最后一个元素时跳出递归.

## 实现:
- 1.定义一个Person类,属性有起点和终点(类型为Point),一个person对象表示一个人
- 2.定义Point类,表示坐标,属性分别有位置,与基准距离
- 3.创建4个person对象,并分别给其的起点与终点进行初始化.
- 4.取出所有person的所有起点和终点,放入链表中,对链表按照思路的算法进行排序递归.

## 代码实现:
- 1.将工程导入IDE中(普通java工程jar)
- 2.运行com.yumtao.route.GetRoute类的Main方法
- 3.输入A,B,C,D的位置
- 4.完成规划
