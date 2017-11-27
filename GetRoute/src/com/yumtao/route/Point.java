package com.yumtao.route;

/**
 * 起点/终点对象
 * @author yumTao
 *
 */
public class Point {
	private int distance;		// 离基点距离
	private int location;		// 当前位置

	// 获取点到original的距离
	public int getDistance(int original) {
		return Math.abs(original - location);
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

}
