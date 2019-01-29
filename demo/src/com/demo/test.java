package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class test {

	public static void main(String[] args) {
		readFileByLines("G:\\student.txt");
	}

	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			List<Student> list = new ArrayList<Student>();
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				String[] str = tempString.split("\\|");
				Student stu = new Student();
				if (line != 1) {
					for (int i = 0; i < str.length; i++) {
						if (i == 0) {
							stu.setName(str[i]);
						}
						if (i == 1) {
							stu.setAge(str[i]);
						}
						if (i == 2) {
							stu.setId(str[i]);
						}
						if (i == 3) {
							stu.setScore(str[i]);
						}
					}
					list.add(stu);
				}
				line++;

			}
			Comparator<Student> comparator = new Comparator<Student>() {
				public int compare(Student s1, Student s2) {
					// 先排年龄
					if  (!s1.getScore().equals(s2.getScore())) {
	                    // 年龄相同则按姓名排序
	                    return s1.getScore().compareTo(s2.getScore());
	                }
					return 0;
				}
			};
			// 这里就会自动根据规则进行排序
			Collections.sort(list, comparator);
			List<String> scores = new ArrayList<String>();
			int total = 0;
			for (Student stu : list) {
				scores.add(stu.getScore());
				total += Integer.parseInt(stu.getScore().trim());
			}
			// 分数最高的学生
			for (Student stu : list) {
				if (stu.getScore().equals(Collections.max(scores))) {
					System.out.println("分数最高的学生姓名是:"+stu.getName()+"，分数是："+stu.getScore());
				}
				if (stu.getScore().equals(Collections.min(scores))) {
					System.out.println("分数最低的学生姓名是:"+stu.getName()+"，分数是："+stu.getScore());
				}
			}
			double avg = total/list.size();
			System.out.println("平均分是：" + avg );
			reader.close();
		} catch (
		IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {

				}
			}
		}
	}
}
